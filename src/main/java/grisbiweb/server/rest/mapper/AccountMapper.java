package grisbiweb.server.rest.mapper;

import grisbiweb.server.model.AccountOld;
import grisbiweb.server.rest.model.response.AccountResponse;

import java.util.ArrayList;
import java.util.List;

public class AccountMapper {

	public static AccountResponse mapAccount(AccountOld accountOld) {
		AccountResponse accountUI = new AccountResponse();
		accountUI.setId(Long.valueOf(accountOld.getId()));
		accountUI.setName(accountOld.getName());
		accountUI.setTypeAccount(accountOld.getAccountType());
		accountUI.setCurrencyId(accountOld.getCurrencyId());
		return accountUI;
	}

	public static List<AccountResponse> mapAccounts(List<AccountOld> accountOlds) {
		List<AccountResponse> accountUIs = new ArrayList<>();
		for (AccountOld accountOld : accountOlds) {
			accountUIs.add(mapAccount(accountOld));
		}
		return accountUIs;
	}

}
