package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.EscaneoQR;

import java.util.List;
import java.util.Optional;

public interface EscaneoQRService {

    List<EscaneoQR> findAll();

    Optional<EscaneoQR> findById(Long id);

    List<EscaneoQR> findByReservaId(Long reservaId);

    EscaneoQR save(EscaneoQR escaneoQR);

    void deleteById(Long id);

    boolean existsById(Long id);
}
