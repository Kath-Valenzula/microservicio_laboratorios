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
    private static final String ID_LAB_NULL = "idLab debe no ser null";
    private static final String LABORATORIO_NULL = "laboratorio debe no ser null";

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
    Objects.requireNonNull(idLab, ID_LAB_NULL);
    return repo.findById(idLab)
        .orElseThrow(() -> new cl.duoc.dsy2205.microservicio_laboratorios.exception.ResourceNotFoundException("Laboratorio no encontrado id=" + idLab));
    }

    @Override
    public Laboratorio crear(Laboratorio lab) {
        Objects.requireNonNull(lab, LABORATORIO_NULL);
        lab.setIdLab(null);
        return Objects.requireNonNull(repo.save(lab));
    }

    @Override
    public Laboratorio actualizar(Long idLab, Laboratorio lab) {
        Objects.requireNonNull(lab, LABORATORIO_NULL);
        Objects.requireNonNull(idLab, ID_LAB_NULL);
        Laboratorio actual = obtenerPorId(idLab);
        actual.setNombre(lab.getNombre());
        actual.setUbicacion(lab.getUbicacion());
        actual.setCapacidad(lab.getCapacidad());
        actual.setEncargadoId(lab.getEncargadoId());
        return Objects.requireNonNull(repo.save(actual));
    }

    @Override
    public void eliminar(Long idLab) {
        Objects.requireNonNull(idLab, ID_LAB_NULL);
        Laboratorio actual = obtenerPorId(idLab);
        try {
            repo.delete(Objects.requireNonNull(actual));
        } catch (DataIntegrityViolationException ex) {
            log.warn("Attempt to delete laboratorio failed due to integrity constraints");
            throw new cl.duoc.dsy2205.microservicio_laboratorios.exception.IntegrityViolationException("No se puede eliminar el laboratorio porque tiene reservas asociadas");
        }
    }

    @Override
    public List<Laboratorio> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre == null ? "" : nombre);
    }
}
