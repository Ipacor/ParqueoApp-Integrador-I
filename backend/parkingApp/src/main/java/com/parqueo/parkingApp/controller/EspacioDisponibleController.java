package com.parqueo.parkingApp.controller;

import com.parqueo.parkingApp.model.EspacioDisponible;
import com.parqueo.parkingApp.service.EspacioDisponibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/espacios")
@CrossOrigin("*")
public class EspacioDisponibleController {

    @Autowired
    private EspacioDisponibleService espacioService;

    @GetMapping
    public List<EspacioDisponible> listarEspacios() {
        return espacioService.listarEspacios();
    }

    @GetMapping("/{id}")
    public EspacioDisponible obtenerEspacio(@PathVariable Long id) {
        return espacioService.obtenerEspacioPorId(id);
    }

    @PostMapping
    public EspacioDisponible crearEspacio(@RequestBody EspacioDisponible espacio) {
        return espacioService.crearEspacio(espacio);
    }

    @PutMapping("/{id}")
    public EspacioDisponible actualizarEspacio(@PathVariable Long id, @RequestBody EspacioDisponible espacio) {
        return espacioService.actualizarEspacio(id, espacio);
    }

    @DeleteMapping("/{id}")
    public void eliminarEspacio(@PathVariable Long id) {
        espacioService.eliminarEspacio(id);
    }
}
