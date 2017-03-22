package grisbiweb.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.dto.PartyDto;
import grisbiweb.server.mapper.PartyMapper;
import grisbiweb.server.model.Party;
import grisbiweb.server.xml.GrisbiXmlRepository;
import grisbiweb.server.xml.XmlWriter;
import grisbiweb.server.xml.model.PartyXml;

@Service
public class PartyService {

	@Autowired
	private GrisbiXmlRepository grisbiXmlRepository;

	@Autowired
	private PartyMapper partyMapper;

	@Autowired
	private XmlWriter xmlWriter;

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

	public void deleteParty(String partyId) {
		Party party = this.getPartyById(partyId);
		PartyXml partyXml = partyMapper.partyToPartyXml(party);
		xmlWriter.deleteParty(partyXml);
	}

	public PartyDto updateParty(PartyDto partyDto) {

		PartyXml partyXml = partyMapper.partyDtoToPartyXml(partyDto);
		xmlWriter.updateParty(partyXml);

		return partyDto;
	}
}
