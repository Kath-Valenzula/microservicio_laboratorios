package cl.duoc.dsy2205.microservicio_laboratorios.repository;

import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioRepository extends JpaRepository<Laboratorio, Long> {

}