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

    public UsuarioDTO(int id, String nombre, String apellido, String email,
                      String telefono, String rol, boolean activo,
                      String fechaCreacion, String fechaActualizacion) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.rol = rol;
        this.activo = activo;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public static UsuarioDTO toDto(Usuario u) {
        return new UsuarioDTO(
                u.getId(),
                u.getNombre(),
                u.getApellido(),
                u.getEmail(),
                u.getTelefono(),
                u.getRol(),
                u.isActivo(),
                u.getFechaCreacion() != null ? u.getFechaCreacion().toString() : null,
                u.getFechaActualizacion() != null ? u.getFechaActualizacion().toString() : null
        );
    }
}
