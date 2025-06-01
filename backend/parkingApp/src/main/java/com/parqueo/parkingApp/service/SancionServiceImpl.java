package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.Sancion;
import com.parqueo.parkingApp.repository.SancionRepository;
import com.parqueo.parkingApp.service.SancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SancionServiceImpl implements SancionService {

    @Autowired
    private SancionRepository sancionRepository;

    @Override
    public List<Sancion> findAll() {
        return sancionRepository.findAll();
    }

    @Override
    public Optional<Sancion> findById(Long id) {
        return sancionRepository.findById(id);
    }

    @Override
    public List<Sancion> findByUsuarioId(Long usuarioId) {
        return sancionRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Sancion> findByEstado(String estado) {
        return sancionRepository.findByEstado(estado);
    }

    @Override
    public Sancion save(Sancion sancion) {
        return sancionRepository.save(sancion);
    }

    @Override
    public void deleteById(Long id) {
        sancionRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return sancionRepository.existsById(id);
    }
}
