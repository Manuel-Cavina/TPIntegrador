package com.tpi.logistica.servicio_tramos_rutas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.logistica.servicio_tramos_rutas.entities.TramosDeRuta;

public interface TramosDeRutaRepository extends JpaRepository<TramosDeRuta, Integer> {
    List<TramosDeRuta> findByRutaIdOrderByOrden(Integer rutaId);
}
