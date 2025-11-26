package com.tpi.logistica.servicio_tarifas.repositories;

import com.tpi.logistica.servicio_tarifas.entities.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {
}
