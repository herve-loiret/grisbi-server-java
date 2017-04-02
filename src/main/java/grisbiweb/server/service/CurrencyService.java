package grisbiweb.server.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.mapper.CurrencyMapper;
import grisbiweb.server.model.Currency;
import grisbiweb.server.xml.XmlReader;

@Service
public class CurrencyService {

	@Autowired
	private XmlReader xmlReader;

	@Autowired
	private CurrencyMapper currencyMapper;

	public List<Currency> getCurrencies() {
		return this.xmlReader.getGrisbi().getCurrency().stream()
				.map(currencyMapper::currencyXmlToCurrency)
				.collect(Collectors.toList());
	}

}
