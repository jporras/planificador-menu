package com.planificador.menu.api;

import com.planificador.menu.api.dto.ConfirmacionResultadoDto;
import com.planificador.menu.api.dto.ConfirmarPlanComidaRequest;
import com.planificador.menu.service.ConfirmacionPlanComidaService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/api/plan-comidas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ConfirmacionResource {

    @Inject
    ConfirmacionPlanComidaService confirmacionPlanComidaService;

    @POST
    @Path("/{planComidaId}/confirmaciones")
    public ConfirmacionResultadoDto confirmar(@PathParam("planComidaId") Long planComidaId,
                                              ConfirmarPlanComidaRequest request) {
        return confirmacionPlanComidaService.confirmar(planComidaId, request);
    }
}
