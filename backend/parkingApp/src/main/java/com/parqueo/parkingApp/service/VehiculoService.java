package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.Vehiculo;

import java.util.List;
import java.util.Optional;

public interface VehiculoService {

    List<Vehiculo> findAll();

    Optional<Vehiculo> findById(Long id);

    List<Vehiculo> findByUsuarioId(Long usuarioId);

    List<Vehiculo> findByPlaca(String placa);

    Vehiculo save(Vehiculo vehiculo);

    void deleteById(Long id);

    boolean existsById(Long id);
}
