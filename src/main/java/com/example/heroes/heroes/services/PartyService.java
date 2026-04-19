package com.example.heroes.heroes.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.heroes.heroes.model.Party;
import com.example.heroes.heroes.model.Heroe;
import com.example.heroes.heroes.repository.PartyRepository;
import com.example.heroes.heroes.repository.HeroeRepository;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private HeroeRepository heroeRepository;

    public List<Party> obtenerTodas() {
        return partyRepository.listarTodas();
    }

    public Party buscarPorId(Integer id) {
        return partyRepository.buscarPorId(id);
    }

    public Party guardar(Party party) {
        return partyRepository.guardar(party);
    }

    public String añadirHeroeAParty(Integer partyId, Integer heroeId) {
        Heroe heroeEncontrado = heroeRepository.buscarPorId(heroeId);
        if (heroeEncontrado == null) {
            return "Error: El héroe con ID " + heroeId + " no existe.";
        }

        boolean exito = partyRepository.agregarHeroeAParty(partyId, heroeEncontrado);
        
        if (exito) {
            return "Héroe " + heroeEncontrado.getNombre() + " añadido a la party con éxito.";
        } else {
            return "Error: No se encontró la Party con ID " + partyId;
        }
    }

    public String eliminarHeroeDeParty(Integer partyId, Integer heroeId) {
        boolean eliminado = partyRepository.quitarHeroeDeParty(partyId, heroeId);
        
        if (eliminado) {
            return "Héroe eliminado de la party correctamente.";
        } else {
            return "Error: No se pudo eliminar (verifica si la Party o el Héroe existen).";
        }
    }
}