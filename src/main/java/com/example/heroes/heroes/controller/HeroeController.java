package com.example.heroes.heroes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.heroes.heroes.DTO.HeroeDTO;
import com.example.heroes.heroes.model.Heroe;
import com.example.heroes.heroes.services.HeroeService;

@RestController
@RequestMapping("/api/v1/heroes")
public class HeroeController {

    @Autowired
    private HeroeService heroeService;

    @GetMapping
    public ResponseEntity<List<HeroeDTO>> todosLosHeroes() {
        List<HeroeDTO> heroes = heroeService.obtenerTodos();
        if (heroes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroeDTO> buscarPorId(@PathVariable Integer id) {
        try {
            HeroeDTO hero = heroeService.buscarPorId(id);
            return new ResponseEntity<>(hero, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Si el service lanza la excepción del "Heroe no existe"
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/clase/{clase}")
    public ResponseEntity<List<HeroeDTO>> buscarPorClase(@PathVariable String clase) {
        List<HeroeDTO> heroes = heroeService.buscarPorClase(clase);
        if (heroes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<HeroeDTO>> buscarVeteranos(@PathVariable Integer nivel) {
        List<HeroeDTO> heroes = heroeService.buscarVeteranos(nivel);
        if (heroes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(heroes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Heroe> agregarHeroe(@RequestBody Heroe hero) {
        try {
            Heroe guardado = heroeService.guardarHeroe(hero);
            return new ResponseEntity<>(guardado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Heroe> editarHeroe(@PathVariable Integer id, @RequestBody Heroe hero) {
        try {
            Heroe editado = heroeService.guardarHeroe(hero);
            return new ResponseEntity<>(editado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Heroe> actualizarHeroe(@PathVariable Integer id, @RequestBody Heroe hero){
        try{
            Heroe newHero = heroeService.actualizarHeroes( id, hero);
            return new ResponseEntity<>(newHero, HttpStatus.OK);
        }catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarHeroe(@PathVariable Integer id) {
        String resultado = heroeService.eliminar(id);
        
        // Si el mensaje contiene "exitosamente", es un éxito
        if (resultado.contains("exitosamente")) {
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
        }
    }
}