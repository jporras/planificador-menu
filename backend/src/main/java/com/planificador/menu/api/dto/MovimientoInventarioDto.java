package com.planificador.menu.api.dto;

import java.time.LocalDateTime;

public record MovimientoInventarioDto(
        Long id,
        String ingrediente,
        String tipo,
        Double cantidad,
        LocalDateTime fecha,
        Long referenciaId
) {
}

