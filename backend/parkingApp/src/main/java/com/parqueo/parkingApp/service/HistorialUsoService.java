package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.HistorialUso;

import java.util.List;
import java.util.Optional;

public interface HistorialUsoService {

    List<HistorialUso> findAll();

    Optional<HistorialUso> findById(Long id);

    List<HistorialUso> findByUsuarioId(Long usuarioId);

    List<HistorialUso> findByEspacioId(Long espacioId);

    HistorialUso save(HistorialUso historialUso);

    void deleteById(Long id);

    boolean existsById(Long id);
}
