package grisbiweb.server.service;

import grisbiweb.server.model.PartyOld;
import grisbiweb.server.xml.GrisbiXmlManager;
import grisbiweb.server.xml.model.CurrencyXml;
import grisbiweb.server.xml.model.PartyXml;

import java.util.ArrayList;
import java.util.List;

public enum GrisbiService {

	INSTANCE;

	private GrisbiXmlManager grisbiXmlManager = GrisbiXmlManager.INSTANCE;
		
	/**
	 * <Currency Nb="1" Na="Euro" Co="€" Ico="EUR" Fl="2" />
	 * 
	 * @return
	 */
	public List<CurrencyXml> getCurrencies() {
		List<CurrencyXml> currencies = this.grisbiXmlManager.loadGrisbi().getCurrency();
		return currencies;
	}

	public CurrencyXml getCurrencyById(String currencyId){
		for(CurrencyXml currency : this.getCurrencies()){
			if(currency.getNb().equals(currencyId)){
				return currency;
			}
		}
		return null;
	}


	public List<PartyOld> getParties() {
		List<PartyOld> partyOlds = new ArrayList<>();
		for(PartyXml party : this.grisbiXmlManager.loadGrisbi().getParty()){
			PartyOld partyGws = new PartyOld(party);
			partyOlds.add(partyGws);
		}
		return partyOlds;
	}

	public PartyOld getPartyById(String id) {
		if (id == null) {
			return null;
		}
		for (PartyXml party : grisbiXmlManager.loadGrisbi().getParty()) {
			if (id.equals(party.getNb())) {
				return new PartyOld(party);
			}
		}
		return null;
	}

}
