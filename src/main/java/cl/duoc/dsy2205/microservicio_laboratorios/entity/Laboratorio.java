package cl.duoc.dsy2205.microservicio_laboratorios.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "LABORATORIOS")
public class Laboratorio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LAB")
    private Long idLab;

    @NotNull
    @Size(max = 100)
    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Size(max = 150)
    @Column(name = "UBICACION", length = 150) // DDL: VARCHAR2(150)
    private String ubicacion;

    @Column(name = "CAPACIDAD")
    private Integer capacidad; // DDL: NUMBER(4)

    @Column(name = "ENCARGADO_ID")
    private Long encargadoId;

    // --- Constructores ---
    public Laboratorio() {}

    public Laboratorio(Long idLab, String nombre, String ubicacion, Integer capacidad, Long encargadoId) {
        this.idLab = idLab;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.encargadoId = encargadoId;
    }

    // --- Getters/Setters ---
    public Long getIdLab() { return idLab; }
    public void setIdLab(Long idLab) { this.idLab = idLab; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getUbicacion() { return ubicacion; }
    public void setUbicacion(String ubicacion) { this.ubicacion = ubicacion; }

    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }

    public Long getEncargadoId() { return encargadoId; }
    public void setEncargadoId(Long encargadoId) { this.encargadoId = encargadoId; }

    // --- equals/hashCode por ID ---
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laboratorio)) return false;
        Laboratorio that = (Laboratorio) o;
        return Objects.equals(idLab, that.idLab);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLab);
    }

    // --- toString ---
    @Override
    public String toString() {
        return "Laboratorio{" +
                "idLab=" + idLab +
                ", nombre='" + nombre + '\'' +
                ", ubicacion='" + ubicacion + '\'' +
                ", capacidad=" + capacidad +
                ", encargadoId=" + encargadoId +
                '}';
    }
}
