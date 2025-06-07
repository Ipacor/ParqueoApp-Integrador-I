package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.Sancion;
import com.parqueo.parkingApp.repository.SancionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SancionServiceImpl implements SancionService {

    @Autowired
    private SancionRepository sancionRepository;

    @Override
    public List<Sancion> listarTodas() {
        return sancionRepository.findAll();
    }

    @Override
    public Optional<Sancion> buscarPorId(Long id) {
        return sancionRepository.findById(id);
    }

    @Override
    public List<Sancion> listarPorUsuarioId(Long usuarioId) {
        return sancionRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public Sancion guardar(Sancion sancion) {
        return sancionRepository.save(sancion);
    }

    @Override
    public Sancion actualizar(Long id, Sancion sancionActualizada) {
        return sancionRepository.findById(id).map(s -> {
            s.setMotivo(sancionActualizada.getMotivo());
            s.setEstado(sancionActualizada.getEstado());
            return sancionRepository.save(s);
        }).orElseThrow(() -> new RuntimeException("Sanción no encontrada"));
    }

    @Override
    public void eliminar(Long id) {
        sancionRepository.deleteById(id);
    }
}

