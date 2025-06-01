package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.SeguridadSistema;
import com.parqueo.parkingApp.repository.SeguridadSistemaRepository;
import com.parqueo.parkingApp.service.SeguridadSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeguridadSistemaServiceImpl implements SeguridadSistemaService {

    @Autowired
    private SeguridadSistemaRepository seguridadSistemaRepository;

    @Override
    public List<SeguridadSistema> findAll() {
        return seguridadSistemaRepository.findAll();
    }

    @Override
    public Optional<SeguridadSistema> findById(Long id) {
        return seguridadSistemaRepository.findById(id);
    }

    @Override
    public Optional<SeguridadSistema> findByUsuarioId(Long usuarioId) {
        return seguridadSistemaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public SeguridadSistema save(SeguridadSistema seguridadSistema) {
        return seguridadSistemaRepository.save(seguridadSistema);
    }

    @Override
    public void deleteById(Long id) {
        seguridadSistemaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return seguridadSistemaRepository.existsById(id);
    }
}
