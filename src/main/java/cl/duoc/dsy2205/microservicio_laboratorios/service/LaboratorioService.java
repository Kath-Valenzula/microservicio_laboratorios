package cl.duoc.dsy2205.microservicio_laboratorios.service;

import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;

import java.util.List;

public interface LaboratorioService {
    List<Laboratorio> listar();
    Laboratorio obtenerPorId(Long idLab);
    Laboratorio crear(Laboratorio lab);
    Laboratorio actualizar(Long idLab, Laboratorio lab);
    void eliminar(Long idLab);
    List<Laboratorio> buscarPorNombre(String nombre);
}
