package com.example.heroes.heroes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.heroes.heroes.DTO.ArmaDTO;
import com.example.heroes.heroes.model.Arma;
import com.example.heroes.heroes.services.ArmaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/armas")
public class ArmaController {

    @Autowired
    private ArmaService armaService;

    @GetMapping
    public ResponseEntity<?> todasLasArmas(){
        List<ArmaDTO> armas = armaService.obtenerTodas();
        if(!armas.isEmpty()){
            return new ResponseEntity<>(armas, HttpStatus.OK);
        }
        return new ResponseEntity<>("No hay armas", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> armaPorId(@PathVariable Integer id){
        try{
            ArmaDTO arma = armaService.buscarPorId(id);
            return new ResponseEntity<>(arma, HttpStatus.ACCEPTED);
        }catch(RuntimeException e){
            return new ResponseEntity<>("No se encontró el arma", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> agregarArma(@Valid @RequestBody Arma arma){
        try{
            return new ResponseEntity<>(armaService.guardar(arma), HttpStatus.CREATED);
        }catch(RuntimeException e){
            return new ResponseEntity<>("No se guardar el arma", HttpStatus.BAD_REQUEST);
        }
    }
    
}
