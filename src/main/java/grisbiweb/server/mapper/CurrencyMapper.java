package grisbiweb.server.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import grisbiweb.server.dto.CurrencyDto;
import grisbiweb.server.xml.model.CurrencyXml;

@Mapper(componentModel = "spring", uses = {})
public interface CurrencyMapper {

    List<CurrencyDto> currencyXmlToCurrencyResponse(List<CurrencyXml> currencyXmls);

    @Mapping(source="nb", target="id")
    @Mapping(source="na", target="name")
    @Mapping(source="co", target="sign")
    CurrencyDto currencyXmlToCurrencyResponse(CurrencyXml currencyXml);
}
