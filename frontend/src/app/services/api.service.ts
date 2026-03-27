import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
  ConfirmacionResultado,
  ConfirmarPlanComidaRequest,
  InventarioItem,
  MovimientoInventario,
  PlanComida,
  RecetaDetalle,
  RecetaResumen
} from '../models';

@Injectable({ providedIn: 'root' })
export class ApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = 'http://localhost:8080/api';
  private readonly familiaId = 1;

  getRecetas(): Observable<RecetaResumen[]> {
    return this.http.get<RecetaResumen[]>(`${this.baseUrl}/recetas`);
  }

  getReceta(id: number): Observable<RecetaDetalle> {
    return this.http.get<RecetaDetalle>(`${this.baseUrl}/recetas/${id}`);
  }

  getPlanPendiente(): Observable<PlanComida[]> {
    return this.http.get<PlanComida[]>(`${this.baseUrl}/familias/${this.familiaId}/plan-comidas/pendientes`);
  }

  getInventario(): Observable<InventarioItem[]> {
    return this.http.get<InventarioItem[]>(`${this.baseUrl}/familias/${this.familiaId}/inventario`);
  }

  getMovimientos(): Observable<MovimientoInventario[]> {
    return this.http.get<MovimientoInventario[]>(`${this.baseUrl}/familias/${this.familiaId}/inventario/movimientos`);
  }

  confirmarPlanComida(planComidaId: number, payload: ConfirmarPlanComidaRequest): Observable<ConfirmacionResultado> {
    return this.http.post<ConfirmacionResultado>(`${this.baseUrl}/plan-comidas/${planComidaId}/confirmaciones`, payload);
  }
}

