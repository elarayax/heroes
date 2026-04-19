package com.example.heroes.heroes.model;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Party {

    @NotNull
    private Integer id;
    @NotBlank(message = "El nombre de la party es obligatorio")
    private String nombre;
    private List<Heroe> heroes;
}
