package com.tpi.logistica.servicio_camiones.repositories;

import com.tpi.logistica.servicio_camiones.entities.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamionRepository extends JpaRepository<Camion, Integer> {
}
