package com.planificador.menu.domain.inventario;

import com.planificador.menu.domain.common.BaseEntity;
import com.planificador.menu.domain.familia.Familia;
import com.planificador.menu.domain.ingrediente.Ingrediente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class InventarioItem extends BaseEntity {

    @ManyToOne(optional = false)
    public Familia familia;

    @ManyToOne(optional = false)
    public Ingrediente ingrediente;

    @Column(nullable = false)
    public Double cantidadActual;
}

