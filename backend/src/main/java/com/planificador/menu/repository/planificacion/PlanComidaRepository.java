package com.planificador.menu.repository.planificacion;

import com.planificador.menu.domain.planificacion.PlanComida;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class PlanComidaRepository implements PanacheRepository<PlanComida> {

    public List<PlanComida> listByPlanId(Long planId) {
        return list("plan.id order by fecha, orden", planId);
    }

    public List<PlanComida> listPendientesByFamilia(Long familiaId, LocalDate fromDate) {
        return list("plan.familia.id = ?1 and fecha >= ?2 and confirmado = false order by fecha, orden", familiaId, fromDate);
    }
}

