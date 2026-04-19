package com.example.heroes.heroes.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.heroes.heroes.model.Heroe;

@Repository
public class HeroeRepository {
    private List<Heroe> heroes = new ArrayList<>();

    public HeroeRepository(){
        heroes.add(new Heroe(1, "aragron", "dunadain", 10));
        heroes.add(new Heroe(2, "bilbo", "saqueador", 1));
        heroes.add(new Heroe(3, "arkantos", "atlanteano", 30));
        heroes.add(new Heroe(4, "Jim Raynor", "Terran", 50));
    }

    public List<Heroe> listarTodos(){
        return this.heroes;
    }

    public Heroe buscarPorId(Integer id){
        for (Heroe heroe : heroes) {
            if(heroe.getId() == id){
                return heroe;
            }
        }
        return null;
    }

    public Heroe guardarHeroe(Heroe heroe){
        this.heroes.add(heroe);
        return heroe;
    }

    public Heroe editarHeroe(Heroe hero){
        for (Heroe heroe : heroes) {
            if(heroe.getId() == hero.getId()){
                heroe.setNombre(hero.getNombre());
                heroe.setClase(hero.getClase());
                heroe.setNivel(hero.getNivel());
                return heroe;
            }
        }
        return null;
    }

    public boolean eliminarPorId(Integer id) {
        return heroes.removeIf(heroe -> id.equals(heroe.getId()));
    }
}
