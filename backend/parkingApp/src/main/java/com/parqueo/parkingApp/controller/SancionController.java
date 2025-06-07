package com.parqueo.parkingApp.controller;

import com.parqueo.parkingApp.model.Sancion;
import com.parqueo.parkingApp.service.SancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sanciones")
public class SancionController {

    @Autowired
    private SancionService sancionService;

    @GetMapping
    public List<Sancion> obtenerTodas() {
        return sancionService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sancion> obtenerPorId(@PathVariable Long id) {
        return sancionService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Sancion> obtenerPorUsuario(@PathVariable Long usuarioId) {
        return sancionService.listarPorUsuarioId(usuarioId);
    }

    @PostMapping
    public Sancion crear(@RequestBody Sancion sancion) {
        return sancionService.guardar(sancion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sancion> actualizar(@PathVariable Long id, @RequestBody Sancion sancion) {
        try {
            return ResponseEntity.ok(sancionService.actualizar(id, sancion));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        sancionService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
