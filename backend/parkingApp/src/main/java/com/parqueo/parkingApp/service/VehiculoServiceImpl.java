package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.Vehiculo;
import com.parqueo.parkingApp.repository.VehiculoRepository;
import com.parqueo.parkingApp.service.VehiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehiculoServiceImpl implements VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo obtenerVehiculoPorId(Long id) {
        return vehiculoRepository.findById(id).orElse(null);
    }

    @Override
    public Vehiculo crearVehiculo(Vehiculo vehiculo) {
        return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculoActualizado) {
        Vehiculo vehiculo = vehiculoRepository.findById(id).orElse(null);
        if (vehiculo != null) {
            vehiculo.setPlaca(vehiculoActualizado.getPlaca());
            vehiculo.setModelo(vehiculoActualizado.getModelo());
            vehiculo.setMarca(vehiculoActualizado.getMarca());
            vehiculo.setColor(vehiculoActualizado.getColor());
            vehiculo.setTipo(vehiculoActualizado.getTipo());
            vehiculo.setUsuario(vehiculoActualizado.getUsuario());
            return vehiculoRepository.save(vehiculo);
        }
        return null;
    }

    @Override
    public void eliminarVehiculo(Long id) {
        vehiculoRepository.deleteById(id);
    }
}