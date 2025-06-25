package com.example.PerfulandiaSPA.Controller;

import com.example.PerfulandiaSPA.Model.Notificaciones;
import com.example.PerfulandiaSPA.Service.NotificacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
@CrossOrigin(origins = "*")

public class NotificacionesController {
    @Autowired
    private NotificacionesService notificacionService;

    @GetMapping
    public List<Notificaciones> listarNotificaciones() {
        return notificacionService.listarNotificaciones();
    }

    @PostMapping("/agregar")
    public String agregarNotificacion(@RequestBody Notificaciones notificacion) {
        notificacionService.agregarNotificacion(notificacion);
        return "Notificación agregada correctamente";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarNotificacion(@PathVariable int id) {
        boolean eliminado = notificacionService.eliminarNotificacion(id);
        return eliminado ? "Notificación eliminada" : "No encontrada";
    }

    @DeleteMapping("/vaciar")
    public String vaciarNotificaciones() {
        notificacionService.vaciarNotificaciones();
        return "Todas las notificaciones fueron eliminadas";
    }
}

