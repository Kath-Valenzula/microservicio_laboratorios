package cl.duoc.dsy2205.microservicio_laboratorios.controller;

import java.util.List;
import java.util.Objects;

import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;

import cl.duoc.dsy2205.microservicio_laboratorios.entity.Laboratorio;
import cl.duoc.dsy2205.microservicio_laboratorios.service.LaboratorioService;

@WebMvcTest(LaboratorioController.class)
@AutoConfigureMockMvc(addFilters = false)
@SuppressWarnings({"removal", "nullness"})
class LaboratorioControllerTest {

    @Autowired MockMvc mvc;
    @Autowired ObjectMapper mapper;
    @MockBean LaboratorioService service;

    @Test
    void createHappyPath() throws Exception {
        Laboratorio input = new Laboratorio(null, "Lab A", "Edif", 20, 1L);
        Laboratorio created = new Laboratorio(1L, "Lab A", "Edif", 20, 1L);
        when(service.crear(any(Laboratorio.class))).thenReturn(created);

    mvc.perform(post("/api/laboratorios")
            .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
            .content(Objects.requireNonNull(mapper.writeValueAsString(input))))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/laboratorios/1"));
    }

    @Test
    void createNullIdFromService_shouldReturnServerError() throws Exception {
        Laboratorio input = new Laboratorio(null, "Lab B", "Edif2", 10, 2L);
        Laboratorio created = new Laboratorio(null, "Lab B", "Edif2", 10, 2L);
        when(service.crear(any(Laboratorio.class))).thenReturn(created);

    mvc.perform(post("/api/laboratorios")
            .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
            .content(Objects.requireNonNull(mapper.writeValueAsString(input))))
                .andExpect(status().is5xxServerError());
    }

    @Test
    void listarReturnsItems() throws Exception {
        Laboratorio lab = new Laboratorio(1L, "Lab A", "Edif", 20, 1L);
        when(service.listar()).thenReturn(List.of(lab));

        mvc.perform(get("/api/laboratorios"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Lab A"));
    }

    @Test
    void obtenerReturnsItem() throws Exception {
        Laboratorio lab = new Laboratorio(2L, "Lab B", "Edif2", 10, 2L);
        when(service.obtenerPorId(2L)).thenReturn(lab);

        mvc.perform(get("/api/laboratorios/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idLab").value(2));
    }

    @Test
    void buscarReturnsItems() throws Exception {
        Laboratorio lab = new Laboratorio(3L, "Lab X", "EdifX", 5, 3L);
        when(service.buscarPorNombre("Lab")).thenReturn(List.of(lab));

        mvc.perform(get("/api/laboratorios/search?nombre=Lab"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Lab X"));
    }

    @Test
    void actualizarReturnsItem() throws Exception {
        Laboratorio updated = new Laboratorio(4L, "Lab New", "Ub", 8, 4L);
        when(service.actualizar(any(Long.class), any(Laboratorio.class))).thenReturn(updated);

        Laboratorio payload = new Laboratorio(null, "Lab New", "Ub", 8, 4L);

        mvc.perform(put("/api/laboratorios/4")
                .contentType(Objects.requireNonNull(MediaType.APPLICATION_JSON))
                .content(Objects.requireNonNull(mapper.writeValueAsString(payload))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Lab New"));
    }

    @Test
    void eliminarReturnsNoContent() throws Exception {
        mvc.perform(delete("/api/laboratorios/5"))
                .andExpect(status().isNoContent());
    }
}
