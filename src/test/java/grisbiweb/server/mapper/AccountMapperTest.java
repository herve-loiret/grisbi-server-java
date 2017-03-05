package grisbiweb.server.mapper;

import java.math.BigDecimal;

import org.assertj.core.api.SoftAssertions;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import grisbiweb.server.dto.AccountDto;
import grisbiweb.server.model.Account;
import grisbiweb.server.model.Account.AccountType;
import grisbiweb.server.utils.NumberUtils;
import grisbiweb.server.xml.model.AccountXml;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class AccountMapperTest {

    private AccountMapper mapper = Mappers.getMapper(AccountMapper.class);

    private PodamFactory factory = new PodamFactoryImpl();

    @Test
    public void should_mapper_map_account_xml_to_account() {
        AccountXml accountXml = factory.manufacturePojo(AccountXml.class);
        accountXml.setKind("0"); // set to BANK
        accountXml.setInitialBalance("100.00");

        Account account = mapper.accountXmlToAccount(accountXml);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(account.getAccountType()).isEqualTo(AccountType.BANK);
        softly.assertThat(account.getCurrencyId()).isEqualTo(accountXml.getCurrency());
        softly.assertThat(account.getId()).isEqualTo(accountXml.getNumber());
        softly.assertThat(account.getInitialBalance()).isEqualTo(NumberUtils.parseNumber("100.00"));
        softly.assertThat(account.getName()).isEqualTo(accountXml.getName());
        softly.assertAll();
    }

    @Test
    public void should_mapper_map_account_to_account_dto() {
        AccountMapper mapper = Mappers.getMapper(AccountMapper.class);
        PodamFactory factory = new PodamFactoryImpl();
        Account account = factory.manufacturePojo(Account.class);
        account.setAccountType(AccountType.CASH);
        account.setId("1");
        account.setInitialBalance(new BigDecimal("100.00"));

        AccountDto accountDto = mapper.accountToAccountDto(account);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(accountDto.getAccountType()).isEqualTo(AccountType.CASH);
        softly.assertThat(accountDto.getCurrencyId()).isEqualTo(account.getCurrencyId());
        softly.assertThat(accountDto.getId()).isEqualTo(Long.valueOf(account.getId()));
        softly.assertThat(accountDto.getName()).isEqualTo(account.getName());
        softly.assertAll();
    }
}
