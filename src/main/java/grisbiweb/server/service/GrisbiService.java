package grisbiweb.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.mapper.PartyMapper;
import grisbiweb.server.model.Party;
import grisbiweb.server.xml.GrisbiXmlManager;
import grisbiweb.server.xml.model.CurrencyXml;
import grisbiweb.server.xml.model.PartyXml;

@Service
public class GrisbiService {

    @Autowired
    private GrisbiXmlManager grisbiXmlManager;

    @Autowired
    private PartyMapper partyMapper;

    /**
     * <Currency Nb="1" Na="Euro" Co="€" Ico="EUR" Fl="2" />
     * 
     * @return
     */
    public List<CurrencyXml> getCurrencies() {
        List<CurrencyXml> currencies = this.grisbiXmlManager.loadGrisbi().getCurrency();
        return currencies;
    }

    public CurrencyXml getCurrencyById(String currencyId) {
        for (CurrencyXml currency : this.getCurrencies()) {
            if (String.valueOf(currency.getNb()).equals(currencyId)) {
                return currency;
            }
        }
        return null;
    }

    public List<Party> getParties() {
        return partyMapper.partyXmlToParty(this.grisbiXmlManager.loadGrisbi().getParty());
    }

    public Party getPartyById(String id) {
        if (id == null) {
            return null;
        }
        for (PartyXml party : grisbiXmlManager.loadGrisbi().getParty()) {
            if (id.equals(party.getNb())) {
                return partyMapper.partyXmlToParty(party);
            }
        }
        return null;
    }

}
