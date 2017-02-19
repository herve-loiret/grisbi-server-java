package grisbiweb.server.rest.mapper;

import grisbiweb.server.rest.model.response.CurrencyResponse;
import grisbiweb.server.xml.model.CurrencyXml;

import java.util.ArrayList;
import java.util.List;

public class CurrencyMapper {

	public static List<CurrencyResponse> mapCurrencies(List<CurrencyXml> currencies) {
		List<CurrencyResponse> currencyUIs = new ArrayList<>();
		for (CurrencyXml currency : currencies) {
			CurrencyResponse currencyUI = new CurrencyResponse();
			currencyUI.setId(Long.valueOf(currency.getNb()));
			currencyUI.setName(currency.getNa());
			currencyUI.setSign(currency.getCo());
			currencyUIs.add(currencyUI);
		}
		return currencyUIs;
	}

}
