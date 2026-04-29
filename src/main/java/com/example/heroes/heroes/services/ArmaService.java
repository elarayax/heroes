package com.example.heroes.heroes.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.heroes.heroes.DTO.ArmaDTO;
import com.example.heroes.heroes.model.Arma;
import com.example.heroes.heroes.repository.ArmaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ArmaService {

    @Autowired
    private ArmaRepository armaRepository;

    public List<ArmaDTO> obtenerTodas(){
        List<ArmaDTO> armas = new ArrayList<>();
        for(Arma arma : armaRepository.findAll()){
            armas.add(convertirADTO(arma));
        }
        return armas;
    }

    public ArmaDTO buscarPorId(Integer id){
        Arma arma = armaRepository.findById(id).orElseThrow(() -> new RuntimeException("Arma no encontrada"));
        return convertirADTO(arma);
    }

    public ArmaDTO guardar(Arma nuevaArma) {
        Arma armaGuardada = armaRepository.save(nuevaArma);
        return convertirADTO(armaGuardada);
    }

    private ArmaDTO convertirADTO (Arma arma){
        ArmaDTO armaDTO = new ArmaDTO();
        armaDTO.setId(arma.getId());
        armaDTO.setNombre(arma.getNombre());
        armaDTO.setDanio(arma.getDanio());
        if(arma.getHeroe() != null){
            armaDTO.setNombreHeroe(arma.getHeroe().getNombre());
        }else{
            armaDTO.setNombreHeroe("Desconocido");
        }
        return armaDTO;
    }
}
