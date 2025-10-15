package com.empresix.gestion.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String codigo;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String correo;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private boolean activo;

    @Version
    private int version;

    @ManyToOne(optional = false)
    @JoinColumn(name = "cargo_id", nullable = false)
    private Cargo cargo;

    public Empleado() {
        this.fechaCreacion = LocalDateTime.now();
        this.activo = true;
    }

    public Empleado(String codigo, String nombre, String correo, Cargo cargo) {
        this();
        this.codigo = codigo;
        this.nombre = nombre;
        this.correo = correo;
        this.cargo = cargo;
    }

    // Getters y setters

    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public int getVersion() { return version; }

    public Cargo getCargo() { return cargo; }
    public void setCargo(Cargo cargo) { this.cargo = cargo; }

    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", activo=" + activo +
                ", version=" + version +
                ", cargo=" + (cargo != null ? cargo.getCodigo() : "null") +
                '}';
    }
}
