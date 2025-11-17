package cl.duoc.dsy2205.microservicio_laboratorios.service.impl;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;
import cl.duoc.dsy2205.microservicio_laboratorios.repository.LaboratorioRepository;
import cl.duoc.dsy2205.microservicio_laboratorios.service.LaboratorioService;

@Service
@Transactional
public class LaboratorioServiceImpl implements LaboratorioService {

    private static final Logger log = LoggerFactory.getLogger(LaboratorioServiceImpl.class);

    private final LaboratorioRepository repo;

    public LaboratorioServiceImpl(LaboratorioRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Laboratorio> listar() {
        return repo.findAll();
    }

    @Override
    public Laboratorio obtenerPorId(Long idLab) {
    Objects.requireNonNull(idLab, "idLab debe no ser null");
    return repo.findById(idLab)
        .orElseThrow(() -> new cl.duoc.dsy2205.microservicio_laboratorios.exception.ResourceNotFoundException("Laboratorio no encontrado id=" + idLab));
    }

    @Override
    public Laboratorio crear(Laboratorio lab) {
        Objects.requireNonNull(lab, "laboratorio debe no ser null");
        lab.setIdLab(null);
        return Objects.requireNonNull(repo.save(lab));
    }

    @Override
    public Laboratorio actualizar(Long idLab, Laboratorio lab) {
        Objects.requireNonNull(lab, "laboratorio debe no ser null");
        Objects.requireNonNull(idLab, "idLab debe no ser null");
        Laboratorio actual = obtenerPorId(idLab);
        actual.setNombre(lab.getNombre());
        actual.setUbicacion(lab.getUbicacion());
        actual.setCapacidad(lab.getCapacidad());
        actual.setEncargadoId(lab.getEncargadoId());
        return Objects.requireNonNull(repo.save(actual));
    }

    @Override
    public void eliminar(Long idLab) {
        Objects.requireNonNull(idLab, "idLab debe no ser null");
        Laboratorio actual = obtenerPorId(idLab);
        try {
            repo.delete(Objects.requireNonNull(actual));
        } catch (DataIntegrityViolationException ex) {
            log.warn("Attempt to delete laboratorio {} failed due to integrity constraints", idLab);
            throw new cl.duoc.dsy2205.microservicio_laboratorios.exception.IntegrityViolationException("No se puede eliminar el laboratorio porque tiene reservas asociadas");
        }
    }

    @Override
    public List<Laboratorio> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre == null ? "" : nombre);
    }
}
