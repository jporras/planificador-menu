package com.planificador.menu.api.dto;

import java.util.List;

public record RecetaDetalleDto(
        Long id,
        String nombre,
        String descripcion,
        Double caloriasEstimadas,
        List<RecetaIngredienteDto> ingredientes
) {
}

