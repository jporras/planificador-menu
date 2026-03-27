package com.planificador.menu.api;

import com.planificador.menu.api.dto.PlanComidaDto;
import com.planificador.menu.service.PlanificacionService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/api/familias/{familiaId}/plan-comidas")
@Produces(MediaType.APPLICATION_JSON)
public class PlanificacionResource {

    @Inject
    PlanificacionService planificacionService;

    @GET
    @Path("/pendientes")
    public List<PlanComidaDto> pendientes(@PathParam("familiaId") Long familiaId) {
        return planificacionService.getPendientes(familiaId);
    }
}

