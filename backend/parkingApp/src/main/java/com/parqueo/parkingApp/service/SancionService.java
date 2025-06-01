package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.Sancion;

import java.util.List;
import java.util.Optional;

public interface SancionService {

    List<Sancion> findAll();

    Optional<Sancion> findById(Long id);

    List<Sancion> findByUsuarioId(Long usuarioId);

    List<Sancion> findByEstado(String estado);

    Sancion save(Sancion sancion);

    void deleteById(Long id);

    boolean existsById(Long id);
}
