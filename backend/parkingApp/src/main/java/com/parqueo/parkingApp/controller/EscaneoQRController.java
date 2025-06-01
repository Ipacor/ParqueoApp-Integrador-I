package com.parqueo.parkingApp.controller;

import com.parqueo.parkingApp.model.EscaneoQR;
import com.parqueo.parkingApp.service.EscaneoQRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/escaneos")
public class EscaneoQRController {

    @Autowired
    private EscaneoQRService escaneoQRService;

    @GetMapping
    public List<EscaneoQR> getAll() {
        return escaneoQRService.findAll();
    }

    @GetMapping("/{id}")
    public EscaneoQR getById(@PathVariable Long id) {
        return escaneoQRService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Escaneo no encontrado"));
    }

    @GetMapping("/reserva/{reservaId}")
    public List<EscaneoQR> getByReservaId(@PathVariable Long reservaId) {
        return escaneoQRService.findByReservaId(reservaId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EscaneoQR create(@RequestBody EscaneoQR escaneoQR) {
        return escaneoQRService.save(escaneoQR);
    }

    @PutMapping("/{id}")
    public EscaneoQR update(@PathVariable Long id, @RequestBody EscaneoQR escaneoQR) {
        if (!escaneoQRService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Escaneo no encontrado");
        }
        escaneoQR.setId(id);
        return escaneoQRService.save(escaneoQR);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        if (!escaneoQRService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Escaneo no encontrado");
        }
        escaneoQRService.deleteById(id);
    }
}
