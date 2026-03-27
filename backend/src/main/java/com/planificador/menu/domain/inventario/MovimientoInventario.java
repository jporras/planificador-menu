package com.planificador.menu.domain.inventario;

import com.planificador.menu.domain.common.BaseEntity;
import com.planificador.menu.domain.familia.Familia;
import com.planificador.menu.domain.ingrediente.Ingrediente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class MovimientoInventario extends BaseEntity {

    @ManyToOne(optional = false)
    public Familia familia;

    @ManyToOne(optional = false)
    public Ingrediente ingrediente;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public TipoMovimientoInventario tipo;

    @Column(nullable = false)
    public Double cantidad;

    @Column(nullable = false)
    public LocalDateTime fecha;

    public Long referenciaId;
}

