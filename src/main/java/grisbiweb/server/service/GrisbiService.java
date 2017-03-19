package grisbiweb.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.mapper.PartyMapper;
import grisbiweb.server.xml.GrisbiXmlLoader;
import grisbiweb.server.xml.GrisbiXmlRepository;
import grisbiweb.server.xml.model.CurrencyXml;

@Service
public class GrisbiService {

    @Autowired
    private GrisbiXmlLoader grisbiXmlLoader;

    @Autowired
    private PartyMapper partyMapper;
    
    @Autowired
    private GrisbiXmlRepository grisbiXmlRepository;

    public List<CurrencyXml> getCurrencies() {
        List<CurrencyXml> currencies = this.grisbiXmlLoader.loadGrisbi().getCurrency();
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

}
