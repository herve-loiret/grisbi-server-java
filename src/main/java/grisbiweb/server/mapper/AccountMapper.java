package grisbiweb.server.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import grisbiweb.server.dto.AccountDto;
import grisbiweb.server.model.Account;
import grisbiweb.server.model.Account.AccountType;
import grisbiweb.server.xml.model.AccountXml;

@Mapper(componentModel = "spring", uses = {})
public interface AccountMapper {

    @Mapping(source = "currency", target = "currencyId")
    @Mapping(source = "number", target = "id")
    @Mapping(target = "initialBalance", expression = "java( grisbiweb.server.utils.NumberUtils.parseNumber(accountXml.getInitialBalance()) )")
    @Mapping(target = "accountType", expression = "java( AccountMapper.getAccountTypeFromStringId(accountXml.getKind()) )")
    Account accountXmlToAccount(AccountXml accountXml);

    public List<AccountDto> accountToAccountDto(List<Account> accounts);

    public AccountDto accountToAccountDto(Account account);

    public static Account.AccountType getAccountTypeFromStringId(String id) {
        AccountType accountType = null;
        if ("0".equals(id)) {
            accountType = AccountType.BANK;
        } else if ("1".equals(id)) {
            accountType = AccountType.CASH;
        } else if ("2".equals(id)) {
            accountType = AccountType.LIABILITY;
        } else if ("3".equals(id)) {
            accountType = AccountType.ASSET;
        }
        return accountType;
    }

    public static AccountType getAccountTypeFromStringName(String name) {
        AccountType accountType = null;
        if ("BANK".equalsIgnoreCase(name)) {
            accountType = AccountType.BANK;
        } else if ("ASSET".equalsIgnoreCase(name)) {
            accountType = AccountType.ASSET;
        } else if ("LIABILITY".equalsIgnoreCase(name)) {
            accountType = AccountType.LIABILITY;
        } else if ("CASH".equalsIgnoreCase(name)) {
            accountType = AccountType.CASH;
        }
        return accountType;
    }

}
