package cl.duoc.dsy2205.microservicio_laboratorios.controller;

import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;
import cl.duoc.dsy2205.microservicio_laboratorios.service.LaboratorioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<Laboratorio>> getAll() {
        return ResponseEntity.ok(laboratorioService.findAll());
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<Laboratorio> getById(@PathVariable Long id) {
        return laboratorioService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Laboratorio> create(@Valid @RequestBody Laboratorio lab) {
        Laboratorio creado = laboratorioService.create(lab);
        return ResponseEntity
                .created(URI.create("/api/laboratorios/" + creado.getIdLab()))
                .body(creado);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Laboratorio> update(@PathVariable Long id,
                                              @Valid @RequestBody Laboratorio lab) {
        Laboratorio actualizado = laboratorioService.update(id, lab);
        return ResponseEntity.ok(actualizado);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        laboratorioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}