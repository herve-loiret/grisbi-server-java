package grisbiweb.server.rest.mapper;

import grisbiweb.server.model.PartyOld;
import grisbiweb.server.rest.model.response.PartyResponse;

import java.util.ArrayList;
import java.util.List;

public class PartyMapper {

	public static List<PartyResponse> mapParties(List<PartyOld> partyOlds) {
		List<PartyResponse> partiesUIs = new ArrayList<>();
		for (PartyOld partyOld : partyOlds) {
			PartyResponse partyUI = new PartyResponse();
			partyUI.setId(partyOld.getIdLong());
			partyUI.setName(partyOld.getName());
			partiesUIs.add(partyUI);
		}
		return partiesUIs;
	}

}
