package com.example.heroes.heroes.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Heroe {
    
    @NotNull
    private Integer id;

    @NotBlank (message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank (message = "La clase es obligatoria")
    private String clase;
    
    @Builder.Default
    private Integer nivel = 1;
}
