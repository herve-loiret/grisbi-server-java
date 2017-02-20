package grisbiweb.server.model;

import grisbiweb.server.exception.TypeAccountNotFoundException;
import grisbiweb.server.utils.NumberUtils;
import grisbiweb.server.xml.model.AccountXml;

import java.math.BigDecimal;

public class AccountOld {

	public enum AccountType {
		BANK, ASSET, LIABILITY, CASH;
		public static AccountType getEnumByString(String value) {
			AccountType accountType = null;
			if ("BANK".equalsIgnoreCase(value)) {
				accountType = BANK;
			} else if ("ASSET".equalsIgnoreCase(value)) {
				accountType = ASSET;
			} else if ("LIABILITY".equalsIgnoreCase(value)) {
				accountType = LIABILITY;
			} else if ("CASH".equalsIgnoreCase(value)) {
				accountType = CASH;
			}
			if(accountType == null){
				throw new TypeAccountNotFoundException(value);
			}
			return accountType;
		}
	}

	private AccountXml account;

	public AccountOld(AccountXml account) {
		this.account = account;
	}

	public AccountType getAccountType() {
		AccountType accountType = null;
		String kind = this.account.getKind();
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

	public String getCurrencyId() {
		return this.account.getCurrency();
	}

	public String getId() {
		return account.getNumber();
	}

	public BigDecimal getInitialBalance() {
		return NumberUtils.parseNumber(account.getInitialBalance());
	}

	public String getKind() {
		return account.getKind();
	}

	public String getName() {
		return account.getName();
	}

}
