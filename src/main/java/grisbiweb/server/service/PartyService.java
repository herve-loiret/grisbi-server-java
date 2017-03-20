package grisbiweb.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.dto.PartyDto;
import grisbiweb.server.model.Party;
import grisbiweb.server.xml.GrisbiXmlRepository;

@Service
public class PartyService {

    @Autowired
    private GrisbiXmlRepository grisbiXmlRepository;

    public List<Party> getParties() {
        return grisbiXmlRepository.getParties();
    }
    
    public Party getPartyById(String id) {
        if (id == null) {
            return null;
        }
        for (Party party : grisbiXmlRepository.getParties()) {
            if (id.equals(party.getId())) {
                return party;
            }
        }
        return null;
    }

    public PartyDto createParty(PartyDto partyDto) {

        return null;
    }

    public void deleteParty(Long partyId) {

    }
}
