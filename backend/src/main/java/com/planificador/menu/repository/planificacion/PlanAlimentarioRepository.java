package com.planificador.menu.repository.planificacion;

import com.planificador.menu.domain.planificacion.PlanAlimentario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PlanAlimentarioRepository implements PanacheRepository<PlanAlimentario> {
}

