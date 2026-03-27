package com.planificador.menu.repository.consumo;

import com.planificador.menu.domain.consumo.Consumo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ConsumoRepository implements PanacheRepository<Consumo> {

    public List<Consumo> listByPlanComida(Long planComidaId) {
        return list("planComida.id", planComidaId);
    }
}
