package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.Vehiculo;
import com.parqueo.parkingApp.repository.VehiculoRepository;
import com.parqueo.parkingApp.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> findAll() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Optional<Vehiculo> findById(Long id) {
        return vehiculoRepository.findById(id);
    }

    @Override
    public List<Vehiculo> findByUsuarioId(Long usuarioId) {
        return vehiculoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    public List<Vehiculo> findByPlaca(String placa) {
        return vehiculoRepository.findByPlaca(placa);
    }

    @Override
    public Vehiculo save(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public void deleteById(Long id) {
        vehiculoRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return vehiculoRepository.existsById(id);
    }
}
