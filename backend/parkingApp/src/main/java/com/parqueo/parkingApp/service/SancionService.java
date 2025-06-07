package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.Sancion;

import java.util.List;
import java.util.Optional;

public interface SancionService {
    List<Sancion> listarTodas();
    Optional<Sancion> buscarPorId(Long id);
    List<Sancion> listarPorUsuarioId(Long usuarioId);
    Sancion guardar(Sancion sancion);
    Sancion actualizar(Long id, Sancion sancionActualizada);
    void eliminar(Long id);
}
