package com.tpi.logistica.servicio_tramos_rutas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.tpi.logistica.servicio_tramos_rutas.entities.Direccion;

@RepositoryRestResource(path = "direcciones")
public interface DireccionRepository extends JpaRepository<Direccion, Integer> {
}

