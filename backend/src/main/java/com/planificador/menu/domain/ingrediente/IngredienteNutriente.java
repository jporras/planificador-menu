package com.planificador.menu.domain.ingrediente;

import com.planificador.menu.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class IngredienteNutriente extends BaseEntity {

    @ManyToOne(optional = false)
    public Ingrediente ingrediente;

    @ManyToOne(optional = false)
    public Nutriente nutriente;

    @Column(nullable = false)
    public Double cantidad;
}

