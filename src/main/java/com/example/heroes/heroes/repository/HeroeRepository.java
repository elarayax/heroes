package com.example.heroes.heroes.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.heroes.heroes.model.Heroe;

@Repository
public interface HeroeRepository extends JpaRepository<Heroe, Integer> {
    List<Heroe> findByClase(String clase);

    @Query("SELECT h FROM Heroe h WHERE h.nivel >= :nivelMinimo")
    List<Heroe> buscarVeteranos(@Param("nivelMinimo") Integer nivelMinimo);
}
