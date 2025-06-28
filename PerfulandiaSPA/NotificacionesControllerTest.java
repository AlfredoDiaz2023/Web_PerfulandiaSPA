package com.example.PerfulandiaSPA;

import com.example.PerfulandiaSPA.Controller.NotificacionesController;
import com.example.PerfulandiaSPA.Model.Notificaciones;
import com.example.PerfulandiaSPA.Service.NotificacionesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;

@WebMvcTest(NotificacionesController.class)
public class NotificacionesControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NotificacionesService notificacionesService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void obtenerTodasNotificaciones_returnList() throws Exception {
        mockMvc.perform(get("/api/v1/notificaciones"))
                .andExpect(status().isOk());
    }

    @Test
    void obtenerNoLeidas_returnList() throws Exception {
        mockMvc.perform(get("/api/v1/notificaciones/noleidas"))
                .andExpect(status().isOk());
    }

    @Test
    void crearNotificaciones_returnNotificacion() throws Exception {
        mockMvc.perform(post("/api/v1/notificaciones/crear")
                .param("titulo", "Nueva notificacion")
                .param("mensaje", "Este es una prueba"))
                .andExpect(status().isOk());
    }

    @Test
    void marcarComoLeida_returnSuccess() throws Exception {
        mockMvc.perform(post("/api/v1/notificaciones/leer/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Notificacion marcada como leida: ID 1"));
    }

    @Test
    void marcarTodasComoLeidas_returnSuccess() throws Exception {
        mockMvc.perform(post("/api/v1/notificaciones/leer/todas"))
                .andExpect(status().isOk())
                .andExpect(content().string("Todas las notificaciones fueron marcadas como leídas"));
    }

    @Test
    void contarTodasNotificaciones_returnCount() throws Exception {
        mockMvc.perform(get("/api/v1/notificaciones/total"))
                .andExpect(status().isOk());
    }

    @Test
    void contarNoLeidas_returnCount() throws Exception {
        mockMvc.perform(get("/api/v1/notificaciones/total/noleidas"))
                .andExpect(status().isOk());
    }
}
