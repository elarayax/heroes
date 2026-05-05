package com.example.heroes.heroes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.heroes.heroes.DTO.HeroeDTO;
import com.example.heroes.heroes.model.Heroe;
import com.example.heroes.heroes.model.LibroHechizos;
import com.example.heroes.heroes.repository.HeroeRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HeroeService {

    @Autowired
    private HeroeRepository heroeRepository;

    public List<HeroeDTO> obtenerTodos() {
        return heroeRepository.findAll().stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public HeroeDTO buscarPorId(Integer id) {
        Heroe heroe = heroeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("¡Héroe no encontrado!"));
        return convertirADTO(heroe);
    }

    public String eliminar(Integer id) {
        try {
            Heroe heroe = heroeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! El héroe con ID " + id + " no existe."));
            heroeRepository.delete(heroe);
            return "El héroe '" + heroe.getNombre() + "' ha sido retirado de la aventura exitosamente.";
        } catch (RuntimeException e) {
            return e.getMessage();
        }
    }

    public Heroe guardarHeroe(Heroe heroe) {
        return heroeRepository.save(heroe);
    }

    public Heroe actualizarHeroes(Integer id,Heroe heroe){
        Heroe hero = heroeRepository.findById(id).orElseThrow(() -> new RuntimeException("¡El héroe no existe en los registros!"));
        if(heroe.getNivel() != null){
            hero.setNivel(heroe.getNivel());
        }
        if(heroe.getNombre() != null){
            hero.setNombre(heroe.getNombre());
        }
        if(heroe.getClase() != null){
            hero.setClase(heroe.getClase());
        }
        return heroeRepository.save(hero);
    }

    public List<HeroeDTO> buscarPorClase(String clase){
        return heroeRepository.findByClase(clase).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    public List<HeroeDTO> buscarVeteranos(Integer nivelMinimo){
        return heroeRepository.buscarVeteranos(nivelMinimo).stream()
                 .map(this::convertirADTO)
                 .toList();
    }

    private HeroeDTO convertirADTO(Heroe heroe) {
        HeroeDTO dto = new HeroeDTO();
        dto.setId(heroe.getId());
        dto.setNombre(heroe.getNombre());
        dto.setClase(heroe.getClase());
        dto.setNivel(heroe.getNivel());

        if (heroe.getParty() != null) {
            dto.setNombreParty(heroe.getParty().getNombre());
        }else{
            dto.setNombreParty("Lobo solitario, auuu");
        }

        if (heroe.getArma() != null) {
            dto.setNombreArma(heroe.getArma().getNombre());
        } else {
            dto.setNombreArma("Desarmado, todos contra mi solo");
        }

        List<String> nombresHechizos = new ArrayList<>();
        if(heroe.getHechizosAprendidos() != null) {
            for(LibroHechizos nexo : heroe.getHechizosAprendidos()) {
                nombresHechizos.add(nexo.getMagia().getNombre());
            }
        }
        dto.setHechizos(nombresHechizos);
            
        return dto;
    }
}
