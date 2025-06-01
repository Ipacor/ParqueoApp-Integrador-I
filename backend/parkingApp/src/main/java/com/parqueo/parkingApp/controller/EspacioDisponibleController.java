package com.parqueo.parkingApp.controller;

import com.parqueo.parkingApp.model.EspacioDisponible;
import com.parqueo.parkingApp.service.EspacioDisponibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/espacios")
public class EspacioDisponibleController {

    @Autowired
    private EspacioDisponibleService espacioDisponibleService;

    @GetMapping
    public List<EspacioDisponible> getAll() {
        return espacioDisponibleService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspacioDisponible> getById(@PathVariable Long id) {
        Optional<EspacioDisponible> espacio = espacioDisponibleService.findById(id);
        return espacio.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/estado/{estado}")
    public List<EspacioDisponible> getByEstado(@PathVariable String estado) {
        return espacioDisponibleService.findByEstado(estado);
    }

    @GetMapping("/tipo/{tipo}")
    public List<EspacioDisponible> getByTipo(@PathVariable String tipo) {
        return espacioDisponibleService.findByTipo(tipo);
    }

    @PostMapping
    public EspacioDisponible create(@RequestBody EspacioDisponible espacioDisponible) {
        return espacioDisponibleService.save(espacioDisponible);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspacioDisponible> update(@PathVariable Long id, @RequestBody EspacioDisponible espacioDisponible) {
        if (!espacioDisponibleService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        espacioDisponible.setId(id);
        return ResponseEntity.ok(espacioDisponibleService.save(espacioDisponible));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!espacioDisponibleService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        espacioDisponibleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
