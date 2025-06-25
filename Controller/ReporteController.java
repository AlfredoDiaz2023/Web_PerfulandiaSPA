package com.example.PerfulandiaSPA.Controller;

import com.example.PerfulandiaSPA.Model.Reporte;
import com.example.PerfulandiaSPA.Service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reportes")
@CrossOrigin(origins = "*")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<Reporte> listarReportes() {
        return reporteService.listarReportes(); 
    }

    @PostMapping("/agregar")
    public String agregarReporte(@RequestBody Reporte reporte) {
        reporteService.agregarReporte(reporte);
        return "Reporte agregado correctamente";
    }

    @DeleteMapping("/eliminar/{id}")
    public String eliminarReporte(@PathVariable int id) {
        boolean eliminado = reporteService.eliminarReporte(id);
        return eliminado ? "Reporte eliminado" : "Reporte no encontrado";
    }

    @DeleteMapping("/vaciar")
    public String vaciarReportes() {
        reporteService.vaciarReportes();
        return "Todos los reportes han sido eliminados";
    }
}


