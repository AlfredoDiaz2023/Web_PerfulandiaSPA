package com.example.PerfulandiaSPA.Controller;

import com.example.PerfulandiaSPA.Model.Notificaciones;
import com.example.PerfulandiaSPA.Service.NotificacionesService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Importar las librerias de Swagger para la documentacion de la API
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//Importar el assembler para HATEOAS de este controlador 
import com.example.PerfulandiaSPA.assemblers.NotificacionesModelAssembler;

//Importar las clases de HATEAOS EntityModel, CollectionModel y MediaType para Manejar los modelos de respuesta 
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;

//importar stram y collecciones para manejar la lista Notificaciones en java
import java.util.stream.Collector;


@RestController
@RequestMapping("/api/v1/notificaciones")
@Tag(name="Notificacion", description = "Operaciones sobre notificaciones")
public class NotificacionesControllerV2 {
    @Autowired
    private NotificacionesService notificacionesService;

    //inyectar el assebmbler de notificaciones 
    @Autowired
    private NotificacionesModelAssembler assembler;

     // Obtener todas las notificaciones
    @Operation(summary = "Muestra el total e notificaciones", description ="Muestra un listado de notificaciones marcando si se encuentran leido o no")
    @GetMapping(produces= MediaTypes.HAL_JSON_VALUE)
    public List<Notificaciones> obtenerTodas() {
        return notificacionesService.obtenerTodas();
    }

    // Obtener notificaciones no leídas
    @Operation(summary = "Muestra las notifiones que no se encuentran leidas", description ="Retorna las notificaciones que no se encuentran leidas")
    @GetMapping(value = "/noleidas", produces= MediaTypes.HAL_JSON_VALUE)
    public List<Notificaciones> obtenerNoLeidas() {
        return notificacionesService.obtenerNoLeidas();
    }

    // Crear una nueva notificación
     @Operation(summary = "Agregar una Notificacion", description = "Agrega una nueva notificacion  a la lista de de notficacionnes")
    @PostMapping(value = "/crear", produces= MediaTypes.HAL_JSON_VALUE)
    public Notificaciones crearNotificacion(@RequestParam String titulo, @RequestParam String mensaje) {
        return notificacionesService.crearNotificacion(titulo, mensaje);
    }

    // Marcar una notificación como leída
    @Operation(summary = "Modificar una Notificacion", description = "Modificar una notificacion  ya existente en la lista de de notficacionnes")
    @PostMapping(value = "/leer/{id}", produces= MediaTypes.HAL_JSON_VALUE)
    public String marcarComoLeida(@PathVariable int id) {
        notificacionesService.marcarComoLeida(id);
        return "Notificación marcada como leída: ID " + id;
    }

    // Marcar todas como leídas
    @Operation(summary = "marcar Notificacines como leidas", description = "Marca el total de notificaciones como leidas")
    @PostMapping(value = "/leer/todas", produces= MediaTypes.HAL_JSON_VALUE)
    public String marcarTodasComoLeidas() {
        notificacionesService.marcarTodasComoLeidas();
        return "Todas las notificaciones fueron marcadas como leídas";
    }

    // Contar todas las notificaciones
    @Operation(summary = "Muestra el total de  notificaciones", description = "Retorna la cantidad de notificaciones que se encuentran en la lista")
    @GetMapping(value = "/total", produces= MediaTypes.HAL_JSON_VALUE)
    public int contarTodas() {
        return notificacionesService.contarTodas();
    }

    // Contar las no leídas
    @Operation(summary = "Total numerico de notificaciones no leidas", description = "Retorna la cantidad de notificaciones no leidas que se encuentran en la lista")
    @GetMapping(value = "/total/noleidas", produces= MediaTypes.HAL_JSON_VALUE)
    public int contarNoLeidas() {
        return notificacionesService.contarNoLeidas();
    }
}

