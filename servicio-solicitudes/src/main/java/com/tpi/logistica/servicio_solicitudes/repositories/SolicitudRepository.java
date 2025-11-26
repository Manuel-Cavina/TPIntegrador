package com.tpi.logistica.servicio_solicitudes.repositories;

import com.tpi.logistica.servicio_solicitudes.entities.Solicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudRepository extends JpaRepository<Solicitud, Integer> {
}
