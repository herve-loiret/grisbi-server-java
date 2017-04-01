package grisbiweb.server.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import grisbiweb.server.dto.CurrencyDto;
import grisbiweb.server.xml.model.CurrencyXml;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
@RunWith(SpringJUnit4ClassRunner.class)
public class CurrencyMapperTest {

    @Test
    public void should_mapper_map_currency_list() {
        CurrencyMapper mapper = Mappers.getMapper(CurrencyMapper.class);
        PodamFactory podam = new PodamFactoryImpl();
        List<CurrencyXml> currenciesXml = new ArrayList<>();
        CurrencyXml currencyXml = podam.manufacturePojo(CurrencyXml.class);
        currencyXml.setNb("123");
        currenciesXml.add(currencyXml);

        List<CurrencyDto> currenciesResponse = mapper.currencyXmlToCurrencyResponse(currenciesXml);

        assertThat(currenciesResponse.size()).isEqualTo(1);
        assertThat(currenciesResponse.get(0).getId()).as("id").isEqualTo(Long.valueOf(currencyXml.getNb()));
        assertThat(currenciesResponse.get(0).getName()).as("name").isEqualTo(currencyXml.getNa());
        assertThat(currenciesResponse.get(0).getSign()).as("sign").isEqualTo(currencyXml.getCo());
    }
}
