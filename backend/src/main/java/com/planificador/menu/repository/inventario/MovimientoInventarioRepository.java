package com.planificador.menu.repository.inventario;

import com.planificador.menu.domain.inventario.MovimientoInventario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MovimientoInventarioRepository implements PanacheRepository<MovimientoInventario> {

    public List<MovimientoInventario> listByFamiliaId(Long familiaId) {
        return list("familia.id order by fecha desc", familiaId);
    }
}

