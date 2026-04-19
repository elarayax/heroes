package com.example.heroes.heroes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.heroes.heroes.model.Party;
import com.example.heroes.heroes.services.PartyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/parties")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping
    public List<Party> getAll() {
        return partyService.obtenerTodas();
    }

    @PostMapping
    public Party create(@Valid @RequestBody Party party) {
        return partyService.guardar(party);
    }

    @PostMapping("/{partyId}/add-heroe/{heroeId}")
    public String addHeroe(@PathVariable Integer partyId, @PathVariable Integer heroeId) {
        return partyService.añadirHeroeAParty(partyId, heroeId);
    }

    @DeleteMapping("/{partyId}/remove-heroe/{heroeId}")
    public String removeHeroe(@PathVariable Integer partyId, @PathVariable Integer heroeId) {
        return partyService.eliminarHeroeDeParty(partyId, heroeId);
    }
}