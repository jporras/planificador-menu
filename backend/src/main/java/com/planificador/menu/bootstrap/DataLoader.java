package com.planificador.menu.bootstrap;

import com.planificador.menu.domain.familia.Familia;
import com.planificador.menu.domain.familia.Usuario;
import com.planificador.menu.domain.ingrediente.Ingrediente;
import com.planificador.menu.domain.ingrediente.TipoIngrediente;
import com.planificador.menu.domain.inventario.TipoMovimientoInventario;
import com.planificador.menu.domain.planificacion.PlanAlimentario;
import com.planificador.menu.domain.planificacion.PlanComida;
import com.planificador.menu.domain.planificacion.TipoMenu;
import com.planificador.menu.domain.receta.Receta;
import com.planificador.menu.domain.receta.RecetaIngrediente;
import com.planificador.menu.repository.familia.FamiliaRepository;
import com.planificador.menu.repository.familia.UsuarioRepository;
import com.planificador.menu.repository.ingrediente.IngredienteRepository;
import com.planificador.menu.repository.planificacion.PlanAlimentarioRepository;
import com.planificador.menu.repository.planificacion.PlanComidaRepository;
import com.planificador.menu.repository.receta.RecetaIngredienteRepository;
import com.planificador.menu.repository.receta.RecetaRepository;
import com.planificador.menu.service.InventarioService;
import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.time.LocalDate;

@ApplicationScoped
public class DataLoader {

    @Inject
    FamiliaRepository familiaRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    IngredienteRepository ingredienteRepository;

    @Inject
    RecetaRepository recetaRepository;

    @Inject
    RecetaIngredienteRepository recetaIngredienteRepository;

    @Inject
    PlanAlimentarioRepository planAlimentarioRepository;

    @Inject
    PlanComidaRepository planComidaRepository;

    @Inject
    InventarioService inventarioService;

    @Transactional
    void onStart(@Observes StartupEvent event) {
        if (familiaRepository.count() > 0) {
            return;
        }

        Familia familia = new Familia();
        familia.nombre = "Familia Garcia";
        familiaRepository.persist(familia);

        crearUsuario("Ana", familia);
        crearUsuario("Luis", familia);
        crearUsuario("Mia", familia);

        Ingrediente pollo = crearIngrediente("Pollo", TipoIngrediente.PROTEINA, 165d);
        Ingrediente arroz = crearIngrediente("Arroz", TipoIngrediente.CEREAL, 130d);
        Ingrediente brocoli = crearIngrediente("Brocoli", TipoIngrediente.VEGETAL, 35d);
        Ingrediente avena = crearIngrediente("Avena", TipoIngrediente.CEREAL, 150d);
        Ingrediente leche = crearIngrediente("Leche", TipoIngrediente.LACTEO, 103d);
        Ingrediente banano = crearIngrediente("Banano", TipoIngrediente.FRUTA, 89d);

        Receta bowl = new Receta();
        bowl.nombre = "Bowl de pollo con arroz";
        bowl.descripcion = "Receta base para almuerzo de una persona";
        recetaRepository.persist(bowl);
        crearRecetaIngrediente(bowl, pollo, 1d, "porcion");
        crearRecetaIngrediente(bowl, arroz, 1d, "porcion");
        crearRecetaIngrediente(bowl, brocoli, 1d, "porcion");

        Receta desayuno = new Receta();
        desayuno.nombre = "Avena con banano";
        desayuno.descripcion = "Desayuno simple para una persona";
        recetaRepository.persist(desayuno);
        crearRecetaIngrediente(desayuno, avena, 1d, "porcion");
        crearRecetaIngrediente(desayuno, leche, 1d, "porcion");
        crearRecetaIngrediente(desayuno, banano, 1d, "unidad");

        inventarioService.registrarMovimiento(familia, pollo, TipoMovimientoInventario.COMPRA, 10d, null);
        inventarioService.registrarMovimiento(familia, arroz, TipoMovimientoInventario.COMPRA, 10d, null);
        inventarioService.registrarMovimiento(familia, brocoli, TipoMovimientoInventario.COMPRA, 10d, null);
        inventarioService.registrarMovimiento(familia, avena, TipoMovimientoInventario.COMPRA, 10d, null);
        inventarioService.registrarMovimiento(familia, leche, TipoMovimientoInventario.COMPRA, 10d, null);
        inventarioService.registrarMovimiento(familia, banano, TipoMovimientoInventario.COMPRA, 10d, null);

        PlanAlimentario plan = new PlanAlimentario();
        plan.familia = familia;
        plan.fechaInicio = LocalDate.now();
        plan.fechaFin = LocalDate.now().plusDays(6);
        planAlimentarioRepository.persist(plan);

        crearPlanComida(plan, LocalDate.now(), TipoMenu.DESAYUNO, 1, desayuno);
        crearPlanComida(plan, LocalDate.now(), TipoMenu.ALMUERZO, 1, bowl);
        crearPlanComida(plan, LocalDate.now().plusDays(1), TipoMenu.CENA, 1, bowl);
    }

    private void crearUsuario(String nombre, Familia familia) {
        Usuario usuario = new Usuario();
        usuario.nombre = nombre;
        usuario.familia = familia;
        usuarioRepository.persist(usuario);
    }

    private Ingrediente crearIngrediente(String nombre, TipoIngrediente tipo, double caloriasPorPorcion) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.nombre = nombre;
        ingrediente.tipo = tipo;
        ingrediente.caloriasPorPorcion = caloriasPorPorcion;
        ingredienteRepository.persist(ingrediente);
        return ingrediente;
    }

    private void crearRecetaIngrediente(Receta receta, Ingrediente ingrediente, double cantidad, String unidad) {
        RecetaIngrediente item = new RecetaIngrediente();
        item.receta = receta;
        item.ingrediente = ingrediente;
        item.cantidadBase = cantidad;
        item.unidad = unidad;
        recetaIngredienteRepository.persist(item);
    }

    private void crearPlanComida(PlanAlimentario plan, LocalDate fecha, TipoMenu tipoMenu, int orden, Receta receta) {
        PlanComida planComida = new PlanComida();
        planComida.plan = plan;
        planComida.fecha = fecha;
        planComida.tipoMenu = tipoMenu;
        planComida.orden = orden;
        planComida.receta = receta;
        planComida.confirmado = false;
        planComidaRepository.persist(planComida);
    }
}
