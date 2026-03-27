package com.planificador.menu.service;

import com.planificador.menu.api.dto.InventarioItemDto;
import com.planificador.menu.api.dto.MovimientoInventarioDto;
import com.planificador.menu.domain.familia.Familia;
import com.planificador.menu.domain.ingrediente.Ingrediente;
import com.planificador.menu.domain.inventario.InventarioItem;
import com.planificador.menu.domain.inventario.MovimientoInventario;
import com.planificador.menu.domain.inventario.TipoMovimientoInventario;
import com.planificador.menu.repository.inventario.InventarioItemRepository;
import com.planificador.menu.repository.inventario.MovimientoInventarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class InventarioService {

    @Inject
    InventarioItemRepository inventarioItemRepository;

    @Inject
    MovimientoInventarioRepository movimientoInventarioRepository;

    public List<InventarioItemDto> getInventarioFamilia(Long familiaId) {
        return inventarioItemRepository.listByFamiliaId(familiaId).stream()
                .map(this::toDto)
                .toList();
    }

    public List<MovimientoInventarioDto> getMovimientos(Long familiaId) {
        return movimientoInventarioRepository.listByFamiliaId(familiaId).stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public MovimientoInventario registrarMovimiento(Familia familia,
                                                    Ingrediente ingrediente,
                                                    TipoMovimientoInventario tipo,
                                                    Double cantidad,
                                                    Long referenciaId) {
        InventarioItem item = inventarioItemRepository.findByFamiliaAndIngrediente(familia.id, ingrediente.id)
                .orElseGet(() -> {
                    InventarioItem nuevo = new InventarioItem();
                    nuevo.familia = familia;
                    nuevo.ingrediente = ingrediente;
                    nuevo.cantidadActual = 0d;
                    inventarioItemRepository.persist(nuevo);
                    return nuevo;
                });

        double siguienteCantidad = switch (tipo) {
            case COMPRA -> item.cantidadActual + cantidad;
            case AJUSTE -> cantidad;
            case CONSUMO -> item.cantidadActual - cantidad;
        };

        if (tipo == TipoMovimientoInventario.CONSUMO && siguienteCantidad < 0) {
            throw new IllegalStateException("Inventario insuficiente para " + ingrediente.nombre);
        }

        item.cantidadActual = siguienteCantidad;

        MovimientoInventario movimiento = new MovimientoInventario();
        movimiento.familia = familia;
        movimiento.ingrediente = ingrediente;
        movimiento.tipo = tipo;
        movimiento.cantidad = cantidad;
        movimiento.fecha = LocalDateTime.now();
        movimiento.referenciaId = referenciaId;
        movimientoInventarioRepository.persist(movimiento);
        return movimiento;
    }

    private InventarioItemDto toDto(InventarioItem item) {
        return new InventarioItemDto(
                item.id,
                item.ingrediente.id,
                item.ingrediente.nombre,
                item.ingrediente.tipo.name(),
                item.cantidadActual
        );
    }

    private MovimientoInventarioDto toDto(MovimientoInventario movimiento) {
        return new MovimientoInventarioDto(
                movimiento.id,
                movimiento.ingrediente.nombre,
                movimiento.tipo.name(),
                movimiento.cantidad,
                movimiento.fecha,
                movimiento.referenciaId
        );
    }
}

