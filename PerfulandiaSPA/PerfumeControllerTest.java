package com.example.PerfulandiaSPA;

import com.example.PerfulandiaSPA.Controller.PerfumeController;
import com.example.PerfulandiaSPA.Model.Perfume;
import com.example.PerfulandiaSPA.Service.PerfumeService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(PerfumeController.class)

public class PerfumeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerfumeService perfumeService;

    @Autowired
    private ObjectMapper objectMapper;
    // Test para mostrar todos los libros de la coleccion
    @Test
    void listarPerfume_debeRetornarListaJson() throws Exception {
        List<Perfume> perfumes = List.of(
                new Perfume(1, "Hugo Boss colection", "Hugo Boss", "Para cualquier momento", 45000, 20),
                new Perfume(2, "Antonio Banderas premium", "Antonio Banderas", "Para seducir", 50000, 10)
        );

        when(perfumeService.getPerfumes()).thenReturn(perfumes);

        mockMvc.perform(get("/api/v1/perfumes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].nombre_perfume").value("Perfume1"));
    }
    //Test para agregar un nuevo libro a la coleccion
    @Test
    void agregarPerfume_debeGuardarYRetornarPerfume() throws Exception {
        Perfume perfume = new Perfume(0, null, null, null, 0, 0);

        when(perfumeService.savePerfume(any(Perfume.class))).thenReturn(perfume);

        mockMvc.perform(post("/api/v1/perfumes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(perfume)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre_perfume").value("Nuevo Perfume"));
    }
    //Test para buscar un libro de la coleccion con ID
    @Test
    void buscarPerfume_porId_existente() throws Exception {
        Perfume perfume = new Perfume(0, null, null, null, 0, 0);

        when(perfumeService.getPerfumeId(5)).thenReturn(perfume);

        mockMvc.perform(get("/api/v1/perfumes/5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre_perfume").value("Buscado"));
    }
    //Test para eliminar un libro de la coleccion
    @Test
    void eliminarPerfume_existente() throws Exception {
        when(perfumeService.deletePerfume(3)).thenReturn("producto eliminado");

        mockMvc.perform(delete("/api/v1/perfumes/3"))
                .andExpect(status().isOk())
                .andExpect(content().string("producto eliminado"));
    }
    //Test para mostrar la cantidad de libros ingresados en lacoleccion
    @Test
    void totalPerfumesV2_debeRetornarCantidad() throws Exception {
        when(perfumeService.totalPerfumesV2()).thenReturn(10);

        mockMvc.perform(get("/api/v1/perfumes/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));
    }
}


