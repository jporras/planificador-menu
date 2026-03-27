import { Routes } from '@angular/router';
import { DashboardPageComponent } from './pages/dashboard-page.component';
import { RecetasPageComponent } from './pages/recetas-page.component';
import { InventarioPageComponent } from './pages/inventario-page.component';

export const routes: Routes = [
  { path: '', component: DashboardPageComponent },
  { path: 'recetas', component: RecetasPageComponent },
  { path: 'inventario', component: InventarioPageComponent },
  { path: '**', redirectTo: '' }
];

