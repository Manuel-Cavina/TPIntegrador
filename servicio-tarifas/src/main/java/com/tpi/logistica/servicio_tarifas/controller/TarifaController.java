package com.tpi.logistica.servicio_tarifas.controller;

import com.tpi.logistica.servicio_tarifas.dtos.CotizacionDTO;
import com.tpi.logistica.servicio_tarifas.dtos.TarifaDTO;
import com.tpi.logistica.servicio_tarifas.entities.Tarifa;
import com.tpi.logistica.servicio_tarifas.repositories.CotizacionRepository;
import com.tpi.logistica.servicio_tarifas.repositories.TarifaRepository;
import com.tpi.logistica.servicio_tarifas.services.PricingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tarifas")
public class TarifaController {

    private final TarifaRepository tarifaRepo;
    private final CotizacionRepository cotizacionRepo;
    private final PricingService pricingService;

    // --- Healthcheck ---
    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok(Map.of("ok", true, "servicio", "tarifas"));
    }

    // --- Crear tarifa ---
    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody TarifaDTO dto){
        Tarifa t = new Tarifa();
        t.setTipo(dto.getTipo());
        t.setDescripcion(dto.getDescripcion());
        t.setCostoBase(dto.getCostoBase());
        t.setCostoPorKm(dto.getCostoPorKm());
        t.setCostoPorKg(dto.getCostoPorKg());
        t.setCostoPorVolumen(dto.getCostoPorVolumen());
        t.setActiva(dto.isActiva());
        t.setFechaInicio(LocalDateTime.now());
        tarifaRepo.save(t);
        return ResponseEntity.ok(TarifaDTO.toDto(t));
    }

    // --- Listar todas ---
    @GetMapping
    public List<TarifaDTO> todas(){
        return tarifaRepo.findAll().stream().map(TarifaDTO::toDto).toList();
    }

    // --- Buscar por ID ---
    @GetMapping("/{id}")
    public ResponseEntity<?> porId(@PathVariable int id){
        return tarifaRepo.findById(id)
                .map(t -> ResponseEntity.ok(TarifaDTO.toDto(t)))
                .orElse(ResponseEntity.status(404).body(
                        (TarifaDTO) Map.of("ok", false, "msg", "Tarifa no encontrada", "id", id)
                ));
    }

    // --- Buscar por tipo ---
    @GetMapping("/tipo/{tipo}")
    public List<TarifaDTO> porTipo(@PathVariable String tipo){
        return tarifaRepo.findByTipo(tipo).stream().map(TarifaDTO::toDto).toList();
    }

    // --- Tarifa activa por tipo ---
    @GetMapping("/tipo/{tipo}/activa")
    public ResponseEntity<?> activa(@PathVariable String tipo){
        Tarifa t = tarifaRepo.findFirstByTipoAndActivaTrueOrderByFechaInicioDesc(tipo);
        if (t == null) return ResponseEntity.status(404).body(
                Map.of("ok", false, "msg", "No hay tarifa activa para ese tipo", "tipo", tipo)
        );
        return ResponseEntity.ok(TarifaDTO.toDto(t));
    }

    // --- Activar ---
    @PutMapping("/{id}/activar")
    public ResponseEntity<?> activar(@PathVariable int id){
        return tarifaRepo.findById(id)
                .map(t -> {
                    t.setActiva(true);
                    t.setFechaInicio(LocalDateTime.now());
                    t.setFechaFin(null);
                    tarifaRepo.save(t);
                    return ResponseEntity.ok(TarifaDTO.toDto(t));
                })
                .orElse(ResponseEntity.status(404).body(
                        (TarifaDTO) Map.of("ok", false, "msg", "Tarifa no encontrada", "id", id)
                ));
    }

    // --- Desactivar ---
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<?> desactivar(@PathVariable int id){
        return tarifaRepo.findById(id)
                .map(t -> {
                    t.setActiva(false);
                    t.setFechaFin(LocalDateTime.now());
                    tarifaRepo.save(t);
                    return ResponseEntity.ok(TarifaDTO.toDto(t));
                })
                .orElse(ResponseEntity.status(404).body(
                        (TarifaDTO) Map.of("ok", false, "msg", "Tarifa no encontrada", "id", id)
                ));
    }

    // --- Cotizar ---
    @PostMapping("/cotizar")
    public ResponseEntity<?> cotizar(
            @RequestParam int solicitudId,
            @RequestParam double distanciaKm,
            @RequestParam double pesoKg,
            @RequestParam double volumenM3,
            @RequestParam String tipoTarifa) {

        try {
            CotizacionDTO dto = pricingService.cotizar(
                    solicitudId, distanciaKm, pesoKg, volumenM3, tipoTarifa
            );
            return ResponseEntity.ok(dto);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(Map.of("ok", false, "error", ex.getMessage()));
        }
    }

    // --- Listar cotizaciones ---
    @GetMapping("/cotizaciones")
    public List<CotizacionDTO> cotizaciones(){
        return cotizacionRepo.findAll().stream().map(CotizacionDTO::toDto).toList();
    }

    // --- Eliminar tarifa ---
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarTarifa(@PathVariable int id){
        return tarifaRepo.findById(id)
                .map(t -> {
                    tarifaRepo.delete(t);
                    return ResponseEntity.ok(Map.of("ok", true, "msg", "Tarifa eliminada", "id", id));
                })
                .orElse(ResponseEntity.status(404).body(
                        Map.of("ok", false, "msg", "Tarifa no encontrada", "id", id)
                ));
    }

    // --- Eliminar cotización ---
    @DeleteMapping("/cotizaciones/{id}")
    public ResponseEntity<?> eliminarCotizacion(@PathVariable int id){
        return cotizacionRepo.findById(id)
                .map(c -> {
                    cotizacionRepo.delete(c);
                    return ResponseEntity.ok(Map.of("ok", true, "msg", "Cotización eliminada", "id", id));
                })
                .orElse(ResponseEntity.status(404).body(
                        Map.of("ok", false, "msg", "Cotización no encontrada", "id", id)
                ));
    }
}
