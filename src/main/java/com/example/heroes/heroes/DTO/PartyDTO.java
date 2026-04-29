package com.example.heroes.heroes.DTO;

import lombok.Data;
import java.util.List;

@Data
public class PartyDTO {
    private Integer id;
    private String nombre;
    private List<String> nombresHeroes;
}