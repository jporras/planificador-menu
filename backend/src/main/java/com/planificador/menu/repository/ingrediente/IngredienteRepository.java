package com.planificador.menu.repository.ingrediente;

import com.planificador.menu.domain.ingrediente.Ingrediente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class IngredienteRepository implements PanacheRepository<Ingrediente> {
}

