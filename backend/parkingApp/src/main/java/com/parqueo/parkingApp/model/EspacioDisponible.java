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

    private String movil;

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

    public String getMovil() {
        return movil;
    }

    public void setMovil(String movil) {
        this.movil = movil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public EspacioDisponible(Long id, String ubicacion, String movil, String estado, Set<Reserva> reservas) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.movil = movil;
        this.estado = estado;
        this.reservas = reservas;
    }
    public EspacioDisponible() {
        // Constructor por defecto
    }

    @Override
    public String toString() {
        return "EspacioDisponible [id=" + id + ", ubicacion=" + ubicacion + ", movil=" + movil + ", estado=" + estado
                + ", reservas=" + reservas + "]";
    }

}
