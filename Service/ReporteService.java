package com.example.PerfulandiaSPA.Service;

import com.example.PerfulandiaSPA.Model.Reporte;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service

public class ReporteService {
    private final List<Reporte> reportes = new ArrayList<>();
    private int idCounter = 1;

    public List<Reporte> listarReportes() {
        return reportes;
    }

    public void agregarReporte(Reporte reporte) {
        reporte.setId(idCounter++);
        reportes.add(reporte);
    }

    public boolean eliminarReporte(int id) {
        return reportes.removeIf(rep -> rep.getId() == id);
    }

    public void vaciarReportes() {
        reportes.clear();
    }
    
}
