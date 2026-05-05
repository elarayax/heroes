package com.example.heroes.heroes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.heroes.heroes.model.LibroHechizos;
import com.example.heroes.heroes.repository.LibroHechizosRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class LibroHechizosService {
    @Autowired
    private LibroHechizosRepository libroRepository;

    public String aprenderHechizo(LibroHechizos nexo) {
        libroRepository.save(nexo);
        return "El héroe " + nexo.getHeroe().getNombre() + 
               " ha aprendido " + nexo.getMagia().getNombre();
    }

    public List<LibroHechizos> obtenerTodos() {
        return libroRepository.findAll();
    }
}