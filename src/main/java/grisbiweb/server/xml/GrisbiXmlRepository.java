package grisbiweb.server.xml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.exception.PartyNotFoundException;
import grisbiweb.server.mapper.PartyMapper;
import grisbiweb.server.model.Party;

@Service
public class GrisbiXmlRepository {

	@Autowired
	private GrisbiXmlLoader grisbiXmlLoader;

	@Autowired
	private PartyMapper partyMapper;

	public List<Party> getParties() {
		return partyMapper.partyXmlToParty(this.grisbiXmlLoader.getGrisbi().getParty());
	}

	/**
	 * TODO : unused ! replace replace this by the partyService search
	 * 
	 * @param partyId
	 * @return
	 */
	public Party getPartyById(String partyId) {
		return partyMapper.partyXmlToParty(this.grisbiXmlLoader.getGrisbi().getParty().stream()
				.filter(p -> p.getNb().equals(partyId))
				.findFirst()
				.orElseThrow(() -> new PartyNotFoundException(partyId)));
	}

	public long findNextPartyId() {
		return getParties().stream()
				.mapToLong(party -> Long.valueOf(party.getId()) + 1).max()
				.orElse(1L);
	}

}
