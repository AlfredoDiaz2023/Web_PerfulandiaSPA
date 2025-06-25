package com.example.PerfulandiaSPA.Controller;

import com.example.PerfulandiaSPA.Model.Perfume;
import com.example.PerfulandiaSPA.Service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Importar las librerias de Swagger para la documentacion de las API
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/perfumes")
//La anotacion Tag se usa para agrupar y etiquetar los controladores dentro de la documentacion
@Tag(name = "Perfume", description = "Operaciones sobre el catalogo de Perfumes")
@CrossOrigin // Opcional si accedes desde otro dominio
public class PerfumeController {

    @Autowired
    private PerfumeService perfumeService;

    @Operation(summary = "Muestra el catalogo de productos", description = "Muestra el catalogo de la lista de Perfumes disponibles")
    @GetMapping
    public List<Perfume> listarPerfumes() {
        return perfumeService.getPerfumes();
    }

    @Operation(summary = "Agregar un nuevo producto", description = "Agrega un nuevo perfume a la lista de Perfumes")
    @PostMapping
    public Perfume agregarLibro(@RequestBody Perfume perfume) {
        return perfumeService.savePerfume(perfume);
    }

    @Operation(summary = "Busca un producto del catalogo", description = "Busca un perfume por ID")
    @GetMapping("{id}")
    public Perfume buscarPerfume(@PathVariable int id){
        return perfumeService.getPerfumeId(id);
    }
    @Operation(summary = "Actualizazr datos de  un producto del catalogo", description = "Actualiza el detalle de un perfume existente")
    @PutMapping("{id}")
    public Perfume actualizarPerfume(@PathVariable int id, @RequestBody Perfume perfume){
        // el id lo usaremos mas adelante
        return perfumeService.updatePerfume(perfume);
    }

    @Operation(summary = "Eliminar un producto del catalogo", description = "Elimina un perfume por ID")
    @DeleteMapping("{id}")
    public String eliminarPerfume(@PathVariable int id) {
        return perfumeService.deletePerfume(id);
    }

    @Operation(summary = "Contar el total de productos del catalogo", description = "Devuelve el total de perfumes disponibles")
    @GetMapping("/total")
    public int totalPerfumesV2() {
        return perfumeService.totalPerfumesV2();
    }
}