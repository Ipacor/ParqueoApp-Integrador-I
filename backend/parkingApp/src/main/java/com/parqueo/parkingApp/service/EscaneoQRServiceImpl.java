package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.EscaneoQR;
import com.parqueo.parkingApp.repository.EscaneoQRRepository;
import com.parqueo.parkingApp.service.EscaneoQRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EscaneoQRServiceImpl implements EscaneoQRService {

    @Autowired
    private EscaneoQRRepository escaneoQRRepository;

    @Override
    public List<EscaneoQR> findAll() {
        return escaneoQRRepository.findAll();
    }

    @Override
    public Optional<EscaneoQR> findById(Long id) {
        return escaneoQRRepository.findById(id);
    }

    @Override
    public List<EscaneoQR> findByReservaId(Long reservaId) {
        return escaneoQRRepository.findByReservaId(reservaId);
    }

    @Override
    public EscaneoQR save(EscaneoQR escaneoQR) {
        return escaneoQRRepository.save(escaneoQR);
    }

    @Override
    public void deleteById(Long id) {
        escaneoQRRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return escaneoQRRepository.existsById(id);
    }
}
