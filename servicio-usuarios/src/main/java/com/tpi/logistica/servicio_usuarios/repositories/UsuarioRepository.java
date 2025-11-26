package com.tpi.logistica.servicio_usuarios.repositories;

import com.tpi.logistica.servicio_usuarios.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
