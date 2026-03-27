import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { ApiService } from '../services/api.service';
import { InventarioItem, MovimientoInventario } from '../models';

@Component({
  selector: 'app-inventario-page',
  standalone: true,
  imports: [CommonModule],
  template: `
    <section class="page-header">
      <div>
        <p class="eyebrow">Contexto critico</p>
        <h2>Inventario y trazabilidad</h2>
      </div>
      <p class="lead">
        Aqui conviven el estado actual y el historico de movimientos para mantener auditoria total.
      </p>
    </section>

    <section class="inventory-layout">
      <article class="panel">
        <div class="panel-header">
          <h3>Estado actual</h3>
          <p>{{ inventario.length }} items</p>
        </div>

        <div class="table-like">
          <div class="table-row table-head">
            <span>Ingrediente</span>
            <span>Tipo</span>
            <span>Cantidad</span>
          </div>
          <div class="table-row" *ngFor="let item of inventario">
            <span>{{ item.ingrediente }}</span>
            <span>{{ item.tipoIngrediente }}</span>
            <span>{{ item.cantidadActual }}</span>
          </div>
        </div>
      </article>

      <article class="panel">
        <div class="panel-header">
          <h3>Movimientos</h3>
          <p>Ultimos eventos del inventario</p>
        </div>

        <div class="movement-list">
          <div class="movement-item" *ngFor="let movimiento of movimientos">
            <strong>{{ movimiento.ingrediente }}</strong>
            <span>{{ movimiento.tipo }}</span>
            <span>{{ movimiento.cantidad }}</span>
          </div>
        </div>
      </article>
    </section>
  `
})
export class InventarioPageComponent implements OnInit {
  private readonly api = inject(ApiService);

  inventario: InventarioItem[] = [];
  movimientos: MovimientoInventario[] = [];

  ngOnInit(): void {
    this.api.getInventario().subscribe((inventario) => {
      this.inventario = inventario;
    });

    this.api.getMovimientos().subscribe((movimientos) => {
      this.movimientos = movimientos;
    });
  }
}
