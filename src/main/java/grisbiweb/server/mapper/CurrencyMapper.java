package grisbiweb.server.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import grisbiweb.server.dto.CurrencyDto;
import grisbiweb.server.model.Currency;
import grisbiweb.server.xml.model.CurrencyXml;

@Mapper(componentModel = "spring", uses = {})
public interface CurrencyMapper {

	List<Currency> currencyXmlToCurrency(List<CurrencyXml> currencyXml);

	@Mapping(source = "nb", target = "id")
	@Mapping(source = "na", target = "name")
	@Mapping(source = "co", target = "sign")
	Currency currencyXmlToCurrency(CurrencyXml currencyXml);

	List<CurrencyDto> currencyToCurrencyDto(List<Currency> currencies);

	CurrencyDto currencyToCurrencyDto(Currency currency);
}
