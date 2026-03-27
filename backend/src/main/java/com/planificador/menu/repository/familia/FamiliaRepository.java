package com.planificador.menu.repository.familia;

import com.planificador.menu.domain.familia.Familia;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FamiliaRepository implements PanacheRepository<Familia> {
}

