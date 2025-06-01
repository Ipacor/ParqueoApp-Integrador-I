package com.parqueo.parkingApp.controller;

import com.parqueo.parkingApp.model.HistorialUso;
import com.parqueo.parkingApp.service.HistorialUsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialUsoController {

    @Autowired
    private HistorialUsoService historialUsoService;

    @GetMapping
    public List<HistorialUso> getAll() {
        return historialUsoService.findAll();
    }

    @GetMapping("/{id}")
    public HistorialUso getById(@PathVariable Long id) {
        return historialUsoService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Historial no encontrado"));
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<HistorialUso> getByUsuarioId(@PathVariable Long usuarioId) {
        return historialUsoService.findByUsuarioId(usuarioId);
    }

    @GetMapping("/espacio/{espacioId}")
    public List<HistorialUso> getByEspacioId(@PathVariable Long espacioId) {
        return historialUsoService.findByEspacioId(espacioId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HistorialUso create(@RequestBody HistorialUso historialUso) {
        return historialUsoService.save(historialUso);
    }

    @PutMapping("/{id}")
    public HistorialUso update(@PathVariable Long id, @RequestBody HistorialUso historialUso) {
        if (!historialUsoService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Historial no encontrado");
        }
        historialUso.setId(id);
        return historialUsoService.save(historialUso);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!historialUsoService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Historial no encontrado");
        }
        historialUsoService.deleteById(id);
    }
}
