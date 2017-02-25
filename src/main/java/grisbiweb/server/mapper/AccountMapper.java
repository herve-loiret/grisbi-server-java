package grisbiweb.server.mapper;

import java.util.ArrayList;
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

    public static Account.AccountType getAccountTypeFromStringId(String kind) {
        AccountType accountType = null;
        if ("0".equals(kind)) {
            accountType = AccountType.BANK;
        } else if ("1".equals(kind)) {
            accountType = AccountType.CASH;
        } else if ("2".equals(kind)) {
            accountType = AccountType.LIABILITY;
        } else if ("3".equals(kind)) {
            accountType = AccountType.ASSET;
        }
        return accountType;
    }
    
    public static AccountType getAccountTypeFromStringName(String value) {
        AccountType accountType = null;
        if ("BANK".equalsIgnoreCase(value)) {
            accountType = AccountType.BANK;
        } else if ("ASSET".equalsIgnoreCase(value)) {
            accountType = AccountType.ASSET;
        } else if ("LIABILITY".equalsIgnoreCase(value)) {
            accountType = AccountType.LIABILITY;
        } else if ("CASH".equalsIgnoreCase(value)) {
            accountType = AccountType.CASH;
        }
        return accountType;
    }
    
    
    public static AccountDto mapAccount(Account accountOld) {
        AccountDto accountUI = new AccountDto();
        accountUI.setId(Long.valueOf(accountOld.getId()));
        accountUI.setName(accountOld.getName());
        accountUI.setTypeAccount(accountOld.getAccountType());
        accountUI.setCurrencyId(accountOld.getCurrencyId());
        return accountUI;
    }

    public static List<AccountDto> mapAccounts(List<Account> accountOlds) {
        List<AccountDto> accountUIs = new ArrayList<>();
        for (Account accountOld : accountOlds) {
            accountUIs.add(mapAccount(accountOld));
        }
        return accountUIs;
    }
}
