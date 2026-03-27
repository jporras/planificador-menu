package com.planificador.menu.domain.familia;

import com.planificador.menu.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Familia extends BaseEntity {

    @Column(nullable = false, unique = true)
    public String nombre;
}

