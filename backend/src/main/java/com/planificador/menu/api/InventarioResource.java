package com.planificador.menu.api;

import com.planificador.menu.api.dto.InventarioItemDto;
import com.planificador.menu.api.dto.MovimientoInventarioDto;
import com.planificador.menu.service.InventarioService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/familias/{familiaId}/inventario")
@Produces(MediaType.APPLICATION_JSON)
public class InventarioResource {

    @Inject
    InventarioService inventarioService;

    @GET
    public List<InventarioItemDto> getInventario(@PathParam("familiaId") Long familiaId) {
        return inventarioService.getInventarioFamilia(familiaId);
    }

    @GET
    @Path("/movimientos")
    public List<MovimientoInventarioDto> getMovimientos(@PathParam("familiaId") Long familiaId) {
        return inventarioService.getMovimientos(familiaId);
    }
}

