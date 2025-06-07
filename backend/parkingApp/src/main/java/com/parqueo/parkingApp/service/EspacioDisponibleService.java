package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.EspacioDisponible;

import java.util.List;

public interface EspacioDisponibleService {
    List<EspacioDisponible> listarEspacios();
    EspacioDisponible obtenerEspacioPorId(Long id);
    EspacioDisponible crearEspacio(EspacioDisponible espacio);
    EspacioDisponible actualizarEspacio(Long id, EspacioDisponible espacioActualizado);
    void eliminarEspacio(Long id);
}
