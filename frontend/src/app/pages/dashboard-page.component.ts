import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ApiService } from '../services/api.service';
import { ConfirmacionResultado, PlanComida } from '../models';

@Component({
  selector: 'app-dashboard-page',
  standalone: true,
  imports: [CommonModule, FormsModule],
  template: `
    <section class="page-header">
      <div>
        <p class="eyebrow">Contexto de planificacion</p>
        <h2>Comidas pendientes por confirmar</h2>
      </div>
      <p class="lead">
        Confirmar una comida descuenta inventario, crea movimientos y genera consumos para los integrantes.
      </p>
    </section>

    <section class="cards-grid">
      <article class="stat-card">
        <span>Flujo</span>
        <strong>{{ planes.length }}</strong>
        <p>comidas pendientes desde hoy</p>
      </article>
      <article class="stat-card accent">
        <span>Familia demo</span>
        <strong>3</strong>
        <p>integrantes precargados</p>
      </article>
    </section>

    <section class="panel" *ngIf="planes.length; else noData">
      <div class="panel-header">
        <h3>Agenda operativa</h3>
        <p>Selecciona una comida y confirma la preparacion real.</p>
      </div>

      <div class="plan-list">
        <article class="plan-card" *ngFor="let plan of planes">
          <div class="plan-card__main">
            <p class="plan-date">{{ plan.fecha | date:'fullDate' }}</p>
            <h4>{{ plan.tipoMenu }} · {{ plan.receta }}</h4>
            <p>{{ plan.integrantes.length }} integrantes previstos</p>
          </div>

          <div class="plan-card__actions">
            <label>
              Invitados
              <input type="number" min="0" [(ngModel)]="invitados[plan.id]" />
            </label>
            <button type="button" (click)="confirmar(plan)">Confirmar</button>
          </div>
        </article>
      </div>
    </section>

    <section class="panel success" *ngIf="resultado">
      <div class="panel-header">
        <h3>Confirmacion registrada</h3>
        <p>Se generaron {{ resultado.movimientosGenerados.length }} movimientos de inventario.</p>
      </div>

      <div class="movement-list">
        <div *ngFor="let movimiento of resultado.movimientosGenerados" class="movement-item">
          <strong>{{ movimiento.ingrediente }}</strong>
          <span>{{ movimiento.tipo }}</span>
          <span>{{ movimiento.cantidad }}</span>
        </div>
      </div>
    </section>

    <ng-template #noData>
      <section class="panel">
        <h3>No hay comidas pendientes</h3>
        <p>Cuando existan planes sin confirmar apareceran aqui.</p>
      </section>
    </ng-template>
  `
})
export class DashboardPageComponent implements OnInit {
  private readonly api = inject(ApiService);

  planes: PlanComida[] = [];
  invitados: Record<number, number> = {};
  resultado: ConfirmacionResultado | null = null;

  ngOnInit(): void {
    this.cargarPlanes();
  }

  confirmar(plan: PlanComida): void {
    const invitados = this.invitados[plan.id] ?? 0;
    this.api.confirmarPlanComida(plan.id, {
      recetaFinalId: plan.recetaId,
      invitados,
      porcionesPreparadas: plan.integrantes.length + invitados,
      usuariosConsumidores: plan.integrantes.map((integrante) => integrante.id)
    }).subscribe((resultado) => {
      this.resultado = resultado;
      this.cargarPlanes();
    });
  }

  private cargarPlanes(): void {
    this.api.getPlanPendiente().subscribe((planes) => {
      this.planes = planes;
    });
  }
}

