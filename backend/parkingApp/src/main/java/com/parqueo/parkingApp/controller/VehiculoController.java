package com.parqueo.parkingApp.controller;

import com.parqueo.parkingApp.model.Vehiculo;
import com.parqueo.parkingApp.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping
    public List<Vehiculo> getAll() {
        return vehiculoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> getById(@PathVariable Long id) {
        Optional<Vehiculo> vehiculo = vehiculoService.findById(id);
        return vehiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Vehiculo> getByUsuario(@PathVariable Long usuarioId) {
        return vehiculoService.findByUsuarioId(usuarioId);
    }

    @GetMapping("/placa/{placa}")
    public List<Vehiculo> getByPlaca(@PathVariable String placa) {
        return vehiculoService.findByPlaca(placa);
    }

    @PostMapping
    public Vehiculo create(@RequestBody Vehiculo vehiculo) {
        return vehiculoService.save(vehiculo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> update(@PathVariable Long id, @RequestBody Vehiculo vehiculo) {
        if (!vehiculoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vehiculo.setId(id);
        return ResponseEntity.ok(vehiculoService.save(vehiculo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!vehiculoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vehiculoService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
