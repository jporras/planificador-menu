package com.planificador.menu.domain.consumo;

import com.planificador.menu.domain.common.BaseEntity;
import com.planificador.menu.domain.familia.Usuario;
import com.planificador.menu.domain.planificacion.PlanComida;
import com.planificador.menu.domain.receta.Receta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Consumo extends BaseEntity {

    @ManyToOne(optional = false)
    public Usuario usuario;

    @ManyToOne(optional = false)
    public Receta receta;

    @ManyToOne
    public PlanComida planComida;

    @Column(nullable = false)
    public LocalDate fecha;

    @Column(nullable = false)
    public Double porciones;

    @Column(nullable = false)
    public boolean esAlternativo;
}
