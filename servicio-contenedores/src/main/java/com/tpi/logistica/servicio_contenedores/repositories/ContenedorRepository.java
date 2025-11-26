package com.tpi.logistica.servicio_contenedores.repositories;

import com.tpi.logistica.servicio_contenedores.entities.Contenedor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenedorRepository extends JpaRepository<Contenedor, Integer> {
    Page<Contenedor> findByEstadoId(Integer estadoId, Pageable pageable);
    Page<Contenedor> findByClienteId(Integer clienteId, Pageable pageable);
    Page<Contenedor> findByEstadoIdAndClienteId(Integer estadoId, Integer clienteId, Pageable pageable);
}
