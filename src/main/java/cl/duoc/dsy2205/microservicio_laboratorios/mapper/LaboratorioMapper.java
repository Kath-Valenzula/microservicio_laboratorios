package cl.duoc.dsy2205.microservicio_laboratorios.mapper;

import cl.duoc.dsy2205.microservicio_laboratorios.dto.LaboratorioDTO;
import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;

public final class LaboratorioMapper {
    private LaboratorioMapper() {}

    public static Laboratorio toEntity(LaboratorioDTO dto) {
        if (dto == null) return null;
        Laboratorio l = new Laboratorio();
        l.setIdLab(dto.getIdLab());
        l.setNombre(dto.getNombre());
        l.setUbicacion(dto.getUbicacion());
        l.setCapacidad(dto.getCapacidad());
        l.setEncargadoId(dto.getEncargadoId());
        return l;
    }

    public static LaboratorioDTO toDto(Laboratorio l) {
        if (l == null) return null;
        LaboratorioDTO dto = new LaboratorioDTO();
        dto.setIdLab(l.getIdLab());
        dto.setNombre(l.getNombre());
        dto.setUbicacion(l.getUbicacion());
        dto.setCapacidad(l.getCapacidad());
        dto.setEncargadoId(l.getEncargadoId());
        return dto;
    }
}
