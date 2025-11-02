package cl.duoc.dsy2205.microservicio_laboratorios.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "LABORATORIOS")
public class Laboratorio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LAB")
    private Long idLab;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "UBICACION", length = 200)
    private String ubicacion;

    @Column(name = "CAPACIDAD")
    private Integer capacidad;

    @Column(name = "ENCARGADO_ID")
    private Long encargadoId;

    // --------- getters y setters ---------

    public Long getIdLab() {
        return idLab;
    }

    public void setIdLab(Long idLab) {
        this.idLab = idLab;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Long getEncargadoId() {
        return encargadoId;
    }

    public void setEncargadoId(Long encargadoId) {
        this.encargadoId = encargadoId;
    }
}
