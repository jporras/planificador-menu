package com.planificador.menu.service;

import com.planificador.menu.api.dto.ConfirmacionResultadoDto;
import com.planificador.menu.api.dto.ConfirmarPlanComidaRequest;
import com.planificador.menu.api.dto.InventarioItemDto;
import com.planificador.menu.api.dto.MovimientoInventarioDto;
import com.planificador.menu.domain.consumo.Consumo;
import com.planificador.menu.domain.ejecucion.ConfirmacionPlanComida;
import com.planificador.menu.domain.familia.Usuario;
import com.planificador.menu.domain.inventario.MovimientoInventario;
import com.planificador.menu.domain.inventario.TipoMovimientoInventario;
import com.planificador.menu.domain.planificacion.PlanComida;
import com.planificador.menu.domain.receta.Receta;
import com.planificador.menu.domain.receta.RecetaIngrediente;
import com.planificador.menu.repository.consumo.ConsumoRepository;
import com.planificador.menu.repository.ejecucion.ConfirmacionPlanComidaRepository;
import com.planificador.menu.repository.familia.UsuarioRepository;
import com.planificador.menu.repository.planificacion.PlanComidaRepository;
import com.planificador.menu.repository.receta.RecetaIngredienteRepository;
import com.planificador.menu.repository.receta.RecetaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ConfirmacionPlanComidaService {

    @Inject
    PlanComidaRepository planComidaRepository;

    @Inject
    RecetaRepository recetaRepository;

    @Inject
    RecetaIngredienteRepository recetaIngredienteRepository;

    @Inject
    ConfirmacionPlanComidaRepository confirmacionRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    ConsumoRepository consumoRepository;

    @Inject
    InventarioService inventarioService;

    @Transactional
    public ConfirmacionResultadoDto confirmar(Long planComidaId, ConfirmarPlanComidaRequest request) {
        PlanComida planComida = planComidaRepository.findByIdOptional(planComidaId)
                .orElseThrow(() -> new IllegalArgumentException("Plan comida no encontrado"));

        if (planComida.confirmado) {
            throw new IllegalStateException("El plan comida ya fue confirmado");
        }

        Receta recetaFinal = recetaRepository.findByIdOptional(
                        request.recetaFinalId() != null ? request.recetaFinalId() : planComida.receta.id)
                .orElseThrow(() -> new IllegalArgumentException("Receta final no encontrada"));

        int integrantes = usuarioRepository.listByFamiliaId(planComida.plan.familia.id).size();
        int invitados = request.invitados() != null ? request.invitados() : 0;
        int porcionesPreparadas = request.porcionesPreparadas() != null
                ? request.porcionesPreparadas()
                : integrantes + invitados;

        if (porcionesPreparadas <= 0) {
            throw new IllegalArgumentException("Las porciones preparadas deben ser mayores que cero");
        }

        ConfirmacionPlanComida confirmacion = new ConfirmacionPlanComida();
        confirmacion.planComida = planComida;
        confirmacion.recetaFinal = recetaFinal;
        confirmacion.porcionesPreparadas = porcionesPreparadas;
        confirmacion.fechaConfirmacion = LocalDateTime.now();
        confirmacionRepository.persist(confirmacion);

        List<MovimientoInventarioDto> movimientos = new ArrayList<>();
        List<RecetaIngrediente> ingredientes = recetaIngredienteRepository.listByRecetaId(recetaFinal.id);
        for (RecetaIngrediente recetaIngrediente : ingredientes) {
            MovimientoInventario movimiento = inventarioService.registrarMovimiento(
                    planComida.plan.familia,
                    recetaIngrediente.ingrediente,
                    TipoMovimientoInventario.CONSUMO,
                    recetaIngrediente.cantidadBase * porcionesPreparadas,
                    confirmacion.id
            );
            movimientos.add(new MovimientoInventarioDto(
                    movimiento.id,
                    movimiento.ingrediente.nombre,
                    movimiento.tipo.name(),
                    movimiento.cantidad,
                    movimiento.fecha,
                    movimiento.referenciaId
            ));
        }

        List<Long> consumidoresIds = request.usuariosConsumidores();
        List<Usuario> consumidores = consumidoresIds == null || consumidoresIds.isEmpty()
                ? usuarioRepository.listByFamiliaId(planComida.plan.familia.id)
                : consumidoresIds.stream()
                        .map(id -> usuarioRepository.findByIdOptional(id)
                                .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado: " + id)))
                        .toList();

        double porcionIndividual = (double) porcionesPreparadas / consumidores.size();
        for (Usuario consumidor : consumidores) {
            Consumo consumo = new Consumo();
            consumo.usuario = consumidor;
            consumo.receta = recetaFinal;
            consumo.planComida = planComida;
            consumo.fecha = planComida.fecha;
            consumo.porciones = porcionIndividual;
            consumo.esAlternativo = !recetaFinal.id.equals(planComida.receta.id);
            consumoRepository.persist(consumo);
        }

        planComida.confirmado = true;

        List<InventarioItemDto> inventarioActual = inventarioService.getInventarioFamilia(planComida.plan.familia.id);
        return new ConfirmacionResultadoDto(
                confirmacion.id,
                planComida.id,
                porcionesPreparadas,
                inventarioActual,
                movimientos
        );
    }
}

