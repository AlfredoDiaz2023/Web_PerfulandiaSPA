package com.example.PerfulandiaSPA.Controller;

import com.example.PerfulandiaSPA.Model.Perfume;
import com.example.PerfulandiaSPA.Service.PerfumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;

//Importar las librerias de Swagger para la documentacion de las API
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

//Importar el assembler para HATEOAS de este controlador
import com.example.PerfulandiaSPA.assemblers.perfumeModelAssembler;

//Importar las clases necesarias para usar HATEOAS
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

//Importar las clases de HATEOAS EntityModel, CollectionModel y MediaType para manejar los modelos de respuetas
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.MediaTypes;

//Importar las clases responseEntity para manejar las respuestas HTTP
import org.springframework.http.ResponseEntity;

//Importar Stram y colecciones para manejar la lista Perfumes en java
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v2/perfumes")
//La anotacion Tag se usa para agrupar y etiquetar los controladores dentro de la documentacion
@Tag(name = "Perfume", description = "Operaciones sobre el catalogo de Perfumes")
@CrossOrigin // Opcional si accedes desde otro dominio
public class PerfumeControllerV2 {

    @Autowired
    private PerfumeService perfumeService;

    //Inyectar el assembler de Perfume
    @Autowired
    private perfumeModelAssembler assembler;

    @Operation(summary = "Muestra el catalogo de productos", description = "Muestra el catalogo de la lista de Perfumes disponibles")
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Perfume>> listarPerfumes() {
        //Obtener la lista de perfumes y la convertimos a EntityModel usando el assembler
        List<EntityModel<Perfume>> perfumes = perfumeService.getPerfumes().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return CollectionModel.of(perfumes,
            linkTo(methodOn(PerfumeControllerV2.class).listarPerfumes()).withSelfRel());
    }

    @Operation(summary = "Agregar un nuevo producto", description = "Agrega un nuevo perfume a la lista de Perfumes")
    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<EntityModel<Perfume>> agregarPerfume(@RequestBody Perfume perfume) {
        Perfume crear = perfumeService.savePerfume(perfume);
        return ResponseEntity
            .created(linkTo(methodOn(PerfumeController.class).buscarPerfume(crear.getId_perfume())).toUri())
            .body(assembler.toModel(crear));
    }

    @Operation(summary = "Busca un producto del catalogo", description = "Busca un perfume por ID")
    @GetMapping("{id}")
    public Perfume buscarPerfume(@PathVariable int id){
        return perfumeService.getPerfumeId(id);
    }
    @Operation(summary = "Actualizar datos de  un producto del catalogo", description = "Actualiza el detalle de un perfume existente")
    
    @PutMapping(value = "{id}", produces = MediaTypes.HAL_JSON_VALUE)
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