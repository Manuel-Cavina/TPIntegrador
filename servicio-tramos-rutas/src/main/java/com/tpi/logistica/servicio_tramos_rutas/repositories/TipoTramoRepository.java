package com.tpi.logistica.servicio_tramos_rutas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.logistica.servicio_tramos_rutas.entities.TipoTramo;

public interface TipoTramoRepository extends JpaRepository<TipoTramo, Integer> {
}
