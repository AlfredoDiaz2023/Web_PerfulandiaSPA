package com.example.PerfulandiaSPA.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.example.PerfulandiaSPA.Model.Perfume;
import com.example.PerfulandiaSPA.Service.PerfumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

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
@RequestMapping("/api/v2/carrito")
//La anotacion Tag se usa para agrupar y etiquetar los controladores dentro de la documentacion
@Tag(name = "Carrito de Compras", description = "Operaciones sobre el carrito de compras")
public class CarritoControllerV2 {

    private final List<Perfume> carrito = new ArrayList<>();

    @Autowired
    private PerfumeService perfumeService;

    //Inyectar el assembler de Perfume
    @Autowired
    private perfumeModelAssembler assembler;

    // Método para agregar un perfume al carrito
    @Operation(summary = "Agregar un producto al carrito de compras", description = "Agrega un perfume al carrito de compras")
    @PostMapping("/agregar/{id}")
    public ResponseEntity<String> agregarPerfume(@PathVariable int id) {
        System.out.println("Intentando agregar perfume con ID: " + id);
        Perfume perfume = perfumeService.getPerfumeId(id);
        if (perfume == null) {
            System.out.println("No se encontró el perfume con ID: " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Perfume no encontrado");
        }
        if (perfume.getStock() <= 0) {
            System.out.println("Sin stock para el perfume con ID: " + id);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No hay stock disponible");
        }
        perfume.setStock(perfume.getStock() - 1);
        perfumeService.savePerfume(perfume);
        carrito.add(perfume);
        System.out.println("Perfume agregado correctamente: " + perfume.getNombre_perfume());
        return ResponseEntity.ok("Perfume agregado al carrito: " + perfume.getNombre_perfume());
    }

    // Método para ver los items del carrito
    @Operation(summary = "Muestra los productos agregados al carrito de compras", description = "Muestra todos los perfumes en el carrito de compras")
    @GetMapping
    public List<Perfume> verCarrito() {
        return carrito;
    }

    // Método para eliminar items del carrito
    @Operation(summary = "Eliminar un producto al carrito de compras", description = "Elimina un perfume del carrito de compras por ID")
    @DeleteMapping("/eliminar/{id}")
    public String eliminarPerfume(@PathVariable int id) {
        Iterator<Perfume> it = carrito.iterator();
        while (it.hasNext()) {
            Perfume p = it.next();
            if (p.getId_perfume() == id) {
                p.setStock(p.getStock() + 1); // Devuelve stock
                it.remove();
                return "Perfume eliminado del carrito";
            }
        }
        return "Perfume no estaba en el carrito";
    }

    // Método para vaciar el carrito
    @Operation(summary = "Vaciar el carrito de compras", description = "Elimina todos los perfumes del carrito de compras")
    @DeleteMapping("/vaciar")
    public String vaciarCarrito() {
        for (Perfume p : carrito) {
            // Devuelve el stock al producto en la base de datos
            Perfume original = perfumeService.getPerfumeId(p.getId_perfume());
            if (original != null) {
                original.setStock(original.getStock() + 1);
                perfumeService.savePerfume(original); // ASEGÚRATE DE GUARDAR EL ORIGINAL, NO p
            }
        }
        carrito.clear();
        return "Carrito vaciado y stock devuelto";
    }

    // Método para contar los perfumes en el carrito
    @Operation(summary = "Contar los productos del carrito de compras", description = "Devuelve el numero total de perfumes en el carrito de compras")
    @GetMapping("/total")
    public int totalPerfumes() {
        return carrito.size();
    }

    // Método para confirmar la compra
    @DeleteMapping("/confirmar")
    public String confirmarCompra() {
        carrito.clear();
        return "Compra confirmada y carrito vaciado";
    }
}
