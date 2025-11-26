package com.tpi.logistica.servicio_tarifas.services;

import com.tpi.logistica.servicio_tarifas.dtos.TarifaDTO;
import com.tpi.logistica.servicio_tarifas.entities.Tarifa;
import com.tpi.logistica.servicio_tarifas.repositories.TarifaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarifaService {

    private final TarifaRepository repo;

    public TarifaService(TarifaRepository repo) {
        this.repo = repo;
    }

    public List<TarifaDTO> getAll() {
        return repo.findAll().stream().map(TarifaDTO::toDto).toList();
    }

    public TarifaDTO getById(int id) {
        Tarifa t = repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa " + id + " no encontrada")
        );
        return TarifaDTO.toDto(t);
    }

    public TarifaDTO create(TarifaDTO dto) {
        Tarifa t = fromDto(dto);
        t.setId(0);
        Tarifa saved = repo.save(t);
        return TarifaDTO.toDto(saved);
    }

    public TarifaDTO update(int id, TarifaDTO dto) {
        Tarifa t = repo.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarifa " + id + " no encontrada")
        );

        t.setTipo(dto.getTipo());
        t.setDescripcion(dto.getDescripcion());
        t.setCostoBase(dto.getCostoBase());
        t.setCostoPorKm(dto.getCostoPorKm());
        t.setCostoPorKg(dto.getCostoPorKg());
        t.setCostoPorVolumen(dto.getCostoPorVolumen());
        t.setActiva(dto.isActiva());

        t.setFechaInicio(dto.getFechaInicio() == null ? null : LocalDateTime.parse(dto.getFechaInicio()));
        t.setFechaFin(dto.getFechaFin() == null ? null : LocalDateTime.parse(dto.getFechaFin()));

        return TarifaDTO.toDto(repo.save(t));
    }

    public void delete(int id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repo.deleteById(id);
    }

    private Tarifa fromDto(TarifaDTO dto) {
        Tarifa t = new Tarifa();
        t.setTipo(dto.getTipo());
        t.setDescripcion(dto.getDescripcion());
        t.setCostoBase(dto.getCostoBase());
        t.setCostoPorKm(dto.getCostoPorKm());
        t.setCostoPorKg(dto.getCostoPorKg());
        t.setCostoPorVolumen(dto.getCostoPorVolumen());
        t.setActiva(dto.isActiva());
        t.setFechaInicio(dto.getFechaInicio() == null ? null : LocalDateTime.parse(dto.getFechaInicio()));
        t.setFechaFin(dto.getFechaFin() == null ? null : LocalDateTime.parse(dto.getFechaFin()));
        return t;
    }
}
