package com.tpi.logistica.servicio_tarifas.repositories;

import com.tpi.logistica.servicio_tarifas.entities.Cotizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CotizacionRepository extends JpaRepository<Cotizacion, Integer> { }
