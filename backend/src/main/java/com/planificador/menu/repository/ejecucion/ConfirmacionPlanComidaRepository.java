package com.planificador.menu.repository.ejecucion;

import com.planificador.menu.domain.ejecucion.ConfirmacionPlanComida;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ConfirmacionPlanComidaRepository implements PanacheRepository<ConfirmacionPlanComida> {
}

