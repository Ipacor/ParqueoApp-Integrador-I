package com.parqueo.parkingApp.controller;

import com.parqueo.parkingApp.model.Sancion;
import com.parqueo.parkingApp.service.SancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/sanciones")
public class SancionController {

    @Autowired
    private SancionService sancionService;

    @GetMapping
    public List<Sancion> getAll() {
        return sancionService.findAll();
    }

    @GetMapping("/{id}")
    public Sancion getById(@PathVariable Long id) {
        return sancionService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sancion no encontrada"));
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<Sancion> getByUsuarioId(@PathVariable Long usuarioId) {
        return sancionService.findByUsuarioId(usuarioId);
    }

    @GetMapping("/estado/{estado}")
    public List<Sancion> getByEstado(@PathVariable String estado) {
        return sancionService.findByEstado(estado);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Sancion create(@RequestBody Sancion sancion) {
        return sancionService.save(sancion);
    }

    @PutMapping("/{id}")
    public Sancion update(@PathVariable Long id, @RequestBody Sancion sancion) {
        if (!sancionService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sancion no encontrada");
        }
        sancion.setId(id);
        return sancionService.save(sancion);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!sancionService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Sancion no encontrada");
        }
        sancionService.deleteById(id);
    }
}
