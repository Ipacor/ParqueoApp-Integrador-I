package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.Reserva;
import com.parqueo.parkingApp.repository.ReservaRepository;
import com.parqueo.parkingApp.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepository.findAll();
    }

    @Override
    public Reserva obtenerReservaPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada con ID: " + id));
    }

    @Override
    public Reserva crearReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    public Reserva actualizarReserva(Long id, Reserva reservaActualizada) {
        Reserva reservaExistente = obtenerReservaPorId(id);
        reservaExistente.setFechaHoraInicio(reservaActualizada.getFechaHoraInicio());
        reservaExistente.setFechaHoraFin(reservaActualizada.getFechaHoraFin());
        reservaExistente.setEstado(reservaActualizada.getEstado());
        // puedes actualizar también vehículo y usuario si lo deseas
        return reservaRepository.save(reservaExistente);
    }

    @Override
    public void eliminarReserva(Long id) {
        reservaRepository.deleteById(id);
    }

    @Override
    public List<Reserva> listarReservasPorUsuario(Long idUsuario) {
        return reservaRepository.findByUsuarioId(idUsuario);
    }
}
