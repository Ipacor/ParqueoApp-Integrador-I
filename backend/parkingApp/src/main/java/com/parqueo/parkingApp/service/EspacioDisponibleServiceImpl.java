package com.parqueo.parkingApp.service;

import com.parqueo.parkingApp.model.EspacioDisponible;
import com.parqueo.parkingApp.repository.EspacioDisponibleRepository;
import com.parqueo.parkingApp.service.EspacioDisponibleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspacioDisponibleServiceImpl implements EspacioDisponibleService {

    @Autowired
    private EspacioDisponibleRepository espacioRepository;

    @Override
    public List<EspacioDisponible> listarEspacios() {
        return espacioRepository.findAll();
    }

    @Override
    public EspacioDisponible obtenerEspacioPorId(Long id) {
        return espacioRepository.findById(id).orElse(null);
    }

    @Override
    public EspacioDisponible crearEspacio(EspacioDisponible espacio) {
        return espacioRepository.save(espacio);
    }

    @Override
    public EspacioDisponible actualizarEspacio(Long id, EspacioDisponible espacioActualizado) {
        EspacioDisponible espacio = espacioRepository.findById(id).orElse(null);
        if (espacio != null) {
            espacio.setUbicacion(espacioActualizado.getUbicacion());
            espacio.setEstado(espacioActualizado.getEstado());
            return espacioRepository.save(espacio);
        }
        return null;
    }

    @Override
    public void eliminarEspacio(Long id) {
        espacioRepository.deleteById(id);
    }
}
