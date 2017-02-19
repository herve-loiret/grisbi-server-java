package grisbiweb.server.model;

import grisbiweb.server.xml.model.PartyXml;

public class Party {

	private PartyXml party;
	
	public Party(PartyXml party) {
		this.party = party;
	}
	
	public String getId(){
		return party.getNb();
	}

	public Long getIdLong(){
		return Long.valueOf(getId());
	}

	public String getName() {
		return party.getNa();
	}

}
