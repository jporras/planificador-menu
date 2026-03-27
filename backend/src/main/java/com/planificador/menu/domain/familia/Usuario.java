package com.planificador.menu.domain.familia;

import com.planificador.menu.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Usuario extends BaseEntity {

    @Column(nullable = false)
    public String nombre;

    @ManyToOne(optional = false)
    public Familia familia;
}

