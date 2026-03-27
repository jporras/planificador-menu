package com.planificador.menu.repository.receta;

import com.planificador.menu.domain.receta.RecetaIngrediente;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class RecetaIngredienteRepository implements PanacheRepository<RecetaIngrediente> {

    public List<RecetaIngrediente> listByRecetaId(Long recetaId) {
        return list("receta.id", recetaId);
    }
}

