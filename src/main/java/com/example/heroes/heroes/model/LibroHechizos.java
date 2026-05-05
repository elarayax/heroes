package com.example.heroes.heroes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "libro_hechizos")
public class LibroHechizos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "heroe_id")
    private Heroe heroe;

    @ManyToOne
    @JoinColumn(name = "magia_id")
    private Magia magia;

    private Integer nivelMaestria = 1; 
}