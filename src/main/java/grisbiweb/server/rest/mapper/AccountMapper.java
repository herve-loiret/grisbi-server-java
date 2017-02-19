package grisbiweb.server.rest.mapper;

import grisbiweb.server.model.Account;
import grisbiweb.server.rest.model.response.AccountResponse;

import java.util.ArrayList;
import java.util.List;

public class AccountMapper {

	public static AccountResponse mapAccount(Account account) {
		AccountResponse accountUI = new AccountResponse();
		accountUI.setId(Long.valueOf(account.getId()));
		accountUI.setName(account.getName());
		accountUI.setTypeAccount(account.getAccountType());
		accountUI.setCurrencyId(account.getCurrencyId());
		return accountUI;
	}

	public static List<AccountResponse> mapAccounts(List<Account> accounts) {
		List<AccountResponse> accountUIs = new ArrayList<>();
		for (Account account : accounts) {
			accountUIs.add(mapAccount(account));
		}
		return accountUIs;
	}

}
