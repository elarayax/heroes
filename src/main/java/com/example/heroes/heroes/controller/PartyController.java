package com.example.heroes.heroes.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.heroes.heroes.DTO.PartyDTO;
import com.example.heroes.heroes.model.Party;
import com.example.heroes.heroes.services.PartyService;

@RestController
@RequestMapping("/api/v1/parties")
public class PartyController {

    @Autowired
    private PartyService partyService;

    @GetMapping
    public ResponseEntity<List<PartyDTO>> listarParties() {
        List<PartyDTO> parties = partyService.obtenerTodas();
        return parties.isEmpty() 
            ? new ResponseEntity<>(HttpStatus.NO_CONTENT) 
            : new ResponseEntity<>(parties, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Party> fundarParty(@RequestBody Party party) {
        return new ResponseEntity<>(partyService.guardar(party), HttpStatus.CREATED);
    }

    @PutMapping("/{partyId}/heroe/{heroeId}")
    public ResponseEntity<String> reclutarHeroe(@PathVariable Integer partyId, @PathVariable Integer heroeId) {
        try {
            String resultado = partyService.añadirHeroeAParty(partyId, heroeId);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{partyId}/heroe/{heroeId}")
    public ResponseEntity<String> expulsarHeroe(@PathVariable Integer partyId, @PathVariable Integer heroeId) {
        try {
            String resultado = partyService.eliminarHeroeDeParty(partyId, heroeId);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}