package com.planificador.menu.api.dto;

public record RecetaResumenDto(
        Long id,
        String nombre,
        String descripcion,
        Double caloriasEstimadas
) {
}

