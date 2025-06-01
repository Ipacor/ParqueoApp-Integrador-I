package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.SeguridadSistema;

import java.util.List;
import java.util.Optional;

public interface SeguridadSistemaService {

    List<SeguridadSistema> findAll();

    Optional<SeguridadSistema> findById(Long id);

    Optional<SeguridadSistema> findByUsuarioId(Long usuarioId);

    SeguridadSistema save(SeguridadSistema seguridadSistema);

    void deleteById(Long id);

    boolean existsById(Long id);
}
