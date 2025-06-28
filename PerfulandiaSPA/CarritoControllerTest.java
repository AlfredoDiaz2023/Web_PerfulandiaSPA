package com.example.PerfulandiaSPA;

import com.example.PerfulandiaSPA.Controller.CarritoController;
import com.example.PerfulandiaSPA.Model.Perfume;
import com.example.PerfulandiaSPA.Service.PerfumeService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(CarritoController.class)
public class CarritoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerfumeService perfumeService;

    private Perfume perfumeEjemplo;

    @BeforeEach
    void setUp() {
        // Inicializa el perfume con los valores que espera el controlador y los tests
        perfumeEjemplo = new Perfume();
        perfumeEjemplo.setId_perfume(1);
        perfumeEjemplo.setNombre_perfume("Clean Code");
        perfumeEjemplo.setMarca_perfume("Editorial");
        perfumeEjemplo.setDescripcion_perfume("Libro de programación");
        perfumeEjemplo.setPrecio_perfume(100);
        perfumeEjemplo.setStock(10);
    }

    @Test
    void agregarPerfume_alCarrito_debeResponderConfirmacion() throws Exception {
        when(perfumeService.getPerfumeId(1)).thenReturn(perfumeEjemplo);

        mockMvc.perform(post("/api/v1/carrito/agregar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Perfume agregado al carrito: Clean Code"));
    }

    @Test
    void verCarrito_debeMostrarLibrosAgregados() throws Exception {
        when(perfumeService.getPerfumeId(1)).thenReturn(perfumeEjemplo);
        mockMvc.perform(post("/api/v1/carrito/agregar/1"));

        mockMvc.perform(get("/api/v1/carrito"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre_perfume").value("Clean Code"));
    }

    @Test
    void eliminarPerfume_delCarrito_debeEliminarCorrectamente() throws Exception {
        // Siempre que se llame a getPerfumeId(1), devuelve perfumeEjemplo
        when(perfumeService.getPerfumeId(1)).thenReturn(perfumeEjemplo);

        mockMvc.perform(post("/api/v1/carrito/agregar/1"));

        mockMvc.perform(delete("/api/v1/carrito/eliminar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Perfume eliminado del carrito"));
    }

    @Test
    void vaciarCarrito_debeResponderCorrectamente() throws Exception {
        when(perfumeService.getPerfumeId(1)).thenReturn(perfumeEjemplo);
        mockMvc.perform(post("/api/v1/carrito/agregar/1"));

        mockMvc.perform(delete("/api/v1/carrito/vaciar"))
                .andExpect(status().isOk())
                .andExpect(content().string("Carrito vaciado"));
    }

    @Test
    void totalPerfumesCarrito_debeRetornarCantidad() throws Exception {
        when(perfumeService.getPerfumeId(1)).thenReturn(perfumeEjemplo);
        mockMvc.perform(post("/api/v1/carrito/agregar/1"));

        mockMvc.perform(get("/api/v1/carrito/total"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }
}
