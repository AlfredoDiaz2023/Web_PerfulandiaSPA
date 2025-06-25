package com.example.PerfulandiaSPA.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;

import com.example.PerfulandiaSPA.Model.Perfume;
import com.example.PerfulandiaSPA.Service.PerfumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/v1/carrito")
public class CarritoController {

    private final List<Perfume> carrito = new ArrayList<>();

    @Autowired
    private PerfumeService perfumeService;

    // Método para agregar un perfume al carrito
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
    @GetMapping
    public List<Perfume> verCarrito() {
        return carrito;
    }

    // Método para eliminar items del carrito
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
