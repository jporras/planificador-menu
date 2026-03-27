package com.planificador.menu.repository.receta;

import com.planificador.menu.domain.receta.Receta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RecetaRepository implements PanacheRepository<Receta> {
}

