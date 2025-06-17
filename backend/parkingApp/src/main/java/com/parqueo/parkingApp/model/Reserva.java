package com.parqueo.parkingApp.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Muchos a uno con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    // Muchos a uno con Vehiculo
    @ManyToOne
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    // Muchos a uno con Espacio
    @ManyToOne
    @JoinColumn(name = "id_espacio", nullable = false)
    private EspacioDisponible espacio;

    // Uno a uno con Escaneo QR (bidireccional)
    @OneToOne(mappedBy = "reserva", cascade = CascadeType.ALL)
    private EscaneoQR escaneoQR;

    @Column(name = "fecha_hora_inicio", nullable = false)
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_fin", nullable = false)
    private LocalDateTime fechaHoraFin;

    @Column(length = 20, nullable = false)
    private String estado;

    // === Constructores ===

    public Reserva() {
        // Constructor vacío requerido por JPA
    }

    public Reserva(Long id, Usuario usuario, Vehiculo vehiculo, EspacioDisponible espacio, EscaneoQR escaneoQR,
            LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String estado) {
        this.id = id;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.espacio = espacio;
        this.escaneoQR = escaneoQR;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.estado = estado;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public EspacioDisponible getEspacio() {
        return espacio;
    }

    public void setEspacio(EspacioDisponible espacio) {
        this.espacio = espacio;
    }

    public EscaneoQR getEscaneoQR() {
        return escaneoQR;
    }

    public void setEscaneoQR(EscaneoQR escaneoQR) {
        this.escaneoQR = escaneoQR;
    }

    public LocalDateTime getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(LocalDateTime fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public LocalDateTime getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(LocalDateTime fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", usuario=" + usuario + ", vehiculo=" + vehiculo + ", espacio=" + espacio
                + ", escaneoQR=" + escaneoQR + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin
                + ", estado=" + estado + "]";
    }

}
