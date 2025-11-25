package com.tpi.logistica.servicio_solicitudes.services;

import com.tpi.logistica.servicio_solicitudes.dtos.SolicitudDTO;
import com.tpi.logistica.servicio_solicitudes.entities.Solicitud;
import com.tpi.logistica.servicio_solicitudes.repositories.SolicitudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitudService {

    private final SolicitudRepository solicitudRepository;

    public SolicitudService(SolicitudRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }

    public List<SolicitudDTO> getAll() {
        return solicitudRepository.findAll()
                .stream()
                .map(SolicitudDTO::toDto)
                .toList();
    }

    public SolicitudDTO getById(int id) {
        Solicitud s = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró la solicitud con id " + id
                ));
        return SolicitudDTO.toDto(s);
    }

    public SolicitudDTO create(SolicitudDTO dto) {
        Solicitud s = fromDto(dto);
        s.setId(0);
        Solicitud guardada = solicitudRepository.save(s);
        return SolicitudDTO.toDto(guardada);
    }

    public SolicitudDTO update(int id, SolicitudDTO dto) {
        Solicitud existente = solicitudRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No se encontró la solicitud con id " + id
                ));

        existente.setNroSolicitud(dto.getNroSolicitud());
        existente.setContenedorId(dto.getContenedorId());
        existente.setClienteId(dto.getClienteId());
        existente.setCostoEstimado(dto.getCostoEstimado());

        if (dto.getTiempoEstimado() != null) {
            existente.setTiempoEstimado(LocalDateTime.parse(dto.getTiempoEstimado()));
        } else {
            existente.setTiempoEstimado(null);
        }

        existente.setCostoFinal(dto.getCostoFinal());

        if (dto.getTiempoReal() != null) {
            existente.setTiempoReal(LocalDateTime.parse(dto.getTiempoReal()));
        } else {
            existente.setTiempoReal(null);
        }

        existente.setEstadoId(dto.getEstadoId());

        Solicitud actualizada = solicitudRepository.save(existente);
        return SolicitudDTO.toDto(actualizada);
    }

    public void delete(int id) {
        if (!solicitudRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se encontró la solicitud con id " + id
            );
        }
        solicitudRepository.deleteById(id);
    }


    private Solicitud fromDto(SolicitudDTO dto) {
        Solicitud s = new Solicitud();
        s.setNroSolicitud(dto.getNroSolicitud());
        s.setContenedorId(dto.getContenedorId());
        s.setClienteId(dto.getClienteId());
        s.setCostoEstimado(dto.getCostoEstimado());

        if (dto.getTiempoEstimado() != null) {
            s.setTiempoEstimado(LocalDateTime.parse(dto.getTiempoEstimado()));
        }

        s.setCostoFinal(dto.getCostoFinal());

        if (dto.getTiempoReal() != null) {
            s.setTiempoReal(LocalDateTime.parse(dto.getTiempoReal()));
        }

        s.setEstadoId(dto.getEstadoId());

        return s;
    }
}
