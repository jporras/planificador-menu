package com.planificador.menu.api.dto;

public record RecetaIngredienteDto(
        Long ingredienteId,
        String ingrediente,
        Double cantidadBase,
        String unidad,
        Double caloriasPorPorcion,
        Double caloriasTotales
) {
}

