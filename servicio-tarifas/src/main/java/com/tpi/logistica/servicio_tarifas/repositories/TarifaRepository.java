package com.tpi.logistica.servicio_tarifas.repositories;

import com.tpi.logistica.servicio_tarifas.entities.Tarifa;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TarifaRepository extends JpaRepository<Tarifa, Integer> {
    List<Tarifa> findByTipo(String tipo);
    Tarifa findFirstByTipoAndActivaTrueOrderByFechaInicioDesc(String tipo);
}
