package com.planificador.menu.domain.planificacion;

import com.planificador.menu.domain.common.BaseEntity;
import com.planificador.menu.domain.receta.Receta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class PlanComida extends BaseEntity {

    @ManyToOne(optional = false)
    public PlanAlimentario plan;

    public LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TipoMenu tipoMenu;

    @Column(nullable = false)
    public Integer orden;

    @ManyToOne(optional = false)
    public Receta receta;

    @Column(nullable = false)
    public boolean confirmado;
}

