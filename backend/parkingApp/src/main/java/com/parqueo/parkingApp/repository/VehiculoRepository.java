package com.parqueo.parkingApp.repository;

import com.parqueo.parkingApp.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

    List<Vehiculo> findByUsuarioId(Long usuarioId);

    List<Vehiculo> findByPlaca(String placa);
}
