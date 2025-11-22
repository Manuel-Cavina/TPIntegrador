package com.tpi.logistica.servicio_tramos_rutas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.logistica.servicio_tramos_rutas.entities.Tramo;

public interface TramoRepository extends JpaRepository<Tramo, Integer> {

    List<Tramo> findByEstadoId(Integer estadoId);

    List<Tramo> findBySolicitudId(Integer solicitudId);
}
