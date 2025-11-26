package com.tpi.logistica.servicio_usuarios.services;

import com.tpi.logistica.servicio_usuarios.dtos.UsuarioDTO;
import com.tpi.logistica.servicio_usuarios.entities.Usuario;
import com.tpi.logistica.servicio_usuarios.repositories.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> getAll() {
        return usuarioRepository.findAll().stream().map(UsuarioDTO::toDto).toList();
    }

    public UsuarioDTO getById(int id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        return UsuarioDTO.toDto(u);
    }

    public UsuarioDTO create(UsuarioDTO dto) {
        Usuario u = UsuarioDTO.toEntity(dto);
        u.setId(0);
        u.setFechaCreacion(LocalDateTime.now());
        Usuario saved = usuarioRepository.save(u);
        return UsuarioDTO.toDto(saved);
    }

    public UsuarioDTO update(int id, UsuarioDTO dto) {
        Usuario existing = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado"));
        existing.setNombre(dto.getNombre());
        existing.setApellido(dto.getApellido());
        existing.setEmail(dto.getEmail());
        existing.setTelefono(dto.getTelefono());
        existing.setRol(dto.getRol());
        existing.setActivo(dto.isActivo());
        existing.setFechaActualizacion(LocalDateTime.now());
        Usuario updated = usuarioRepository.save(existing);
        return UsuarioDTO.toDto(updated);
    }

    public void delete(int id) {
        if (!usuarioRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }
}
