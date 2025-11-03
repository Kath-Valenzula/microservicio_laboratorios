package cl.duoc.dsy2205.microservicio_laboratorios.service.impl;

import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;
import cl.duoc.dsy2205.microservicio_laboratorios.repository.LaboratorioRepository;
import cl.duoc.dsy2205.microservicio_laboratorios.service.LaboratorioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
@Transactional
public class LaboratorioServiceImpl implements LaboratorioService {

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
        return repo.findById(idLab)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Laboratorio no encontrado"));
    }

    @Override
    public Laboratorio crear(Laboratorio lab) {
        lab.setIdLab(null);
        return repo.save(lab);
    }

    @Override
    public Laboratorio actualizar(Long idLab, Laboratorio lab) {
        Laboratorio actual = obtenerPorId(idLab);
        actual.setNombre(lab.getNombre());
        actual.setUbicacion(lab.getUbicacion());
        actual.setCapacidad(lab.getCapacidad());
        actual.setEncargadoId(lab.getEncargadoId());
        return repo.save(actual);
    }

    @Override
    public void eliminar(Long idLab) {
        Laboratorio actual = obtenerPorId(idLab);
        repo.delete(actual);
    }

    @Override
    public List<Laboratorio> buscarPorNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre == null ? "" : nombre);
    }
}
