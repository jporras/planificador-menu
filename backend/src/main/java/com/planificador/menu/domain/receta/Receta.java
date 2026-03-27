package com.planificador.menu.domain.receta;

import com.planificador.menu.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Receta extends BaseEntity {

    @Column(nullable = false, unique = true)
    public String nombre;

    @Column(length = 2000)
    public String descripcion;
}

