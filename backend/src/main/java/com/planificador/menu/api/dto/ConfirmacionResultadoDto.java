package com.planificador.menu.api.dto;

import java.util.List;

public record ConfirmacionResultadoDto(
        Long confirmacionId,
        Long planComidaId,
        int porcionesPreparadas,
        List<InventarioItemDto> inventarioActual,
        List<MovimientoInventarioDto> movimientosGenerados
) {
}
