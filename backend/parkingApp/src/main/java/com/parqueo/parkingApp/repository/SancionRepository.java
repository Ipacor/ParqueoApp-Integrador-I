package com.parqueo.parkingApp.repository;

import com.parqueo.parkingApp.model.Sancion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SancionRepository extends JpaRepository<Sancion, Long> {
    List<Sancion> findByUsuarioId(Long usuarioId); // Para buscar por usuario
}
