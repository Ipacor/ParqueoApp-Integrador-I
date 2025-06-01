package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {

    List<Reserva> findAll();

    Optional<Reserva> findById(Long id);

    List<Reserva> findByUsuarioId(Long usuarioId);

    List<Reserva> findByEstado(String estado);

    Reserva save(Reserva reserva);

    void deleteById(Long id);

    boolean existsById(Long id);
}
