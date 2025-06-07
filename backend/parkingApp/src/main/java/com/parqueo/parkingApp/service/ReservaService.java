package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.Reserva;

import java.util.List;
import java.util.Optional;

public interface ReservaService {

    List<Reserva> listarReservas();

    Reserva obtenerReservaPorId(Long id);

    Reserva crearReserva(Reserva reserva);

    Reserva actualizarReserva(Long id, Reserva reservaActualizada);

    void eliminarReserva(Long id);

    List<Reserva> listarReservasPorUsuario(Long idUsuario);
}

