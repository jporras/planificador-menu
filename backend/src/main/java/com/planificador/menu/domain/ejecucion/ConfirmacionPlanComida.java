package com.planificador.menu.domain.ejecucion;

import com.planificador.menu.domain.common.BaseEntity;
import com.planificador.menu.domain.planificacion.PlanComida;
import com.planificador.menu.domain.receta.Receta;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class ConfirmacionPlanComida extends BaseEntity {

    @OneToOne(optional = false)
    public PlanComida planComida;

    @ManyToOne(optional = false)
    public Receta recetaFinal;

    @Column(nullable = false)
    public Integer porcionesPreparadas;

    @Column(nullable = false)
    public LocalDateTime fechaConfirmacion;
}

