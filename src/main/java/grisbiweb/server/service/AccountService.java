package grisbiweb.server.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.exception.AccountNotFoundException;
import grisbiweb.server.mapper.AccountMapper;
import grisbiweb.server.model.Account;
import grisbiweb.server.model.Account.AccountType;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.xml.GrisbiXmlLoader;
import grisbiweb.server.xml.model.AccountXml;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private GrisbiXmlLoader grisbiXmlLoader;

    @Autowired
    private TransactionService transactionService;

    public Account getAccountById(String accountId) {

        Account account = null;
        for (AccountXml accountXml : grisbiXmlLoader.loadGrisbi().getAccount()) {
            if (accountId.equals(accountXml.getNumber())) {
                account = accountMapper.accountXmlToAccount(accountXml);
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
        log.trace("calculating balance for account {}", accountId);
        Account account = this.getAccountById(accountId);

        List<Transaction> transactions = transactionService.getTransactionsOrderedByAccountId(accountId);

        BigDecimal solde = account.getInitialBalance();

        for (Transaction transaction : transactions) {
            BigDecimal amount = transaction.getAmount();
            if (!transaction.isChildTransaction()) {
                if (!onlyReconciled || transaction.isTransactionPointeOuArchive()) {
                    log.debug("adding amount of {}", transaction);
                    solde = solde.add(amount);
                }
            }
        }

        return solde;
    }

    public BigDecimal getBalanceTotalByAccountType(AccountType accountType, boolean onlyReconciled) {
        BigDecimal balanceTotal = BigDecimal.ZERO;
        for (Account accountGws : this.getAccountsByType(accountType)) {
            balanceTotal = balanceTotal.add(this.getBalance(accountGws.getId(), onlyReconciled));
        }
        return balanceTotal;
    }
    
    public boolean deleteAccount(String accountId){
        boolean success = true;
        
        return success;
        
    }

    public BigDecimal getInitialBalance(String accountNumber) {
        Account account = this.getAccountById(accountNumber);
        return account.getInitialBalance();
    }

    public List<Account> getOpenedAccounts() {
        List<Account> accounts = new ArrayList<>();
        for (AccountXml account : this.grisbiXmlLoader.loadGrisbi().getAccount()) {
            if ("0".equals(account.getClosedAccount())) {
                accounts.add(accountMapper.accountXmlToAccount(account));
            }
        }
        return accounts;
    }
}
