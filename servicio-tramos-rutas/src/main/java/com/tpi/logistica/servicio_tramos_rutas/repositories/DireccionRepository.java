package com.tpi.logistica.servicio_tramos_rutas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.logistica.servicio_tramos_rutas.entities.Direccion;

public interface DireccionRepository extends JpaRepository<Direccion, Integer> {}
