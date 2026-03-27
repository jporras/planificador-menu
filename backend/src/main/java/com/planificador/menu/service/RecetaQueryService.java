package com.planificador.menu.service;

import com.planificador.menu.api.dto.RecetaDetalleDto;
import com.planificador.menu.api.dto.RecetaIngredienteDto;
import com.planificador.menu.api.dto.RecetaResumenDto;
import com.planificador.menu.domain.receta.Receta;
import com.planificador.menu.domain.receta.RecetaIngrediente;
import com.planificador.menu.repository.receta.RecetaIngredienteRepository;
import com.planificador.menu.repository.receta.RecetaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

@ApplicationScoped
public class RecetaQueryService {

    @Inject
    RecetaRepository recetaRepository;

    @Inject
    RecetaIngredienteRepository recetaIngredienteRepository;

    public List<RecetaResumenDto> listRecetas() {
        return recetaRepository.listAll().stream()
                .map(this::toResumen)
                .toList();
    }

    public RecetaDetalleDto getDetalle(Long recetaId) {
        Receta receta = recetaRepository.findByIdOptional(recetaId)
                .orElseThrow(() -> new IllegalArgumentException("Receta no encontrada"));

        List<RecetaIngrediente> ingredientes = recetaIngredienteRepository.listByRecetaId(recetaId);
        List<RecetaIngredienteDto> detalleIngredientes = ingredientes.stream()
                .map(this::toIngredienteDto)
                .toList();

        return new RecetaDetalleDto(
                receta.id,
                receta.nombre,
                receta.descripcion,
                detalleIngredientes.stream().mapToDouble(RecetaIngredienteDto::caloriasTotales).sum(),
                detalleIngredientes
        );
    }

    private RecetaResumenDto toResumen(Receta receta) {
        double calorias = recetaIngredienteRepository.listByRecetaId(receta.id).stream()
                .mapToDouble(item -> item.cantidadBase * item.ingrediente.caloriasPorPorcion)
                .sum();
        return new RecetaResumenDto(receta.id, receta.nombre, receta.descripcion, calorias);
    }

    private RecetaIngredienteDto toIngredienteDto(RecetaIngrediente item) {
        double calorias = item.cantidadBase * item.ingrediente.caloriasPorPorcion;
        return new RecetaIngredienteDto(
                item.ingrediente.id,
                item.ingrediente.nombre,
                item.cantidadBase,
                item.unidad,
                item.ingrediente.caloriasPorPorcion,
                calorias
        );
    }
}

