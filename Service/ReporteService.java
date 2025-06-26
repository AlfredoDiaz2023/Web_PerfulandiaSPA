package com.example.PerfulandiaSPA.Service;

import com.example.PerfulandiaSPA.Model.Reporte;
import com.example.PerfulandiaSPA.Repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteService {
    @Autowired
    private ReporteRepository repor;

    public Reporte guardarReporte(Reporte r) {
        return repor.save(r);
    }


    public List<Reporte> listarReporte() {
        return repor.findAll();
    }

    public Optional<Reporte> buscarReporte(int id) {
        return repor.findById(id); 
    }
}
