import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { ApiService } from '../services/api.service';
import { RecetaDetalle, RecetaResumen } from '../models';

@Component({
  selector: 'app-recetas-page',
  standalone: true,
  imports: [CommonModule],
  template: `
    <section class="page-header">
      <div>
        <p class="eyebrow">Contexto de recetas</p>
        <h2>Recetas definidas por persona</h2>
      </div>
      <p class="lead">
        Cada receta se define para una sola persona y escala al confirmar una comida.
      </p>
    </section>

    <section class="recipe-layout">
      <aside class="panel recipe-list">
        <div class="panel-header">
          <h3>Catalogo</h3>
          <p>{{ recetas.length }} recetas disponibles</p>
        </div>

        <button
          type="button"
          class="recipe-button"
          *ngFor="let receta of recetas"
          [class.active]="receta.id === recetaActiva?.id"
          (click)="seleccionar(receta.id)">
          <span>{{ receta.nombre }}</span>
          <small>{{ receta.caloriasEstimadas | number:'1.0-0' }} kcal</small>
        </button>
      </aside>

      <section class="panel" *ngIf="recetaActiva">
        <div class="panel-header">
          <div>
            <h3>{{ recetaActiva.nombre }}</h3>
            <p>{{ recetaActiva.descripcion }}</p>
          </div>
          <strong>{{ recetaActiva.caloriasEstimadas | number:'1.0-0' }} kcal</strong>
        </div>

        <div class="ingredient-grid">
          <article class="ingredient-card" *ngFor="let ingrediente of recetaActiva.ingredientes">
            <span>{{ ingrediente.ingrediente }}</span>
            <strong>{{ ingrediente.cantidadBase }} {{ ingrediente.unidad }}</strong>
            <small>{{ ingrediente.caloriasTotales | number:'1.0-0' }} kcal</small>
          </article>
        </div>
      </section>
    </section>
  `
})
export class RecetasPageComponent implements OnInit {
  private readonly api = inject(ApiService);

  recetas: RecetaResumen[] = [];
  recetaActiva: RecetaDetalle | null = null;

  ngOnInit(): void {
    this.api.getRecetas().subscribe((recetas) => {
      this.recetas = recetas;
      if (recetas.length > 0) {
        this.seleccionar(recetas[0].id);
      }
    });
  }

  seleccionar(id: number): void {
    this.api.getReceta(id).subscribe((receta) => {
      this.recetaActiva = receta;
    });
  }
}

