package com.planificador.menu.api.dto;

import java.time.LocalDate;
import java.util.List;

public record PlanComidaDto(
        Long id,
        Long planId,
        LocalDate fecha,
        String tipoMenu,
        Integer orden,
        boolean confirmado,
        Long recetaId,
        String receta,
        List<UsuarioResumenDto> integrantes
) {
}

