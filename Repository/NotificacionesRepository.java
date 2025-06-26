package com.example.PerfulandiaSPA.Repository;

import com.example.PerfulandiaSPA.Model.Notificaciones;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificacionesRepository {
    private List<Notificaciones> listarNotificaciones = new ArrayList<>();

    public NotificacionesRepository() {
        // Notificaciones de ejemplo (consistentes con el modelo)
        listarNotificaciones.add(new Notificaciones(1, "Bienvenido", "Gracias por registrarte en Perfulandia", LocalDateTime.now(), false));
        listarNotificaciones.add(new Notificaciones(2, "Oferta", "30% de descuento en el primer perfume", LocalDateTime.now().minusHours(3), false));
    }

    public List<Notificaciones> obtenerTodas() {
        return new ArrayList<>(listarNotificaciones); 
    }

    public List<Notificaciones> obtenerNoLeidas() {
        List<Notificaciones> noLeidos = new ArrayList<>();
        for (Notificaciones n : listarNotificaciones) {
            if (!n.isLeido()) {
                noLeidos.add(n);
            }
        }
        return noLeidos;
    }

    public Notificaciones guardar(Notificaciones notificaciones) {
        // Generar ID secuencial (como en LibroRepository)
        int nuevoId = 1;
        for (Notificaciones n : listarNotificaciones) {
            if (n.getIdNotif() >= nuevoId) {
                nuevoId = n.getIdNotif() + 1;
            }
        }
        
        notificaciones.setIdNotif(nuevoId);
        notificaciones.setFecha(LocalDateTime.now());
        notificaciones.setLeido(false);
        
        listarNotificaciones.add(notificaciones);
        return notificaciones;
    }

    public void marcarComoLeida(int id) {
        for (Notificaciones n : listarNotificaciones) {
            if (n.getIdNotif() == id) {
                n.setLeido(true);
                break;
            }
        }
    }

    public void marcarTodasComoLeidas() {
        for (Notificaciones n : listarNotificaciones) {
            n.setLeido(true);
        }
    }

    public int totalNotificaciones() {
        return listarNotificaciones.size();
    }
}
