package com.example.PerfulandiaSPA;

import com.example.PerfulandiaSPA.Controller.ReporteController;
import com.example.PerfulandiaSPA.Model.Reporte;
import com.example.PerfulandiaSPA.Service.ReporteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebMvcTest(ReporteController.class)
public class ReporteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReporteService incidenciaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void crearReporte_returnReporte() throws Exception {
        Reporte reporte = new Reporte();
        reporte.setDescripcion("Problema con el pedido");

        when(incidenciaService.guardarReporte(any(Reporte.class))).thenReturn(reporte);

        mockMvc.perform(post("/api/v1/reportes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(reporte)))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerTodosLosReportes_returnList() throws Exception {
        mockMvc.perform(get("/api/v1/reportes"))
                .andExpect(status().isOk());
    }
}
