package cl.duoc.dsy2205.microservicio_laboratorios.mapper;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import cl.duoc.dsy2205.microservicio_laboratorios.dto.LaboratorioDTO;
import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;

class LaboratorioMapperTest {

    @Test
    void toEntity_mapsFields() {
        LaboratorioDTO dto = new LaboratorioDTO();
        dto.setIdLab(2L);
        dto.setNombre("Lab A");
        dto.setUbicacion("Edif");
        dto.setCapacidad(10);
        dto.setEncargadoId(5L);

        Laboratorio entity = LaboratorioMapper.toEntity(dto);

        assertNotNull(entity);
        assertEquals("Lab A", entity.getNombre());
        assertEquals(10, entity.getCapacidad());
    }

    @Test
    void toDto_mapsFields() {
        Laboratorio lab = new Laboratorio(3L, "Lab B", "Ub", 4, 8L);

        LaboratorioDTO dto = LaboratorioMapper.toDto(lab);

        assertNotNull(dto);
        assertEquals(3L, dto.getIdLab());
        assertEquals("Lab B", dto.getNombre());
    }
}
