package com.tpi.logistica.servicio_tramos_rutas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.logistica.servicio_tramos_rutas.entities.Ruta;

public interface RutaRepository extends JpaRepository<Ruta, Integer> {
    Optional<Ruta> findBySolicitudId(Integer solicitudId);
}
