package com.parqueo.parkingApp.repository;

import com.parqueo.parkingApp.model.EspacioDisponible;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspacioDisponibleRepository extends JpaRepository<EspacioDisponible, Long> {

    List<EspacioDisponible> findByEstado(String estado);

    
}
