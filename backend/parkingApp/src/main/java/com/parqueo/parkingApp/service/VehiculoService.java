package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.Vehiculo;

import java.util.List;

public interface VehiculoService {
    List<Vehiculo> listarVehiculos();
    Vehiculo obtenerVehiculoPorId(Long id);
    Vehiculo crearVehiculo(Vehiculo vehiculo);
    Vehiculo actualizarVehiculo(Long id, Vehiculo vehiculo);
    void eliminarVehiculo(Long id);
}
