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

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "vehiculo_id")
    private Vehiculo vehiculo;

    @ManyToOne(optional = false)
    @JoinColumn(name = "espacio_id")
    private EspacioDisponible espacio;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EscaneoQR> escaneos;


    private LocalDateTime fechaHoraInicio;
    private LocalDateTime fechaHoraFin;

    private String estado;

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

    public EspacioDisponible getEspacio() {
        return espacio;
    }

    public void setEspacio(EspacioDisponible espacio) {
        this.espacio = espacio;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
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


    public Reserva() {
        // Constructor vacío requerido por JPA
    }

    public Reserva(Usuario usuario, EspacioDisponible espacio, Vehiculo vehiculo, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, String estado) {
        this.usuario = usuario;
        this.espacio = espacio;
        this.vehiculo = vehiculo;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Reserva [id=" + id + ", usuario=" + usuario + ", espacio=" + espacio + ", vehiculo=" + vehiculo
                + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", estado=" + estado
                + "]";
    }

    
}

