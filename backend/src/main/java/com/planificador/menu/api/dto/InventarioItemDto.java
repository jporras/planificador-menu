package com.planificador.menu.api.dto;

public record InventarioItemDto(
        Long id,
        Long ingredienteId,
        String ingrediente,
        String tipoIngrediente,
        Double cantidadActual
) {
}

