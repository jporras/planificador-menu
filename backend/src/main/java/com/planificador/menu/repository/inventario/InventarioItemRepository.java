package com.planificador.menu.repository.inventario;

import com.planificador.menu.domain.inventario.InventarioItem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InventarioItemRepository implements PanacheRepository<InventarioItem> {

    public List<InventarioItem> listByFamiliaId(Long familiaId) {
        return list("familia.id order by ingrediente.nombre", familiaId);
    }

    public Optional<InventarioItem> findByFamiliaAndIngrediente(Long familiaId, Long ingredienteId) {
        return find("familia.id = ?1 and ingrediente.id = ?2", familiaId, ingredienteId).firstResultOptional();
    }
}

