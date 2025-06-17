package com.parqueo.parkingApp.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "sanciones")
public class Sancion {

   @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    private String motivo;
    private String estado;
    private LocalDateTime registroSancion;

    @OneToMany(mappedBy = "sancion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SancionDetalle> detalles = new HashSet<>();

    @OneToMany(mappedBy = "sancion", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReglasEstacionamiento> reglas = new HashSet<>();

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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getRegistroSancion() {
        return registroSancion;
    }

    public void setRegistroSancion(LocalDateTime registroSancion) {
        this.registroSancion = registroSancion;
    }

    public Set<SancionDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<SancionDetalle> detalles) {
        this.detalles = detalles;
    }

    public Set<ReglasEstacionamiento> getReglas() {
        return reglas;
    }

    public void setReglas(Set<ReglasEstacionamiento> reglas) {
        this.reglas = reglas;
    }

    public Sancion() {
        // Constructor vacío requerido por JPA
    }

    public Sancion(Long id, Usuario usuario, Vehiculo vehiculo, String motivo, String estado,
            LocalDateTime registroSancion, Set<SancionDetalle> detalles, Set<ReglasEstacionamiento> reglas) {
        this.id = id;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
        this.motivo = motivo;
        this.estado = estado;
        this.registroSancion = registroSancion;
        this.detalles = detalles;
        this.reglas = reglas;
    }

    @Override
    public String toString() {
        return "Sancion [id=" + id + ", usuario=" + usuario + ", vehiculo=" + vehiculo + ", motivo=" + motivo
                + ", estado=" + estado + ", registroSancion=" + registroSancion + ", detalles=" + detalles + ", reglas="
                + reglas + "]";
    }
    
}