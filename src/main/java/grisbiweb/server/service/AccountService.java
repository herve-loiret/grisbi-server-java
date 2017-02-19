package grisbiweb.server.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import grisbiweb.server.exception.AccountNotFoundException;
import grisbiweb.server.model.Account;
import grisbiweb.server.model.Account.AccountType;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.xml.GrisbiXmlManager;
import grisbiweb.server.xml.model.AccountXml;


public enum AccountService {

	INSTANCE;

	private GrisbiXmlManager grisbiXmlManager = GrisbiXmlManager.INSTANCE;

	private TransactionService transactionService = TransactionService.INSTANCE;

	public Account getAccountById(String accountId) {

		Account account = null;
		for (AccountXml accountXml : grisbiXmlManager.loadGrisbi().getAccount()) {
			if (accountId.equals(accountXml.getNumber())) {
				account = new Account(accountXml);
				break;
			}
		}

		if (account == null) {
			throw new AccountNotFoundException();
		} else {
			return account;
		}
	}

	public List<Account> getAccountsByCurrencyId(String currencyId) {
		List<Account> accounts = new ArrayList<>();
		for (Account account : getOpenedAccounts()) {
			if (currencyId.equals(account.getCurrencyId())) {
				accounts.add(account);
			}
		}
		return accounts;
	}

	private List<Account> getAccountsByType(AccountType accountType) {
		List<Account> accounts = new ArrayList<>();
		for (Account account : getOpenedAccounts()) {
			if (account.getAccountType() == accountType) {
				accounts.add(account);
			}
		}
		return accounts;
	}

	public BigDecimal getBalance(String accountId, boolean onlyReconciled) {

		Account account = this.getAccountById(accountId);

		List<Transaction> transactions = transactionService
				.getTransactionsOrderedByAccountId(accountId);

		BigDecimal solde = account.getInitialBalance();

		for (Transaction transaction : transactions) {
			BigDecimal amount = transaction.getAmount();
			if (!transaction.isChildTransaction()) {
				if (!onlyReconciled || transaction.isTransactionPointeOuArchive()) {
					solde = solde.add(amount);
				}
			}
		}

		return solde;
	}

	public BigDecimal getBalanceTotalByAccountType(AccountType accountType, boolean onlyReconciled) {
		BigDecimal balanceTotal = new BigDecimal(0);
		for (Account accountGws : this.getAccountsByType(accountType)) {
			balanceTotal = balanceTotal.add(this.getBalance(accountGws.getId(), onlyReconciled));
		}
		return balanceTotal;
	}

	public BigDecimal getInitialBalance(String accountNumber) {
		Account account = this.getAccountById(accountNumber);
		return account.getInitialBalance();
	}

	public List<Account> getOpenedAccounts() {
		List<Account> accounts = new ArrayList<>();
		for (AccountXml account : this.grisbiXmlManager.loadGrisbi().getAccount()) {
			if ("0".equals(account.getClosedAccount())) {
				accounts.add(new Account(account));
			}
		}
		return accounts;
	}
}
