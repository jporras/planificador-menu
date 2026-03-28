# Planificador de Menu

Aplicacion full stack para planificacion alimentaria familiar con:

- Frontend en Angular
- API backend en Quarkus
- Kong como API Gateway
- Oracle Database Free para entorno Docker
- Dominio organizado por bounded contexts practicos

## Arquitectura

### Desarrollo local

- Frontend Angular
- Backend Quarkus
- H2 en memoria para desarrollo rapido

### Arquitectura Docker

- `frontend/`: aplicacion Angular
- `backend/`: API REST, dominio y reglas de negocio
- `kong/`: gateway, configuracion declarativa DB-less, `Dockerfile`, `.env` y `.env.example`
- `oracle/`: contenedor Oracle Free, `Dockerfile`, `.env` y `.env.example`

Flujo principal:

`Navegador -> Angular -> Kong -> Quarkus -> Oracle`

## Estructura

- `backend/`: API REST, entidades, servicios, integracion con base de datos, `Dockerfile`, `.env` y `.env.example`
- `frontend/`: interfaz Angular para recetas, planes e inventario, `Dockerfile` y `proxy.conf.json`
- `kong/`: `Dockerfile`, `kong.yml`, `.env` y `.env.example` del gateway
- `oracle/`: `Dockerfile`, `.env` y `.env.example` de la base de datos
- `docker-compose.yml`: orquestacion de frontend, Kong, backend y Oracle

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

## Desarrollo local

### Backend

Requisitos:

- Java 21 o superior
- Maven o Maven Wrapper

Comandos:

```powershell
cd backend
mvn quarkus:dev
```

Por defecto en desarrollo local Quarkus usa H2 en memoria.

### Frontend

Requisitos:

- Node.js
- npm

Comandos en PowerShell:

```powershell
cd frontend
npm.cmd install
npm.cmd start
```

## Docker

Levantar toda la arquitectura:

```powershell
docker compose up --build
```

Servicios expuestos:

- Frontend: `http://localhost:4200`
- Kong Proxy: `http://localhost:8000`
- Kong Admin API: `http://localhost:8001`
- Oracle: `localhost:1521`

Archivos de variables por servicio:

- `backend/.env` y `backend/.env.example`
- `kong/.env` y `kong/.env.example`
- `oracle/.env` y `oracle/.env.example`

Credenciales por defecto:

- Oracle app user: `planificador`
- Oracle app password: `Planificador123`
- Oracle admin password: `Oracle123`
- Oracle service name: `FREEPDB1`

Notas:

- `Kong` corre en modo DB-less y carga su declarativa desde `kong/kong.yml`.
- Las variables de Docker salieron de `docker-compose.yml` y quedaron junto a cada servicio.
- Los `.env` reales estan ignorados en git y los `.env.example` sirven como plantilla.
- En desarrollo local puedes seguir usando H2 con `mvn quarkus:dev`.

## Siguientes pasos recomendados

1. Agregar autenticacion y autorizacion por familia.
2. Anadir plugins y politicas en Kong, por ejemplo rate limiting o API keys.
3. Agregar pruebas de integracion para confirmacion de comidas e inventario.
4. Incorporar modulo de compras para alimentar `MovimientoInventario`.
