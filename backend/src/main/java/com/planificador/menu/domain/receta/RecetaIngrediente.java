package com.planificador.menu.domain.receta;

import com.planificador.menu.domain.common.BaseEntity;
import com.planificador.menu.domain.ingrediente.Ingrediente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class RecetaIngrediente extends BaseEntity {

    @ManyToOne(optional = false)
    public Receta receta;

    @ManyToOne(optional = false)
    public Ingrediente ingrediente;

    @Column(nullable = false)
    public Double cantidadBase;

    @Column(nullable = false)
    public String unidad;
}

