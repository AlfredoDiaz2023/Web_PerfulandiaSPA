package com.example.PerfulandiaSPA.Controller;

import com.example.PerfulandiaSPA.Model.Reporte;
import com.example.PerfulandiaSPA.Service.ReporteService;

import io.swagger.v3.oas.annotations.Operation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reportes")
@CrossOrigin
public class ReporteController {
    @Autowired
    private ReporteService servicio;

    //Crea y guarda un reporte
    @Operation(summary = "Crea y guarda un reporte", description ="Crea y guarda los datos del reporte en una lista")
    @PostMapping
    public Reporte crear(@RequestBody Reporte r) {
        return servicio.guardarReporte(r);
    }

    //Busca un reporte 
    @Operation(summary = "Busca un reporte", description ="Busca un reporte por su id en la lista")
    @GetMapping("/{id}")
    public Optional<Reporte> buscarPorId(@PathVariable int id) {
    return servicio.buscarReporte(id);
    }
    
    //Obtiene el listado total de reportes
    @Operation(summary = "Total de reportes", description ="Obtiene el listado total de reportes")
    @GetMapping
    public List<Reporte> obtenerTodas() {
        return servicio.listarReporte();
    }
}


