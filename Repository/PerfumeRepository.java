package com.example.PerfulandiaSPA.Repository;

import com.example.PerfulandiaSPA.Model.Perfume;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PerfumeRepository {
    // Arreglo que guardara todos los libros
    private List<Perfume> listaPerfumes = new ArrayList<>();

    public PerfumeRepository() {
        // Agregar libros por defecto
        listaPerfumes.add(new Perfume(1, "9pm", "Afnan", "Fragancia dulce y nocturna, ideal para salidas.", 45000, 50)); //Agregado el precio
        listaPerfumes.add(new Perfume(2, "Acqua di Gi Profondo", "Giorgio Armani", "Aroma marino y profundo, fresco y elegante.", 85000, 50));
        listaPerfumes.add(new Perfume(3, "Bleu de Chanel", "Chanel", "Fragancia amaderada y cítrica, moderna y versátil.", 120000, 50));
        listaPerfumes.add(new Perfume(4, "Aventus", "Creed", "Notas frutales y ahumadas, sofisticado y exclusivo.", 250000, 50));
        listaPerfumes.add(new Perfume(5, "Sauvage", "Dior", "Aroma fresco y salvaje, con bergamota y ambroxan.", 110000, 50));
        listaPerfumes.add(new Perfume(6, "Erba Pura", "Sospiro", "Fragancia exótica y frutal, cítricos y almizcle.", 180000, 50));
        listaPerfumes.add(new Perfume(7, "King", "Parfums de Marly", "Aroma real y poderoso, con notas de piña y madera.", 130000, 50));
        listaPerfumes.add(new Perfume(8, "Layton", "Parfums de Marly", "Oriental y especiado, elegante y duradero.", 140000, 50));
        listaPerfumes.add(new Perfume(9, "Light Blue", "Dolce & Gabbana", "Fresco y veraniego, con manzana y cedro.", 90000, 50));
        listaPerfumes.add(new Perfume(10, "One Million EDT", "Paco Rabanne", "Dorado y lujoso, cuero y especias.", 95000, 50));
        listaPerfumes.add(new Perfume(11, "Starwalker", "Montblanc", "Moderno y sofisticado, bambú y cítricos.", 70000, 50));
        listaPerfumes.add(new Perfume(12, "Eros", "Versace", "Vibrante y masculino, menta y manzana.", 105000, 50));
        listaPerfumes.add(new Perfume(13, "Eros Flame", "Versace", "Intenso y cálido, versión flame de Eros.", 115000, 50));
    }

    // Metodo que retorna todoa los libros
    public List<Perfume> obtenerPerfumes() {
        return listaPerfumes;
    }

    // Buscar un libro por su id
    public Perfume buscarPerfumePorId(int id) {
        for (Perfume perfume : listaPerfumes) {
            if (perfume.getId_perfume() == id) {
                return perfume;
            }
        }
        return null;
    }

    public Perfume guardarPerfume(Perfume per) {
        // Generar nuevo ID secuencial
        long nuevoId = 1;
        for (Perfume l : listaPerfumes) {
            if (l.getId_perfume() >= nuevoId) {
                nuevoId = l.getId_perfume() + 1;
            }
        }

        // Crear una nueva instancia con los datos del libro recibido
        Perfume perfume = new Perfume();
        perfume.setId_perfume((int) nuevoId); // ID generado automáticamente
        perfume.setNombre_perfume(per.getNombre_perfume());
        perfume.setMarca_perfume(per.getMarca_perfume());
        perfume.setDescripcion_perfume(per.getDescripcion_perfume());
        perfume.setPrecio_perfume(per.getPrecio_perfume());
        perfume.setStock(per.getStock());

        // Agregar el nuevo libro a la lista
        listaPerfumes.add(perfume);

        return perfume;
    }

    public Perfume actualizarPerfume(Perfume per) {
        int id = 0;
        int idPosicion = 0;

        for (int i = 0; i < listaPerfumes.size(); i++) {
            if (listaPerfumes.get(i).getId_perfume() == per.getId_perfume()) {
                id = per.getId_perfume();
                idPosicion = i;
            }
        }

        Perfume perfume1 = new Perfume();
        perfume1.setId_perfume(id);
        perfume1.setNombre_perfume(per.getNombre_perfume());
        perfume1.setMarca_perfume(per.getMarca_perfume());
        perfume1.setDescripcion_perfume(per.getDescripcion_perfume());
        perfume1.setPrecio_perfume(per.getPrecio_perfume());
        perfume1.setStock(per.getStock());

        listaPerfumes.set(idPosicion, perfume1);
        return perfume1;
    }

    public void eliminarPerfume(int id) {
        // alternativa 1
        // Libro libro = buscarPorId(id);
        // if (libro != null) {
        // listaLibros.remove(libro);
        // }
        //
        // // alternativa 2
        // int idPosicion = 0;
        // for (int i = 0; i < listaLibros.size(); i++) {
        // if (listaLibros.get(i).getId() == id) {
        // idPosicion = i;
        // break;
        // }
        // }
        // if (idPosicion > 0) {
        // listaLibros.remove(idPosicion);
        // }

        // otra alternativa
        listaPerfumes.removeIf(x -> x.getId_perfume() == id);
    }

    public int totalPerfumes() {
        return listaPerfumes.size();
    }
    
} 
    
    


