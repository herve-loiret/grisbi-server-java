package grisbiweb.server.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import grisbiweb.server.exception.AccountNotFoundException;
import grisbiweb.server.model.AccountOld;
import grisbiweb.server.model.AccountOld.AccountType;
import grisbiweb.server.model.TransactionOld;
import grisbiweb.server.xml.GrisbiXmlManager;
import grisbiweb.server.xml.model.AccountXml;


public enum AccountService {

	INSTANCE;

	private GrisbiXmlManager grisbiXmlManager = GrisbiXmlManager.INSTANCE;

	private TransactionService transactionService = TransactionService.INSTANCE;

	public AccountOld getAccountById(String accountId) {

		AccountOld accountOld = null;
		for (AccountXml accountXml : grisbiXmlManager.loadGrisbi().getAccount()) {
			if (accountId.equals(accountXml.getNumber())) {
				accountOld = new AccountOld(accountXml);
				break;
			}
		}

		if (accountOld == null) {
			throw new AccountNotFoundException();
		} else {
			return accountOld;
		}
	}

	public List<AccountOld> getAccountsByCurrencyId(String currencyId) {
		List<AccountOld> accountOlds = new ArrayList<>();
		for (AccountOld accountOld : getOpenedAccounts()) {
			if (currencyId.equals(accountOld.getCurrencyId())) {
				accountOlds.add(accountOld);
			}
		}
		return accountOlds;
	}

	private List<AccountOld> getAccountsByType(AccountType accountType) {
		List<AccountOld> accountOlds = new ArrayList<>();
		for (AccountOld accountOld : getOpenedAccounts()) {
			if (accountOld.getAccountType() == accountType) {
				accountOlds.add(accountOld);
			}
		}
		return accountOlds;
	}

	public BigDecimal getBalance(String accountId, boolean onlyReconciled) {

		AccountOld accountOld = this.getAccountById(accountId);

		List<TransactionOld> transactionOlds = transactionService
				.getTransactionsOrderedByAccountId(accountId);

		BigDecimal solde = accountOld.getInitialBalance();

		for (TransactionOld transactionOld : transactionOlds) {
			BigDecimal amount = transactionOld.getAmount();
			if (!transactionOld.isChildTransaction()) {
				if (!onlyReconciled || transactionOld.isTransactionPointeOuArchive()) {
					solde = solde.add(amount);
				}
			}
		}

		return solde;
	}

	public BigDecimal getBalanceTotalByAccountType(AccountType accountType, boolean onlyReconciled) {
		BigDecimal balanceTotal = new BigDecimal(0);
		for (AccountOld accountGws : this.getAccountsByType(accountType)) {
			balanceTotal = balanceTotal.add(this.getBalance(accountGws.getId(), onlyReconciled));
		}
		return balanceTotal;
	}

	public BigDecimal getInitialBalance(String accountNumber) {
		AccountOld accountOld = this.getAccountById(accountNumber);
		return accountOld.getInitialBalance();
	}

	public List<AccountOld> getOpenedAccounts() {
		List<AccountOld> accountOlds = new ArrayList<>();
		for (AccountXml account : this.grisbiXmlManager.loadGrisbi().getAccount()) {
			if ("0".equals(account.getClosedAccount())) {
				accountOlds.add(new AccountOld(account));
			}
		}
		return accountOlds;
	}
}
