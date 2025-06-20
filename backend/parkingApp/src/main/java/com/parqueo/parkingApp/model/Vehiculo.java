package com.parqueo.parkingApp.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "vehiculos")
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;

    private String modelo;

    private String marca;

    private String color;

    private String tipo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    @JsonBackReference
    private Usuario usuario;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reserva> reservas;

      @OneToOne(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private ReglasEstacionamiento reglasEstacionamiento;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Sancion> sanciones;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Set<Reserva> getReservas() {
        return reservas;
    }

    public void setReservas(Set<Reserva> reservas) {
        this.reservas = reservas;
    }

    public ReglasEstacionamiento getReglasEstacionamiento() {
        return reglasEstacionamiento;
    }

    public void setReglasEstacionamiento(ReglasEstacionamiento reglasEstacionamiento) {
        this.reglasEstacionamiento = reglasEstacionamiento;
    }

    public Set<Sancion> getSanciones() {
        return sanciones;
    }

    public void setSanciones(Set<Sancion> sanciones) {
        this.sanciones = sanciones;
    }

    public Vehiculo() {
    }

    public Vehiculo(Long id, String placa, String modelo, String marca, String color, String tipo, Usuario usuario,
            Set<Reserva> reservas, ReglasEstacionamiento reglasEstacionamiento, Set<Sancion> sanciones) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.color = color;
        this.tipo = tipo;
        this.usuario = usuario;
        this.reservas = reservas;
        this.reglasEstacionamiento = reglasEstacionamiento;
        this.sanciones = sanciones;
    }

    @Override
    public String toString() {
        return "Vehiculo [id=" + id + ", placa=" + placa + ", modelo=" + modelo + ", marca=" + marca + ", color="
                + color + ", tipo=" + tipo + ", usuario=" + usuario + ", reservas=" + reservas
                + ", reglasEstacionamiento=" + reglasEstacionamiento + ", sanciones=" + sanciones + "]";
    }

}
