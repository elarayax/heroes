package com.example.heroes.heroes.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.heroes.heroes.model.Magia;
import com.example.heroes.heroes.repository.MagiaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MagiaService {

    @Autowired
    private MagiaRepository magiaRepository;

    public List<Magia> obtenerTodas() {
        return magiaRepository.findAll();
    }

    public Magia guardar(Magia magia) {
        return magiaRepository.save(magia);
    }
}
