package com.parqueo.parkingApp.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "reglas_estacionamiento")
public class ReglasEstacionamiento {

   @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;


    private String tipoSancion;
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_sancion", nullable = false)
    private Sancion sancion;

    @OneToMany(mappedBy = "regla", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SancionDetalle> detalles = new HashSet<>();

    @OneToOne(optional = false)
    @JoinColumn(name = "vehiculo_id", nullable = false, unique = true)
    private Vehiculo vehiculo;

    @OneToOne
    @JoinColumn(name = "usuario_id", nullable = false, unique = true)
    private Usuario usuario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoSancion() {
        return tipoSancion;
    }

    public void setTipoSancion(String tipoSancion) {
        this.tipoSancion = tipoSancion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Sancion getSancion() {
        return sancion;
    }

    public void setSancion(Sancion sancion) {
        this.sancion = sancion;
    }

    public Set<SancionDetalle> getDetalles() {
        return detalles;
    }

    public void setDetalles(Set<SancionDetalle> detalles) {
        this.detalles = detalles;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ReglasEstacionamiento() {
        // Constructor vacío requerido por JPA
    }

    public ReglasEstacionamiento(Long id, String tipoSancion, String descripcion, Sancion sancion,
            Set<SancionDetalle> detalles, Vehiculo vehiculo, Usuario usuario) {
        this.id = id;
        this.tipoSancion = tipoSancion;
        this.descripcion = descripcion;
        this.sancion = sancion;
        this.detalles = detalles;
        this.vehiculo = vehiculo;
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ReglasEstacionamiento [id=" + id + ", tipoSancion=" + tipoSancion + ", descripcion=" + descripcion
                + ", sancion=" + sancion + ", detalles=" + detalles + ", vehiculo=" + vehiculo + ", usuario=" + usuario
                + "]";
    }

}
