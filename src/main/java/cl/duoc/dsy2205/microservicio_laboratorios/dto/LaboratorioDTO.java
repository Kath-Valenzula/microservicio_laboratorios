package cl.duoc.dsy2205.microservicio_laboratorios.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LaboratorioDTO {

    private Long idLab;

    @NotNull
    @Size(max = 100)
    private String nombre;

    @Size(max = 150)
    private String ubicacion;

    private Integer capacidad;

    private Long encargadoId;

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
}
