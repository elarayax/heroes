package com.example.heroes.heroes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "heroes")
public class Heroe {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank (message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;

    @NotBlank (message = "La clase es obligatoria")
    @Size(min = 4, max = 50, message = "La clase debe tener al menos 4 caracteres")
    @Column(nullable = false, length = 50)
    private String clase;
    
    @Builder.Default
    @Min(value = 1, message = "El nivel mínimo es 1")
    @Max(value = 99, message = "El nivel máximo es 99")
    @Column(nullable = false)
    private Integer nivel = 1;

    @ManyToOne
    @JoinColumn(name = "party_id")
    private Party party;
}
