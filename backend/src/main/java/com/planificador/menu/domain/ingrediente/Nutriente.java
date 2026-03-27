package com.planificador.menu.domain.ingrediente;

import com.planificador.menu.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Nutriente extends BaseEntity {

    @Column(nullable = false, unique = true)
    public String nombre;

    @Column(nullable = false)
    public String tipo;
}

