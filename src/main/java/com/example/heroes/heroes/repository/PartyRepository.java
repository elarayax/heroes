package com.example.heroes.heroes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.heroes.heroes.model.Party;

@Repository
public interface PartyRepository extends JpaRepository<Party, Integer> {
    
}