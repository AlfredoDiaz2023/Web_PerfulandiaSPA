package com.example.PerfulandiaSPA.Controller;

import com.example.PerfulandiaSPA.Model.Reporte;
import com.example.PerfulandiaSPA.Service.ReporteService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Importar las librerias de Swagger para la documentación de las API
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


//Importar assembler para HATEOAS
import com.example.PerfulandiaSPA.assemblers.usuarioModelAssembler;

//iMPORTAR LAS CLASES NECESARIAS PARA HATEOAS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//importar las clases para mejorar los modelos de respuesta
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;

//import las clases de ResponseEntity para manejar las respuestas http
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/v1/incidencias")
@Tag(name="Reporte Incidecia", description="Operacion sobre reporte de incidencias ")
@CrossOrigin
public class ReporteControllerV2 {
    @Autowired
    private ReporteService servicio;

    //Crea y guarda un reporte
    @Operation(summary = "Crea y guarda un reporte", description ="Crea y guarda los datos del reporte en una lista")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public Reporte crear(@RequestBody Reporte r) {
        return servicio.guardarReporte(r);
    }

    //Busca un reporte 
    @Operation(summary = "Busca un reporte", description ="Busca un reporte por su id en la lista")
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public Optional<Reporte> buscarPorId(@PathVariable int id) {
    return servicio.buscarReporte(id);
    }
    
    //Obtiene el listado total de reportes
    @Operation(summary = "Total de reportes", description ="Obtiene el listado total de reportes")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public List<Reporte> obtenerTodas() {
        return servicio.listarReporte();
    }
}


