package com.tpi.logistica.servicio_contenedores.repositories;

import com.tpi.logistica.servicio_contenedores.entities.EstadoContenedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoContenedorRepository extends JpaRepository<EstadoContenedor, Integer> {
    Optional<EstadoContenedor> findByNombre(String nombre);
}
