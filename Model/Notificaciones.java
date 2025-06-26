package com.example.PerfulandiaSPA.Model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor 
@NoArgsConstructor
public class Notificaciones {
    private int idNotif;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private boolean leido;
}

