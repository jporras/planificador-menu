package com.planificador.menu.api.dto;

import java.util.List;

public record ConfirmarPlanComidaRequest(
        Long recetaFinalId,
        Integer invitados,
        Integer porcionesPreparadas,
        List<Long> usuariosConsumidores
) {
}

