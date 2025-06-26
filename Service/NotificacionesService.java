package com.example.PerfulandiaSPA.Service;

import com.example.PerfulandiaSPA.Model.Notificaciones;
import com.example.PerfulandiaSPA.Repository.NotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificacionesService {
    @Autowired
    private NotificacionesRepository notificacionesRepository;

    public List<Notificaciones> obtenerTodas() {
        return notificacionesRepository.obtenerTodas();
    }

    public List<Notificaciones> obtenerNoLeidas() {
        return notificacionesRepository.obtenerNoLeidas();
    }

    public Notificaciones crearNotificacion(String titulo, String mensaje) {
        Notificaciones nueva = new Notificaciones();
        nueva.setTitulo(titulo);
        nueva.setMensaje(mensaje);
        return notificacionesRepository.guardar(nueva);
    }

    public void marcarComoLeida(int id) {
        notificacionesRepository.marcarComoLeida(id);
    }

    public void marcarTodasComoLeidas() {
        notificacionesRepository.marcarTodasComoLeidas();
    }

    public int contarNoLeidas() {
        return notificacionesRepository.obtenerNoLeidas().size();
    }

    public int contarTodas() {
        return notificacionesRepository.totalNotificaciones();
    }
}

