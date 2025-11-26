package com.tpi.logistica.servicio_contenedores.service;

import com.tpi.logistica.servicio_contenedores.dtos.ContenedorDTO;
import com.tpi.logistica.servicio_contenedores.dtos.EstadoContenedorDTO;
import com.tpi.logistica.servicio_contenedores.entities.Contenedor;
import com.tpi.logistica.servicio_contenedores.entities.EstadoContenedor;
import com.tpi.logistica.servicio_contenedores.repositories.ContenedorRepository;
import com.tpi.logistica.servicio_contenedores.repositories.EstadoContenedorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContenedorService {

    private final ContenedorRepository contRepo;
    private final EstadoContenedorRepository estadoRepo;

    public ContenedorService(ContenedorRepository contRepo, EstadoContenedorRepository estadoRepo) {
        this.contRepo = contRepo;
        this.estadoRepo = estadoRepo;
    }

    // CREATE
    @Transactional
    public ContenedorDTO crear(ContenedorDTO dto) {
        Contenedor c = fromDto(dto);
        // Si no vino estado, intento "DISPONIBLE"
        if (c.getEstadoId() == 0) {
            estadoRepo.findByNombre("DISPONIBLE").ifPresent(e -> c.setEstadoId(e.getId()));
        }
        return ContenedorDTO.toDto(contRepo.save(c));
    }

    // READ con filtros + paginación
    @Transactional(readOnly = true)
    public Page<ContenedorDTO> listar(Integer estadoId, Integer clienteId, Pageable pageable) {
        if (estadoId != null && clienteId != null)
            return contRepo.findByEstadoIdAndClienteId(estadoId, clienteId, pageable).map(ContenedorDTO::toDto);
        if (estadoId != null)
            return contRepo.findByEstadoId(estadoId, pageable).map(ContenedorDTO::toDto);
        if (clienteId != null)
            return contRepo.findByClienteId(clienteId, pageable).map(ContenedorDTO::toDto);
        return contRepo.findAll(pageable).map(ContenedorDTO::toDto);
    }

    // READ uno
    @Transactional(readOnly = true)
    public ContenedorDTO obtener(int id) {
        Contenedor c = contRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contenedor no encontrado: " + id));
        return ContenedorDTO.toDto(c);
    }

    // UPDATE (PUT)
    @Transactional
    public ContenedorDTO actualizar(int id, ContenedorDTO dto) {
        Contenedor c = contRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contenedor no encontrado: " + id));

        c.setNroIdentificacion(dto.getNroIdentificacion());
        c.setPeso(dto.getPeso());
        c.setVolumen(dto.getVolumen());
        c.setClienteId(dto.getClienteId());
        c.setUbicacionId(dto.getUbicacionId());
        if (dto.getEstadoId() != 0) c.setEstadoId(dto.getEstadoId());

        return ContenedorDTO.toDto(contRepo.save(c));
    }

    // PATCH estado (acepta id o nombre)
    @Transactional
    public ContenedorDTO cambiarEstado(int id, EstadoContenedorDTO dto) {
        Contenedor c = contRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contenedor no encontrado: " + id));

        Integer nuevoEstadoId = dto.getId();
        if ((nuevoEstadoId == null || nuevoEstadoId == 0) && dto.getNombre() != null) {
            EstadoContenedor e = estadoRepo.findByNombre(dto.getNombre())
                    .orElseThrow(() -> new IllegalArgumentException("Estado inexistente: " + dto.getNombre()));
            nuevoEstadoId = e.getId();
        }
        if (nuevoEstadoId == null || nuevoEstadoId == 0)
            throw new IllegalArgumentException("Debe indicar id o nombre de estado");

        c.setEstadoId(nuevoEstadoId);
        return ContenedorDTO.toDto(contRepo.save(c));
    }

    // DELETE
    @Transactional
    public void eliminar(int id) {
        contRepo.deleteById(id);
    }

    // ===== Mapping básico (DTO -> Entity)
    private Contenedor fromDto(ContenedorDTO dto) {
        Contenedor c = new Contenedor();
        c.setId(dto.getId());
        c.setNroIdentificacion(dto.getNroIdentificacion());
        c.setPeso(dto.getPeso());
        c.setVolumen(dto.getVolumen());
        c.setEstadoId(dto.getEstadoId());
        c.setUbicacionId(dto.getUbicacionId());
        c.setClienteId(dto.getClienteId());
        return c;
    }
}
