package com.example.PerfulandiaSPA.Service;

import com.example.PerfulandiaSPA.Model.Perfume;
import com.example.PerfulandiaSPA.Repository.PerfumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PerfumeService {
    @Autowired
    private PerfumeRepository perfumeRepository;

    public List<Perfume> getPerfumes() {
        return perfumeRepository.obtenerPerfumes();
    }

    public Perfume savePerfume(Perfume perfume) {
        return perfumeRepository.guardarPerfume(perfume);
    }

    public Perfume getPerfumeId(int id) {
        return perfumeRepository.buscarPerfumePorId(id);
    }

    public Perfume updatePerfume(Perfume perfume) {
        return perfumeRepository.actualizarPerfume(perfume);
    }

    public String deletePerfume(int id) {
        perfumeRepository.eliminarPerfume(id);
        return "producto eliminado";
    }

    // LA ACCIÓN LA HACE EL SERVICE
    public int totalPerfumes() {
        return perfumeRepository.obtenerPerfumes().size();
    }

    // LA ACCIÓN LA HACE EL MODELO
    public int totalPerfumesV2() {
        return perfumeRepository.totalPerfumes();
    }
}
