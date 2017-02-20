package grisbiweb.server.mapper;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import grisbiweb.server.model.Account;
import grisbiweb.server.model.Account.AccountType;
import grisbiweb.server.utils.NumberUtils;
import grisbiweb.server.xml.model.AccountXml;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class AccountMapperTest {

    @Test
    public void should_mapper_map_account_xml_to_account() {
        AccountMapper mapper = Mappers.getMapper(AccountMapper.class);
        PodamFactory factory = new PodamFactoryImpl();
        AccountXml accountXml = factory.manufacturePojo(AccountXml.class);
        accountXml.setKind("0"); // set to BANK
        accountXml.setInitialBalance("100.00");
        
        Account account = mapper.accountXmlToAccount(accountXml);
        
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(account.getAccountType()).isEqualTo(AccountType.BANK);
        softly.assertThat(account.getCurrencyId()).isEqualTo(accountXml.getCurrency());
        softly.assertThat(account.getId()).isEqualTo(accountXml.getNumber());
        softly.assertThat(account.getInitialBalance()).isEqualTo(NumberUtils.parseNumber("100.00"));
        softly.assertThat(account.getKind()).isEqualTo(accountXml.getKind());
        softly.assertThat(account.getName()).isEqualTo(accountXml.getName());
        softly.assertAll();
    }
}
