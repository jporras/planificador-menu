import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, RouterLinkActive],
  template: `
    <div class="shell">
      <aside class="sidebar">
        <p class="eyebrow">Planificador familiar</p>
        <h1>Menu, inventario y ejecucion en un solo flujo.</h1>
        <nav>
          <a routerLink="/" routerLinkActive="active" [routerLinkActiveOptions]="{ exact: true }">Resumen</a>
          <a routerLink="/recetas" routerLinkActive="active">Recetas</a>
          <a routerLink="/inventario" routerLinkActive="active">Inventario</a>
        </nav>
        <div class="sidebar-card">
          <span>Regla central</span>
          <p>El inventario solo cambia al comprar, ajustar o confirmar una comida.</p>
        </div>
      </aside>

      <main class="content">
        <router-outlet />
      </main>
    </div>
  `
})
export class AppComponent {
}
