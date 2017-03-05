package grisbiweb.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.dto.PartyDto;
import grisbiweb.server.mapper.PartyMapper;

@Service
public class PartyService {

    @Autowired
    private GrisbiService grisbiService;

    @Autowired
    private PartyMapper partyMapper;

    public List<PartyDto> getParties() {
        return partyMapper.partyToPartyDto(grisbiService.getParties());
    }

    public PartyDto createParty(PartyDto partyDto) {

        return null;
    }
}
