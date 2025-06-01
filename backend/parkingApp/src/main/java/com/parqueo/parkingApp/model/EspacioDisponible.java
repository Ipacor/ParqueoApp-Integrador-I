package com.parqueo.parkingApp.model;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "espacios_disponibles")
public class EspacioDisponible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ubicacion;

    private String estado;

     // Relación con Reserva: 1 espacio puede tener muchas reservas
    @OneToMany(mappedBy = "espacio", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reserva> reservas;

    // Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public EspacioDisponible() {
    }

    public EspacioDisponible(Long id, String ubicacion, String estado) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EspacioDisponible{" +
                "id=" + id +
                ", ubicacion='" + ubicacion + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
