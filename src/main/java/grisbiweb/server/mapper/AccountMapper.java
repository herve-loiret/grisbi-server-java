package grisbiweb.server.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import grisbiweb.server.model.Account;
import grisbiweb.server.model.Account.AccountType;
import grisbiweb.server.xml.model.AccountXml;

@Mapper(componentModel = "spring", uses = {})
public interface AccountMapper {

    @Mapping(source = "currency", target = "currencyId")
    @Mapping(source = "number", target = "id")
    @Mapping(target = "initialBalance", expression = "java( grisbiweb.server.utils.NumberUtils.parseNumber(accountXml.getInitialBalance()) )")
    @Mapping(target = "accountType", expression = "java( getAccountTypeFromString(accountXml.getKind()) )")
    Account accountXmlToAccount(AccountXml accountXml);

    default Account.AccountType getAccountTypeFromString(String kind) {
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
}
