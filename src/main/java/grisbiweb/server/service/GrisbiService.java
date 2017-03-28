package grisbiweb.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.xml.XmlReader;
import grisbiweb.server.xml.model.CurrencyXml;

@Service
public class GrisbiService {

	@Autowired
	private XmlReader xmlReader;

	public List<CurrencyXml> getCurrencies() {
		List<CurrencyXml> currencies = this.xmlReader.getGrisbi().getCurrency();
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
