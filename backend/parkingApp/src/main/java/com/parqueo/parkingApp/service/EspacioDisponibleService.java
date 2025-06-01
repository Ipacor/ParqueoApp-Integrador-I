package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.EspacioDisponible;

import java.util.List;
import java.util.Optional;

public interface EspacioDisponibleService {

    List<EspacioDisponible> findAll();

    Optional<EspacioDisponible> findById(Long id);

    List<EspacioDisponible> findByEstado(String estado);

    List<EspacioDisponible> findByTipo(String tipo);

    EspacioDisponible save(EspacioDisponible espacioDisponible);

    void deleteById(Long id);

    boolean existsById(Long id);
}
