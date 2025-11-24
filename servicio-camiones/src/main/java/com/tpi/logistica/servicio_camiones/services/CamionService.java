package com.tpi.logistica.servicio_camiones.services;

import com.tpi.logistica.servicio_camiones.dtos.CamionDTO;
import com.tpi.logistica.servicio_camiones.entities.Camion;
import com.tpi.logistica.servicio_camiones.repositories.CamionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CamionService {

    private final CamionRepository camionRepository;

    public CamionService(CamionRepository camionRepository) {
        this.camionRepository = camionRepository;
    }

    public List<CamionDTO> getAll() {
        return camionRepository.findAll()
                .stream()
                .map(CamionDTO::toDto)
                .toList();
    }

    public CamionDTO getById(int id) {
        Camion camion = camionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró el camión con id " + id
                ));
        return CamionDTO.toDto(camion);
    }

    public CamionDTO create(CamionDTO dto) {
        Camion camion = fromDto(dto);
        //por si mandan id en el body
        camion.setId(0);
        Camion guardado = camionRepository.save(camion);
        return CamionDTO.toDto(guardado);
    }

    public CamionDTO update(int id, CamionDTO dto) {
        Camion existente = camionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró el camión con id " + id
                ));

        existente.setDominio(dto.getDominio());
        existente.setNombreTransportista(dto.getNombreTransportista());
        existente.setTelefono(dto.getTelefono());
        existente.setCapacidadPeso(dto.getCapacidadPeso());
        existente.setCapacidadVolumen(dto.getCapacidadVolumen());
        existente.setConsumoKm(dto.getConsumoKm());
        existente.setCostoKm(dto.getCostoKm());
        existente.setEstadoId(dto.getEstadoId());

        Camion actualizado = camionRepository.save(existente);
        return CamionDTO.toDto(actualizado);
    }

    public void delete(int id) {
        if (!camionRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró el camión con id " + id
            );
        }
        camionRepository.deleteById(id);
    }


    private Camion fromDto(CamionDTO dto) {
        Camion camion = new Camion();
        camion.setDominio(dto.getDominio());
        camion.setNombreTransportista(dto.getNombreTransportista());
        camion.setTelefono(dto.getTelefono());
        camion.setCapacidadPeso(dto.getCapacidadPeso());
        camion.setCapacidadVolumen(dto.getCapacidadVolumen());
        camion.setConsumoKm(dto.getConsumoKm());
        camion.setCostoKm(dto.getCostoKm());
        camion.setEstadoId(dto.getEstadoId());
        return camion;
    }
}
