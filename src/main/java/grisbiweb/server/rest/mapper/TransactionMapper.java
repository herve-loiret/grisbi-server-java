package grisbiweb.server.rest.mapper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.dto.TransactionCreationDto;
import grisbiweb.server.dto.TransactionDto;
import grisbiweb.server.model.Account;
import grisbiweb.server.model.Category;
import grisbiweb.server.model.Party;
import grisbiweb.server.model.SubCategory;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.service.AccountService;
import grisbiweb.server.service.CategoryService;
import grisbiweb.server.service.GrisbiService;
import grisbiweb.server.service.TransactionService;

@Service
public class TransactionMapper {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GrisbiService grisbiService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountService accountService;

    /**
     * permet de récuperer les transaction déjà mappé par leur id permet de
     * rajouter les transaction enfantes aux parents
     */
    private static Map<String, TransactionDto> transactionById;

    /**
     * permet de calculer le solde
     */
    private static BigDecimal cptTransactionAll;

    /**
     * la liste des transactions
     */
    private static List<TransactionDto> transactionUIs;

    private static void mapAmount(Transaction transaction, TransactionDto transactionUI) {
        BigDecimal amount = transaction.getAmount();
        if (transaction.isDebit()) {
            transactionUI.setDebit(amount.doubleValue());
        } else {
            transactionUI.setCredit(amount.doubleValue());
        }
    }

    private void mapParty(Transaction transaction, TransactionDto transactionUI) {
        String partyId = transaction.getPartyId();
        if (partyId != null) {
            Party party = grisbiService.getPartyById(partyId);
            if (party != null) {
                transactionUI.setParty(party.getName());
            }
        }
    }

    private TransactionDto mapTransaction(Transaction transaction) {

        TransactionDto transactionUI = new TransactionDto();

        mapAmount(transaction, transactionUI);

        transactionUI.setId(transaction.getId());
        transactionUI.setDate(transaction.getDate());
        String categoryId = transaction.getCategoryId();
        String subCategoryId = transaction.getSubCategoryId();

        if (transaction.isATransfer()) {
            Transaction transactionDistante = transactionService.getForeignTransaction(transaction);
            if (transactionDistante != null) {
                Account accountDistant = accountService.getAccountById(transactionDistante.getAccountId());
                if (accountDistant != null) {
                    transactionUI.setCategory("Virement : " + accountDistant.getName());
                }
            }
        } else {
            Category category = categoryService.getCategoryById(categoryId);
            if (category != null) {
                transactionUI.setCategory(category.getName());
                SubCategory subCategory = categoryService.getSubCategoryByIds(categoryId, subCategoryId);
                if (subCategory != null) {
                    transactionUI.setCategory(transactionUI.getCategory() + " : " + subCategory.getName());
                }
            }
        }

        mapParty(transaction, transactionUI);

        // P/R
        if (transaction.isTransactionPointe()) {
            transactionUI.setPr("P");
        }

        // solde
        if (transaction.isChildTransaction()) {
            // dans une transaction enfant, on ne cumul pas le solde
            String transactionParentId = transaction.getTransactionParentId();
            TransactionDto transactionParent = transactionById.get(transactionParentId);
            if (transactionParent != null) {
                transactionParent.getSubTransactions().add(transactionUI);
            }
        } else {
            // c'est une transaction parente ou une transaction normale
            cptTransactionAll = cptTransactionAll.add(transaction.getAmount());
            transactionUI.setSolde(cptTransactionAll.doubleValue());

            // save in list
            transactionUIs.add(transactionUI);
            transactionById.put(String.valueOf(transaction.getId()), transactionUI);
        }

        return transactionUI;

    }

    /**
     * , "debit":1, "credit":2, "party":{"id":1,"name":"Huguette Lefacteur"}}
     * 
     * @param transactionCreationDto
     * @return
     * @throws ParseException
     */
    public Transaction mapTransactionRequest(TransactionCreationDto transactionCreationDto) {

        Transaction transaction = new Transaction();
        String categoryId = transactionCreationDto.getCategoryId();
        String subCategoryId = transactionCreationDto.getSubCategoryId();
        String partyId = transactionCreationDto.getPartyId();

        BigDecimal amount = transactionCreationDto.getAmount();

        String accountId = transactionCreationDto.getAccountId();
        // only to check and throw exception if account doesn't exist... (TODO
        // change that)
        accountService.getAccountById(accountId);

        transaction.setAccountId(accountId);
        // Nb = (generated by service)
        transaction.setOfxId("(null)"); // not supported yet
        transaction.setDate(java.sql.Date.valueOf(transactionCreationDto.getDate()));
        transaction.setValueDate(null); // not supported yet
        transaction.setAmount(amount);
        transaction.setCurrencyId("1"); // not supported yet
        transaction.setExchange(false); // not supported yet
        transaction.setExchangeRate(null); // not supported yet
        transaction.setExchangeFees(null); // not supported yet
        transaction.setPartyId(partyId);
        transaction.setCategoryId(categoryId);
        transaction.setSubCategoryId(subCategoryId);
        transaction.setBreakdown("0"); // not supported yet
        transaction.setNotes("(null)"); // not supported yet
        transaction.setPaiementMethodId("1"); // not supported yet
        transaction.setPaiementMethodContent("(null)"); // not supported yet
        transaction.setTransactionStatusId("0"); // not supported yet
        transaction.setArchiveNumber("0"); // not supported yet
        transaction.setAutomatic(false); // not supported yet
        transaction.setReconcileNumber("0"); // not supported yet
        transaction.setFinancialYearNumber("0");// not supported yet
        transaction.setBudgetImputationId("0");// not supported yet
        transaction.setSubCategoryId("0");// not supported yet
        transaction.setAccountingDocument(null);// not supported yet
        transaction.setBankReferences("(null)");// not supported yet
        transaction.setForeignTransactionId("0"); // not supported yet
        transaction.setTransactionParentId("0");// not supported yet

        return transaction;
    }

    /**
     * 
     * why synchronized ?
     * 
     * @param transactions
     * @return
     */
    public synchronized List<TransactionDto> mapTransactions(List<Transaction> transactions) {

        transactionUIs = new ArrayList<>();
        transactionById = new HashMap<>();

        if (transactions != null && !transactions.isEmpty()) {

            cptTransactionAll = accountService.getInitialBalance(transactions.get(0).getAccountId());

            for (Transaction transaction : transactions) {
                mapTransaction(transaction);
            }
        }
        return transactionUIs;
    }

}
