package com.example.heroes.heroes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.heroes.heroes.model.Heroe;
import com.example.heroes.heroes.repository.HeroeRepository;

@Service
public class HeroeService {

    @Autowired
    private HeroeRepository heroeRepository;

    public List<Heroe> getHeroes(){
        return heroeRepository.listarTodos();
    }

    public Heroe getHeroeById(Integer id){
        if(id != null){
            return heroeRepository.buscarPorId(id);
        }
        return null;
    }

    public Heroe saveHeroe(Heroe hero){
        if(heroeRepository.buscarPorId(hero.getId()) == null){
            if(hero.getNivel() == null){
                hero.setNivel(1);
            }
            return heroeRepository.guardarHeroe(hero);
        }
        return null;
    }

    public Heroe updateHeroe(Heroe hero){
        if(hero != null){
            return heroeRepository.editarHeroe(hero);
        }
        return null;
    }

    public String deleteHeroe(Integer id){
        if(heroeRepository.eliminarPorId(id)){
            return "Heroe eliminado";
        }
        return "Heroe no encontrado";
    }


}
