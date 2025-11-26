package com.tpi.logistica.servicio_usuarios.dtos;

import com.tpi.logistica.servicio_usuarios.entities.Usuario;
import lombok.Data;

@Data
public class UsuarioDTO {
    private int id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String rol;
    private boolean activo;
    private String fechaCreacion;
    private String fechaActualizacion;

    public static UsuarioDTO toDto(Usuario u) {
        if (u == null) return null;
        UsuarioDTO d = new UsuarioDTO();
        d.id = u.getId();
        d.nombre = u.getNombre();
        d.apellido = u.getApellido();
        d.email = u.getEmail();
        d.telefono = u.getTelefono();
        d.rol = u.getRol();
        d.activo = u.isActivo();
        d.fechaCreacion = u.getFechaCreacion() != null ? u.getFechaCreacion().toString() : null;
        d.fechaActualizacion = u.getFechaActualizacion() != null ? u.getFechaActualizacion().toString() : null;
        return d;
    }

    public static Usuario toEntity(UsuarioDTO d) {
        if (d == null) return null;
        Usuario u = new Usuario();
        u.setId(d.getId());
        u.setNombre(d.getNombre());
        u.setApellido(d.getApellido());
        u.setEmail(d.getEmail());
        u.setTelefono(d.getTelefono());
        u.setRol(d.getRol());
        u.setActivo(d.isActivo());
        return u;
    }
}
