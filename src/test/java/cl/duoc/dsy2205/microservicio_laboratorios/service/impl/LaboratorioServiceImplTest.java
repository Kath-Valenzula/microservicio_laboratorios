package cl.duoc.dsy2205.microservicio_laboratorios.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;
import cl.duoc.dsy2205.microservicio_laboratorios.exception.IntegrityViolationException;
import cl.duoc.dsy2205.microservicio_laboratorios.exception.ResourceNotFoundException;
import cl.duoc.dsy2205.microservicio_laboratorios.repository.LaboratorioRepository;

@ExtendWith(MockitoExtension.class)
class LaboratorioServiceImplTest {

    @Mock LaboratorioRepository repo;
    @InjectMocks LaboratorioServiceImpl service;

    @Test
    void listar_delegatesToRepo() {
        when(repo.findAll()).thenReturn(Collections.emptyList());
        List<Laboratorio> result = service.listar();
        assertEquals(0, result.size());
    }

    @Test
    void obtenerPorId_missingThrows() {
        when(repo.findById(10L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.obtenerPorId(10L));
    }

    @Test
    void crear_setsIdNull() {
        Laboratorio lab = new Laboratorio(9L, "Lab", "Ub", 3, 1L);
        when(repo.save(any(Laboratorio.class))).thenAnswer(inv -> inv.getArgument(0));

        Laboratorio saved = service.crear(lab);

        assertNull(saved.getIdLab());
    }

    @Test
    void actualizar_updatesFields() {
        Laboratorio existing = new Laboratorio(1L, "Old", "OldUb", 1, 2L);
        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(any(Laboratorio.class))).thenAnswer(inv -> inv.getArgument(0));

        Laboratorio update = new Laboratorio(null, "New", "NewUb", 5, 9L);
        Laboratorio saved = service.actualizar(1L, update);

        assertEquals("New", saved.getNombre());
        assertEquals("NewUb", saved.getUbicacion());
        assertEquals(5, saved.getCapacidad());
        assertEquals(9L, saved.getEncargadoId());
    }

    @Test
    void eliminar_integrityViolationThrows() {
        Laboratorio existing = new Laboratorio(1L, "Lab", "Ub", 1, 1L);
        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        doThrow(new DataIntegrityViolationException("fk")).when(repo).delete(existing);

        assertThrows(IntegrityViolationException.class, () -> service.eliminar(1L));
    }

    @Test
    void buscarPorNombre_nullUsesEmptyString() {
        when(repo.findByNombreContainingIgnoreCase("")).thenReturn(List.of());
        service.buscarPorNombre(null);
        verify(repo).findByNombreContainingIgnoreCase("");
    }
}
