package com.tpi.logistica.servicio_tramos_rutas.config;

import com.tpi.logistica.servicio_tramos_rutas.entities.EstadoTramo;
import com.tpi.logistica.servicio_tramos_rutas.entities.TipoTramo;
import com.tpi.logistica.servicio_tramos_rutas.repositories.EstadoTramoRepository;
import com.tpi.logistica.servicio_tramos_rutas.repositories.TipoTramoRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {

    private static final Logger LOG = LoggerFactory.getLogger(DataLoader.class);

    private final EstadoTramoRepository estadoRepo;
    private final TipoTramoRepository tipoRepo;

    @PostConstruct
    public void init() {
        cargarEstadosTramo();
        cargarTiposTramo();
    }

    private void cargarEstadosTramo() {
        if (estadoRepo.count() == 0) {
            LOG.info("📌 Cargando Estados de Tramo...");

            estadoRepo.save(new EstadoTramo(null, "pendiente"));
            estadoRepo.save(new EstadoTramo(null, "asignado"));
            estadoRepo.save(new EstadoTramo(null, "iniciado"));
            estadoRepo.save(new EstadoTramo(null, "finalizado"));
        }
    }

    private void cargarTiposTramo() {
        if (tipoRepo.count() == 0) {
            LOG.info("📌 Cargando Tipos de Tramo...");

            tipoRepo.save(new TipoTramo(null, "origen-deposito"));
            tipoRepo.save(new TipoTramo(null, "deposito-deposito"));
            tipoRepo.save(new TipoTramo(null, "deposito-destino"));
            tipoRepo.save(new TipoTramo(null, "origen-destino"));
        }
    }
}
