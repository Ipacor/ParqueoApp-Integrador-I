package com.parqueo.parkingApp.controller;

import com.parqueo.parkingApp.model.Reserva;
import com.parqueo.parkingApp.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> getAll() {
        return reservaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> getById(@PathVariable Long id) {
        Optional<Reserva> reserva = reservaService.findById(id);
        return reserva.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Reserva> getByUsuario(@PathVariable Long usuarioId) {
        return reservaService.findByUsuarioId(usuarioId);
    }

    @GetMapping("/estado/{estado}")
    public List<Reserva> getByEstado(@PathVariable String estado) {
        return reservaService.findByEstado(estado);
    }

    @PostMapping
    public Reserva create(@RequestBody Reserva reserva) {
        return reservaService.save(reserva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id, @RequestBody Reserva reserva) {
        if (!reservaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reserva.setId(id);
        return ResponseEntity.ok(reservaService.save(reserva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!reservaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        reservaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
