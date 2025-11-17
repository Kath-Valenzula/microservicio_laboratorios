package cl.duoc.dsy2205.microservicio_laboratorios.controller;

import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;
import cl.duoc.dsy2205.microservicio_laboratorios.service.LaboratorioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    public List<Laboratorio> listar() {
        log.info("GET /api/laboratorios");
        return service.listar();
    }

    @GetMapping("/{id}")
    public Laboratorio obtener(@PathVariable("id") Long id) {
        log.info("GET /api/laboratorios/{}", id);
        return service.obtenerPorId(id);
    }

    @GetMapping("/search")
    public List<Laboratorio> buscar(@RequestParam(name = "nombre", required = false) String nombre) {
        return service.buscarPorNombre(nombre);
    }

    @PostMapping
    public ResponseEntity<Laboratorio> crear(@Valid @RequestBody Laboratorio lab) {
        log.info("POST /api/laboratorios - creating laboratorio: {}", lab.getNombre());
        Laboratorio creado = service.crear(lab);
        return ResponseEntity.created(URI.create("/api/laboratorios/" + creado.getIdLab())).body(creado);
    }

    @PutMapping("/{id}")
    public Laboratorio actualizar(@PathVariable("id") Long id, @Valid @RequestBody Laboratorio lab) {
        log.info("PUT /api/laboratorios/{} - updating laboratorio", id);
        return service.actualizar(id, lab);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        log.info("DELETE /api/laboratorios/{} - deleting laboratorio", id);
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
