package cl.duoc.dsy2205.microservicio_laboratorios.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import cl.duoc.dsy2205.microservicio_laboratorios.dto.LaboratorioDTO;
import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;
import cl.duoc.dsy2205.microservicio_laboratorios.exception.IntegrityViolationException;
import cl.duoc.dsy2205.microservicio_laboratorios.exception.ResourceNotFoundException;

class LaboratorioModelsTest {

    @Test
    void laboratorioEntity_gettersSetters() {
        Laboratorio lab = new Laboratorio();
        lab.setIdLab(1L);
        lab.setNombre("Lab");
        lab.setUbicacion("Ub");
        lab.setCapacidad(10);
        lab.setEncargadoId(2L);

        assertEquals(1L, lab.getIdLab());
        assertEquals("Lab", lab.getNombre());
        assertEquals(10, lab.getCapacidad());
    }

    @Test
    void laboratorioDto_gettersSetters() {
        LaboratorioDTO dto = new LaboratorioDTO();
        dto.setIdLab(2L);
        dto.setNombre("Lab 2");
        dto.setUbicacion("Ub2");
        dto.setCapacidad(5);
        dto.setEncargadoId(3L);

        assertEquals(2L, dto.getIdLab());
        assertEquals("Lab 2", dto.getNombre());
        assertEquals(5, dto.getCapacidad());
    }

    @Test
    void exceptions_keepMessage() {
        IntegrityViolationException ex1 = new IntegrityViolationException("conflict");
        ResourceNotFoundException ex2 = new ResourceNotFoundException("missing");
        assertEquals("conflict", ex1.getMessage());
        assertEquals("missing", ex2.getMessage());
    }
}
