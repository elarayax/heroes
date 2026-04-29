package com.example.heroes.heroes.services;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.heroes.heroes.model.Party;
import com.example.heroes.heroes.DTO.PartyDTO;
import com.example.heroes.heroes.model.Heroe;
import com.example.heroes.heroes.repository.PartyRepository;
import com.example.heroes.heroes.repository.HeroeRepository;

@Service
public class PartyService {

    @Autowired
    private PartyRepository partyRepository;

    @Autowired
    private HeroeRepository heroeRepository;

    public List<PartyDTO> obtenerTodas() {
        return partyRepository.findAll().stream()
                .map(this::convertirADTO) // Transmutamos cada party
                .toList();
    }

    public PartyDTO buscarPorId(Integer id) {
        Party party = partyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("¡La party no existe!"));
        return convertirADTO(party); // Transmutamos la party encontrada
    }

    public Party guardar(Party party) {
        return partyRepository.save(party);
    }

    public String añadirHeroeAParty(Integer partyId, Integer heroeId) {
        Party party = partyRepository.findById(partyId)
            .orElseThrow(() -> new RuntimeException("Error: La Party no existe."));
        Heroe heroe = heroeRepository.findById(heroeId)
            .orElseThrow(() -> new RuntimeException("Error: El héroe no existe."));
        heroe.setParty(party); 
        heroeRepository.save(heroe);

        return "El héroe '" + heroe.getNombre() + "' ahora lucha por la party: " + party.getNombre();
    }

    public String eliminarHeroeDeParty(Integer partyId, Integer heroeId) {
        Heroe heroe = heroeRepository.findById(heroeId)
            .orElseThrow(() -> new RuntimeException("El héroe no existe."));
        if (heroe.getParty() != null && heroe.getParty().getId().equals(partyId)) {
            heroe.setParty(null);
            heroeRepository.save(heroe);
            return "El héroe ha sido expulsado de la party y ahora es un lobo solitario.";
        }
        return "Error: El héroe no pertenece a esa party, no puedes expulsarlo.";
    }

    private PartyDTO convertirADTO(Party party) {
        PartyDTO dto = new PartyDTO();
        dto.setId(party.getId());
        dto.setNombre(party.getNombre());
        
        if (party.getHeroes() != null) {
            dto.setNombresHeroes(party.getHeroes().stream()
                                    .map(Heroe::getNombre)
                                    .toList());
        } else {
            dto.setNombresHeroes(new ArrayList<>());
        }
        
        return dto;
    }
}