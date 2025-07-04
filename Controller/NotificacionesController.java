package com.example.PerfulandiaSPA.Controller;

import com.example.PerfulandiaSPA.Model.Notificaciones;
import com.example.PerfulandiaSPA.Service.NotificacionesService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionesController {
    @Autowired
    private NotificacionesService notificacionesService;

    // Obtener todas las notificaciones
    @Operation(summary = "Muestra el total e notificaciones", description ="Muestra un listado de notificaciones marcando si se encuentran leido o no")
    @GetMapping
    public List<Notificaciones> obtenerTodas() {
        return notificacionesService.obtenerTodas();
    }

    // Obtener notificaciones no leídas
    @Operation(summary = "Muestra las notificaciones que no se encuentran leidas", description ="Retorna las notificaciones que no se encuentran leidas")
    @GetMapping("/noleidas")
    public List<Notificaciones> obtenerNoLeidas() {
        return notificacionesService.obtenerNoLeidas();
    }

    // Crear una nueva notificación
     @Operation(summary = "Agregar una Notificacion", description = "Agrega una nueva notificacion  a la lista de de notficacionnes")
    @PostMapping("/crear")
    public Notificaciones crearNotificacion(@RequestParam String titulo, @RequestParam String mensaje) {
        return notificacionesService.crearNotificacion(titulo, mensaje);
    }

    // Marcar una notificación como leída
    @Operation(summary = "Modificar una Notificacion", description = "Modificar una notificacion  ya existente en la lista de de notficacionnes")
    @PostMapping("/leer/{id}")
    public String marcarComoLeida(@PathVariable int id) {
        notificacionesService.marcarComoLeida(id);
        return "Notificación marcada como leída: ID " + id;
    }

    // Marcar todas como leídas
    @Operation(summary = "marcar Notificacines como leidas", description = "Marca el total de notificaciones como leidas")
    @PostMapping("/leer/todas")
    public String marcarTodasComoLeidas() {
        notificacionesService.marcarTodasComoLeidas();
        return "Todas las notificaciones fueron marcadas como leídas";
    }

    // Contar todas las notificaciones
    @Operation(summary = "Muestra el total de  notificaciones", description = "Retorna la cantidad de notificaciones que se encuentran en la lista")
    @GetMapping("/total")
    public int contarTodas() {
        return notificacionesService.contarTodas();
    }

    // Contar las no leídas
    @Operation(summary = "Total de notificaciones no leidas", description = "Retorna la cantidad de notificaciones no leidas que se encuentran en la lista")
    @GetMapping("/total/noleidas")
    public int contarNoLeidas() {
        return notificacionesService.contarNoLeidas();
    }
}

