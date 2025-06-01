package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.HistorialUso;
import com.parqueo.parkingApp.repository.HistorialUsoRepository;
import com.parqueo.parkingApp.service.HistorialUsoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistorialUsoServiceImpl implements HistorialUsoService {

    @Autowired
    private HistorialUsoRepository historialUsoRepository;

    @Override
    public List<HistorialUso> findAll() {
        return historialUsoRepository.findAll();
    }

    @Override
    public Optional<HistorialUso> findById(Long id) {
        return historialUsoRepository.findById(id);
    }

    @Override
    public List<HistorialUso> findByUsuarioId(Long usuarioId) {
        return historialUsoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<HistorialUso> findByEspacioId(Long espacioId) {
        return historialUsoRepository.findByEspacioId(espacioId);
    }

    @Override
    public HistorialUso save(HistorialUso historialUso) {
        return historialUsoRepository.save(historialUso);
    }

    @Override
    public void deleteById(Long id) {
        historialUsoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return historialUsoRepository.existsById(id);
    }
}
