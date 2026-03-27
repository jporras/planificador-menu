package com.planificador.menu.domain.ingrediente;

import com.planificador.menu.domain.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class Ingrediente extends BaseEntity {

    @Column(nullable = false, unique = true)
    public String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TipoIngrediente tipo;

    @Column(nullable = false)
    public Double caloriasPorPorcion;
}

