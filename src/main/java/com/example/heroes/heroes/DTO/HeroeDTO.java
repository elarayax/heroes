package com.example.heroes.heroes.DTO;

import lombok.Data;

@Data
public class HeroeDTO {
    private Integer id;
    private String nombre;
    private String clase;
    private Integer nivel;
    private String nombreParty;
}