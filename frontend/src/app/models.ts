export interface UsuarioResumen {
  id: number;
  nombre: string;
}

export interface PlanComida {
  id: number;
  planId: number;
  fecha: string;
  tipoMenu: string;
  orden: number;
  confirmado: boolean;
  recetaId: number;
  receta: string;
  integrantes: UsuarioResumen[];
}

export interface RecetaResumen {
  id: number;
  nombre: string;
  descripcion: string;
  caloriasEstimadas: number;
}

export interface RecetaIngrediente {
  ingredienteId: number;
  ingrediente: string;
  cantidadBase: number;
  unidad: string;
  caloriasPorPorcion: number;
  caloriasTotales: number;
}

export interface RecetaDetalle extends RecetaResumen {
  ingredientes: RecetaIngrediente[];
}

export interface InventarioItem {
  id: number;
  ingredienteId: number;
  ingrediente: string;
  tipoIngrediente: string;
  cantidadActual: number;
}

export interface MovimientoInventario {
  id: number;
  ingrediente: string;
  tipo: string;
  cantidad: number;
  fecha: string;
  referenciaId: number | null;
}

export interface ConfirmarPlanComidaRequest {
  recetaFinalId: number | null;
  invitados: number;
  porcionesPreparadas: number | null;
  usuariosConsumidores: number[];
}

export interface ConfirmacionResultado {
  confirmacionId: number;
  planComidaId: number;
  porcionesPreparadas: number;
  inventarioActual: InventarioItem[];
  movimientosGenerados: MovimientoInventario[];
}

