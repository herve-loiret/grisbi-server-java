package grisbiweb.server.rest.mapper;

import grisbiweb.server.model.Party;
import grisbiweb.server.rest.model.response.PartyResponse;

import java.util.ArrayList;
import java.util.List;

public class PartyMapper {

	public static List<PartyResponse> mapParties(List<Party> parties) {
		List<PartyResponse> partiesUIs = new ArrayList<>();
		for (Party party : parties) {
			PartyResponse partyUI = new PartyResponse();
			partyUI.setId(party.getIdLong());
			partyUI.setName(party.getName());
			partiesUIs.add(partyUI);
		}
		return partiesUIs;
	}

}
