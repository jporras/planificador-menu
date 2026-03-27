package com.planificador.menu.domain.planificacion;

import com.planificador.menu.domain.common.BaseEntity;
import com.planificador.menu.domain.familia.Familia;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class PlanAlimentario extends BaseEntity {

    @ManyToOne(optional = false)
    public Familia familia;

    public LocalDate fechaInicio;

    public LocalDate fechaFin;
}

