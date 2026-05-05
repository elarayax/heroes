package com.example.heroes.heroes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.heroes.heroes.model.Magia;
import com.example.heroes.heroes.services.MagiaService;

@RestController
@RequestMapping("/api/v1/magias")
public class MagiaController {
    @Autowired
    private MagiaService magiaService;

    @GetMapping
    public ResponseEntity<List<Magia>> listar() {
        List<Magia> lista = (List<Magia>) magiaService.obtenerTodas();
        return new ResponseEntity<List<Magia>>(lista, HttpStatus.OK);
    }   

    @PostMapping
    public ResponseEntity<Magia> crear(@RequestBody Magia magia) {
        return new ResponseEntity<>(magiaService.guardar(magia), HttpStatus.CREATED);
    }
}
