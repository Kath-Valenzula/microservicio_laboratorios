package cl.duoc.dsy2205.microservicio_laboratorios.service;

import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;

import java.util.List;
import java.util.Optional;

public interface LaboratorioService {

    List<Laboratorio> findAll();

    Optional<Laboratorio> findById(Long id);

    Laboratorio create(Laboratorio lab);

    Laboratorio update(Long id, Laboratorio lab);

    void delete(Long id);
}