package com.parqueo.parkingApp.repository;

import com.parqueo.parkingApp.model.Sancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SancionRepository extends JpaRepository<Sancion, Long> {

    List<Sancion> findByUsuarioId(Long usuarioId);

    List<Sancion> findByEstado(String estado);
}
