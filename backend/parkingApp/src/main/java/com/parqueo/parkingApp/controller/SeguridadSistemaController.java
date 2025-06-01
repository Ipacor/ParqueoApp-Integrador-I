package com.parqueo.parkingApp.controller;

import com.parqueo.parkingApp.model.SeguridadSistema;
import com.parqueo.parkingApp.service.SeguridadSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/seguridad")
public class SeguridadSistemaController {

    @Autowired
    private SeguridadSistemaService seguridadSistemaService;

    @GetMapping
    public List<SeguridadSistema> getAll() {
        return seguridadSistemaService.findAll();
    }

    @GetMapping("/{id}")
    public SeguridadSistema getById(@PathVariable Long id) {
        return seguridadSistemaService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seguridad no encontrada"));
    }

    @GetMapping("/usuario/{usuarioId}")
    public SeguridadSistema getByUsuarioId(@PathVariable Long usuarioId) {
        return seguridadSistemaService.findByUsuarioId(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Seguridad para usuario no encontrada"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeguridadSistema create(@RequestBody SeguridadSistema seguridadSistema) {
        return seguridadSistemaService.save(seguridadSistema);
    }

    @PutMapping("/{id}")
    public SeguridadSistema update(@PathVariable Long id, @RequestBody SeguridadSistema seguridadSistema) {
        if (!seguridadSistemaService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Seguridad no encontrada");
        }
        seguridadSistema.setId(id);
        return seguridadSistemaService.save(seguridadSistema);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!seguridadSistemaService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Seguridad no encontrada");
        }
        seguridadSistemaService.deleteById(id);
    }
}
