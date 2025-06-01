package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.EspacioDisponible;
import com.parqueo.parkingApp.repository.EspacioDisponibleRepository;
import com.parqueo.parkingApp.service.EspacioDisponibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspacioDisponibleServiceImpl implements EspacioDisponibleService {

    @Autowired
    private EspacioDisponibleRepository espacioDisponibleRepository;

    @Override
    public List<EspacioDisponible> findAll() {
        return espacioDisponibleRepository.findAll();
    }

    @Override
    public Optional<EspacioDisponible> findById(Long id) {
        return espacioDisponibleRepository.findById(id);
    }

    @Override
    public List<EspacioDisponible> findByEstado(String estado) {
        return espacioDisponibleRepository.findByEstado(estado);
    }



    @Override
    public EspacioDisponible save(EspacioDisponible espacioDisponible) {
        return espacioDisponibleRepository.save(espacioDisponible);
    }

    @Override
    public void deleteById(Long id) {
        espacioDisponibleRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return espacioDisponibleRepository.existsById(id);
    }

    @Override
    public List<EspacioDisponible> findByTipo(String tipo) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByTipo'");
    }
}
