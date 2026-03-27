package com.planificador.menu.service;

import com.planificador.menu.api.dto.PlanComidaDto;
import com.planificador.menu.api.dto.UsuarioResumenDto;
import com.planificador.menu.domain.familia.Usuario;
import com.planificador.menu.domain.planificacion.PlanComida;
import com.planificador.menu.repository.familia.UsuarioRepository;
import com.planificador.menu.repository.planificacion.PlanComidaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class PlanificacionService {

    @Inject
    PlanComidaRepository planComidaRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    public List<PlanComidaDto> getPendientes(Long familiaId) {
        List<UsuarioResumenDto> integrantes = usuarioRepository.listByFamiliaId(familiaId).stream()
                .map(this::toUsuario)
                .toList();

        return planComidaRepository.listPendientesByFamilia(familiaId, LocalDate.now()).stream()
                .map(planComida -> toDto(planComida, integrantes))
                .toList();
    }

    private PlanComidaDto toDto(PlanComida planComida, List<UsuarioResumenDto> integrantes) {
        return new PlanComidaDto(
                planComida.id,
                planComida.plan.id,
                planComida.fecha,
                planComida.tipoMenu.name(),
                planComida.orden,
                planComida.confirmado,
                planComida.receta.id,
                planComida.receta.nombre,
                integrantes
        );
    }

    private UsuarioResumenDto toUsuario(Usuario usuario) {
        return new UsuarioResumenDto(usuario.id, usuario.nombre);
    }
}
