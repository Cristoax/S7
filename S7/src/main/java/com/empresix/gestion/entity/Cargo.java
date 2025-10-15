package com.empresix.gestion.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "cargo", uniqueConstraints = @UniqueConstraint(columnNames = "codigo"))
public class Cargo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(length = 500)
    private String descripcion;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    @Column(nullable = false)
    private boolean activo = true;

    @Version
    private Long version;

    public Cargo() { }

    public Cargo(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Long getId() { return id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public Long getVersion() { return version; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cargo cargo = (Cargo) o;
        if (id != null && cargo.id != null) return Objects.equals(id, cargo.id);
        return Objects.equals(codigo, cargo.codigo);
    }

    @Override
    public int hashCode() {
        return id != null ? Objects.hash(id) : Objects.hash(codigo);
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", activo=" + activo +
                ", version=" + version +
                '}';
    }
}
