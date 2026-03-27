package com.planificador.menu.repository.familia;

import com.planificador.menu.domain.familia.Usuario;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class UsuarioRepository implements PanacheRepository<Usuario> {

    public List<Usuario> listByFamiliaId(Long familiaId) {
        return list("familia.id", familiaId);
    }
}

