package com.tpi.logistica.servicio_tramos_rutas.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tpi.logistica.servicio_tramos_rutas.entities.Deposito;

public interface DepositoRepository extends JpaRepository<Deposito, Integer> {
    List<Deposito> findByActivoTrue();
}
