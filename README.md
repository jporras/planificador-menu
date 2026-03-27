# Planificador de Menu

Aplicacion full stack para planificacion alimentaria familiar con:

- Backend en Quarkus
- Frontend en Angular
- Dominio organizado por bounded contexts practicos

## Estructura

- `backend/`: API REST, dominio, reglas de negocio e inicializacion
- `frontend/`: aplicacion Angular para gestionar familias, recetas, planes e inventario

## Dominio principal

Se implementaron estos contextos:

- Familia y usuarios
- Ingredientes y nutricion
- Recetas
- Planificacion
- Inventario
- Ejecucion de plan
- Consumo

## Reglas modeladas

- El inventario solo cambia por compras, ajustes o confirmacion de comidas
- Un plan no consume inventario hasta que se confirma una comida
- Las recetas se definen para 1 persona
- El consumo es editable y no modifica inventario

## Backend

Requisitos:

- Java 21 o superior
- Maven o Maven Wrapper

Comandos:

```powershell
cd backend
mvn quarkus:dev
```

## Frontend

Requisitos:

- Node.js
- npm

Comandos en PowerShell:

```powershell
cd frontend
npm.cmd install
npm.cmd start
```

## Siguientes pasos recomendados

1. Agregar autenticacion y autorizacion por familia.
2. Persistir en PostgreSQL para entorno real.
3. Agregar pruebas de reglas de inventario y confirmacion.
4. Incorporar modulo de compras para alimentar `MovimientoInventario`.

