package grisbiweb.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.mapper.PartyMapper;
import grisbiweb.server.model.Party;
import grisbiweb.server.xml.XmlRepository;
import grisbiweb.server.xml.XmlWriter;
import grisbiweb.server.xml.model.PartyXml;

@Service
public class PartyService {

	@Autowired
	private XmlRepository xmlRepository;

	@Autowired
	private PartyMapper partyMapper;

	@Autowired
	private XmlWriter xmlWriter;

	public List<Party> getParties() {
		return xmlRepository.getParties();
	}

	public Party getPartyById(String id) {
		return xmlRepository.getPartyById(id);
	}

	public Party createParty(Party party) {
		PartyXml partyXml = xmlWriter.createParty(partyMapper.partyToPartyXml(party));
		return partyMapper.partyXmlToParty(partyXml);
	}

	public void deleteParty(String partyId) {
		Party party = this.getPartyById(partyId);
		PartyXml partyXml = partyMapper.partyToPartyXml(party);
		xmlWriter.deleteParty(partyXml);
	}

	public Party updateParty(Party party) {

		PartyXml partyXml = partyMapper.partyToPartyXml(party);
		xmlWriter.updateParty(partyXml);

		return party;
	}
}
