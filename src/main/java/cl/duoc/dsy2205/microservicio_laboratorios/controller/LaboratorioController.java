package cl.duoc.dsy2205.microservicio_laboratorios.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.dsy2205.microservicio_laboratorios.dto.LaboratorioDTO;
import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;
import cl.duoc.dsy2205.microservicio_laboratorios.mapper.LaboratorioMapper;
import cl.duoc.dsy2205.microservicio_laboratorios.service.LaboratorioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/laboratorios")
@CrossOrigin(origins = "*")
public class LaboratorioController {

    private static final Logger log = LoggerFactory.getLogger(LaboratorioController.class);

    private final LaboratorioService service;

    public LaboratorioController(LaboratorioService service) {
        this.service = service;
    }

    @GetMapping
    public List<LaboratorioDTO> listar() {
        log.info("GET /api/laboratorios");
        return service.listar().stream().map(LaboratorioMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public LaboratorioDTO obtener(@PathVariable("id") Long id) {
        log.info("GET /api/laboratorios/{}", id);
        return LaboratorioMapper.toDto(service.obtenerPorId(id));
    }

    @GetMapping("/search")
    public List<Laboratorio> buscar(@RequestParam(name = "nombre", required = false) String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<LaboratorioDTO> crear(@Valid @RequestBody LaboratorioDTO labDto) {
        Laboratorio lab = LaboratorioMapper.toEntity(labDto);
        log.info("POST /api/laboratorios - creating laboratorio: {}", lab.getNombre());
        Laboratorio creado = service.crear(lab);
        Long id = Objects.requireNonNull(creado.getIdLab(), "Created laboratorio id is null");
        URI location = Objects.requireNonNull(URI.create("/api/laboratorios/" + id));
        return ResponseEntity.created(location).body(LaboratorioMapper.toDto(creado));
    }

    @PutMapping("/{id}")
    public LaboratorioDTO actualizar(@PathVariable("id") Long id, @Valid @RequestBody LaboratorioDTO labDto) {
        log.info("PUT /api/laboratorios/{} - updating laboratorio", id);
        Laboratorio lab = LaboratorioMapper.toEntity(labDto);
        Laboratorio updated = service.actualizar(id, lab);
        return LaboratorioMapper.toDto(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        log.info("DELETE /api/laboratorios/{} - deleting laboratorio", id);
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
