package com.example.PerfulandiaSPA;

import com.example.PerfulandiaSPA.Controller.ControlStockController;
import com.example.PerfulandiaSPA.Model.ControlStock;
import com.example.PerfulandiaSPA.Service.ControlStockService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ControlStockController.class)
public class ControlStockControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ControlStockService controlStockService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void registrarMovimiento_returnControlStock() throws Exception {
        ControlStock movimiento = new ControlStock(1, 10, 9, "Venta");
        movimiento.setFecha(LocalDateTime.now());

        when(controlStockService.guardarMovimiento(any(ControlStock.class))).thenReturn(movimiento);

        mockMvc.perform(post("/api/control-stock")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movimiento)))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerTodos_returnList() throws Exception {
        when(controlStockService.obtenerTodos()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/control-stock"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerPorPerfume_returnList() throws Exception {
        when(controlStockService.obtenerMovimientosPorPerfume(1)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/control-stock/perfume/1"))
                .andExpect(status().isOk());
    }
}