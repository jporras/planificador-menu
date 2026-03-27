package com.planificador.menu.api;

import com.planificador.menu.api.dto.RecetaDetalleDto;
import com.planificador.menu.api.dto.RecetaResumenDto;
import com.planificador.menu.service.RecetaQueryService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/recetas")
@Produces(MediaType.APPLICATION_JSON)
public class RecetaResource {

    @Inject
    RecetaQueryService recetaQueryService;

    @GET
    public List<RecetaResumenDto> list() {
        return recetaQueryService.listRecetas();
    }

    @GET
    @Path("/{id}")
    public RecetaDetalleDto get(@PathParam("id") Long id) {
        return recetaQueryService.getDetalle(id);
    }
}

