package com.example.heroes.heroes.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.example.heroes.heroes.model.Party;
import com.example.heroes.heroes.model.Heroe;

@Repository
public class PartyRepository {
    
    private List<Party> parties = new ArrayList<>();

    public List<Party> listarTodas() {
        return this.parties;
    }

    public Party buscarPorId(Integer id) {
        for (Party p : parties) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public Party guardar(Party party) {
        this.parties.add(party);
        return party;
    }

    public Party editar(Party pary){
        for (Party party : parties) {
            if(pary.getId() == party.getId()){
                party.setNombre(pary.getNombre());
                party.setHeroes(pary.getHeroes());
                return pary;
            }
        }
        return null;
    }

    public boolean eliminarPorId(Integer id) {
        return parties.removeIf(party -> id.equals(party.getId()));
    }

    public boolean agregarHeroeAParty(Integer partyId, Heroe heroe) {
        Party party = buscarPorId(partyId);
        if (party != null) {
            if (party.getHeroes() == null) {
                party.setHeroes(new ArrayList<>());
            }
            party.getHeroes().add(heroe);
            return true;
        }
        return false;
    }

    public boolean quitarHeroeDeParty(Integer partyId, Integer heroeId) {
        Party party = buscarPorId(partyId);
        if (party != null && party.getHeroes() != null) {
            return party.getHeroes().removeIf(h -> h.getId().equals(heroeId));
        }
        return false;
    }
}