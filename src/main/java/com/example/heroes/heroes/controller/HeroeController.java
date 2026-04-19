package com.example.heroes.heroes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import com.example.heroes.heroes.model.Heroe;
import com.example.heroes.heroes.services.HeroeService;

@RestController
@RequestMapping("/api/v1/heroes")
public class HeroeController {
    @Autowired
    private HeroeService heroeService;

    @GetMapping
    public ResponseEntity<List<Heroe>> todosLosHeroes(){
        List<Heroe> heroes = heroeService.getHeroes();
        if(heroes.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Heroe> buscarPorId(@PathVariable Integer id){
        Heroe hero = heroeService.getHeroeById(id);
        if(hero != null){
            return new ResponseEntity<>(hero, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Heroe> agregarHeroe(@RequestBody Heroe hero){
        Heroe heroes =  heroeService.saveHeroe(hero);
        if (heroes != null) {
            return new ResponseEntity<>(heroes, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }
    }

    @PutMapping
    public ResponseEntity<Heroe> editarHeroe(@RequestBody Heroe hero) {
        Heroe editado = heroeService.updateHeroe(hero);
        if (editado != null) {
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHeroe(@PathVariable Integer id) {
        String resultado = heroeService.deleteHeroe(id);
        if (resultado.equals("Heroe eliminado")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK); 
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }

}
