package com.example.PerfulandiaSPA.Service;

import com.example.PerfulandiaSPA.Model.Notificaciones;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

@Service

public class NotificacionesService {
    private List<Notificaciones> notificaciones = new ArrayList<>();
    private int contadorId = 1;

    public List<Notificaciones> listarNotificaciones() {
        return notificaciones;
    }

    public void agregarNotificacion(Notificaciones noti) {
        noti.setId(contadorId++);
        notificaciones.add(noti);
    }

    public boolean eliminarNotificacion(int id) {
        Iterator<Notificaciones> it = notificaciones.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    public void vaciarNotificaciones() {
        notificaciones.clear();
    }
}

