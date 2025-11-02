package cl.duoc.dsy2205.microservicio_laboratorios.service.impl;

import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;
import cl.duoc.dsy2205.microservicio_laboratorios.repository.LaboratorioRepository;
import cl.duoc.dsy2205.microservicio_laboratorios.service.LaboratorioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaboratorioServiceImpl implements LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;

    public LaboratorioServiceImpl(LaboratorioRepository laboratorioRepository) {
        this.laboratorioRepository = laboratorioRepository;
    }

    @Override
    public List<Laboratorio> findAll() {
        return laboratorioRepository.findAll();
    }

    @Override
    public Optional<Laboratorio> findById(Long id) {
        return laboratorioRepository.findById(id);
    }

    @Override
    public Laboratorio create(Laboratorio lab) {

        return laboratorioRepository.save(lab);
    }

    @Override
    public Laboratorio update(Long id, Laboratorio lab) {
        return laboratorioRepository.findById(id)
                .map(existente -> {
                    existente.setNombre(lab.getNombre());
                    existente.setUbicacion(lab.getUbicacion());
                    existente.setCapacidad(lab.getCapacidad());
                    existente.setEncargadoId(lab.getEncargadoId());
                    return laboratorioRepository.save(existente);
                })
                .orElseThrow(() -> new RuntimeException("Laboratorio con id " + id + " no encontrado"));
    }

    @Override
    public void delete(Long id) {
        if (!laboratorioRepository.existsById(id)) {
            throw new RuntimeException("Laboratorio con id " + id + " no encontrado");
        }
        laboratorioRepository.deleteById(id);
    }
}