package com.tpi.logistica.servicio_tramos_rutas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.tpi.logistica.servicio_tramos_rutas.entities.TramosDeRuta;
import java.util.List;
@RepositoryRestResource(path = "tramos-de-ruta")
public interface TramosDeRutaRepository extends JpaRepository<TramosDeRuta, Integer> {

        List<TramosDeRuta> findByRutaIdOrderByOrden(Integer rutaId);

    
}