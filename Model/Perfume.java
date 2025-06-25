package com.example.PerfulandiaSPA.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Genera getters, setters, toString, equals, hashCode y un constructor con los
      // campos requeridos.
@AllArgsConstructor // Genera un constructor con todos los campos.
@NoArgsConstructor // Genera un constructor con todos los campos.
public class Perfume {
    private int id_perfume;
    private String nombre_perfume;
    private String marca_perfume;
    private String descripcion_perfume;
    private int precio_perfume;
    private int stock; // Agregado el campo precio
}