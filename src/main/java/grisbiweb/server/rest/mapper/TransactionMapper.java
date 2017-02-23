package grisbiweb.server.rest.mapper;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.exception.TransactionRequestNotValidException;
import grisbiweb.server.model.Account;
import grisbiweb.server.model.Category;
import grisbiweb.server.model.Party;
import grisbiweb.server.model.SubCategory;
import grisbiweb.server.model.TransactionOld;
import grisbiweb.server.rest.model.request.TransactionRequest;
import grisbiweb.server.rest.model.response.TransactionResponse;
import grisbiweb.server.service.AccountService;
import grisbiweb.server.service.CategoryService;
import grisbiweb.server.service.GrisbiService;
import grisbiweb.server.service.TransactionService;
import grisbiweb.server.utils.DateUtils;
import grisbiweb.server.utils.NumberUtils;

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
    private static Map<String, TransactionResponse> transactionById = null;

    /**
     * permet de calculer le solde
     */
    private static BigDecimal cptTransactionAll = null;

    /**
     * la liste des transactions
     */
    private static List<TransactionResponse> transactionUIs = null;

    private static void mapAmount(TransactionOld transactionOld, TransactionResponse transactionUI) {
        BigDecimal amount = transactionOld.getAmount();
        if (transactionOld.isDebit()) {
            transactionUI.setDebit(amount.doubleValue());
        } else {
            transactionUI.setCredit(amount.doubleValue());
        }
    }

    private void mapParty(TransactionOld transactionOld, TransactionResponse transactionUI) {
        String partyId = transactionOld.getPartyId();
        if (partyId != null) {
            Party party = grisbiService.getPartyById(partyId);
            if (party != null) {
                transactionUI.setParty(party.getName());
            }
        }
    }

    private TransactionResponse mapTransaction(TransactionOld transactionOld) {

        TransactionResponse transactionUI = new TransactionResponse();

        mapAmount(transactionOld, transactionUI);

        transactionUI.setId(transactionOld.getIdLong());
        transactionUI.setDate(transactionOld.getDate());
        transactionUI.setId(transactionOld.getIdLong());
        String categoryId = transactionOld.getCategoryId();
        String subCategoryId = transactionOld.getSubCategoryId();

        if (transactionOld.isATransfer()) {
            TransactionOld transactionDistante = transactionService.getForeignTransaction(transactionOld);
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

        mapParty(transactionOld, transactionUI);

        // P/R
        if (transactionOld.isTransactionPointe()) {
            transactionUI.setPr("P");
        }

        // solde
        if (transactionOld.isChildTransaction()) {
            // dans une transaction enfant, on ne cumul pas le solde
            String transactionParentId = transactionOld.getTransactionParentId();
            TransactionResponse transactionParent = transactionById.get(transactionParentId);
            if (transactionParent != null) {
                transactionParent.getSubTransactions().add(transactionUI);
            }
        } else {
            // c'est une transaction parente ou une transaction normale
            cptTransactionAll = cptTransactionAll.add(transactionOld.getAmount());
            transactionUI.setSolde(cptTransactionAll.doubleValue());

            // save in list
            transactionUIs.add(transactionUI);
            transactionById.put(transactionOld.getId(), transactionUI);
        }

        return transactionUI;

    }

    /**
     * , "debit":1, "credit":2, "party":{"id":1,"name":"Huguette Lefacteur"}}
     * 
     * @param transactionRequest
     * @return
     * @throws ParseException
     */
    public TransactionOld mapTransactionRequest(TransactionRequest transactionRequest) {

        if ((transactionRequest.getDebit() != null && transactionRequest.getCredit() != null)
                || (transactionRequest.getDebit() == null && transactionRequest.getCredit() == null)) {
            throw new TransactionRequestNotValidException("credit and debit");
        }

        Date date;
        try {
            date = DateUtils.getFrenchDateFormat().parse(transactionRequest.getDate());
        } catch (ParseException | NullPointerException e) {
            throw new TransactionRequestNotValidException("date");
        }

        TransactionOld transactionOld = new TransactionOld();
        String categoryId = transactionRequest.getCategoryId();
        String subCategoryId = transactionRequest.getSubCategoryId();
        String partyId = transactionRequest.getPartyId();

        BigDecimal amount = null;
        if (transactionRequest.getDebit() != null) {
            amount = NumberUtils.parseNumber(transactionRequest.getDebit());
            amount = amount.negate();
        } else {
            amount = NumberUtils.parseNumber(transactionRequest.getCredit());
        }

        String accountId = transactionRequest.getAccountId();
        // only to check and throw exception if account doesn't exist... (TODO
        // change that)
        accountService.getAccountById(accountId);

        transactionOld.setAccountId(accountId);
        // Nb = (generated by service)
        transactionOld.setOfxId("(null)"); // not supported yet
        transactionOld.setDate(date);
        transactionOld.setValueDate(null); // not supported yet
        transactionOld.setAmount(amount);
        transactionOld.setCurrencyId("1"); // not supported yet
        transactionOld.setExchange(false); // not supported yet
        transactionOld.setExchangeRate(null); // not supported yet
        transactionOld.setExchangeFees(null); // not supported yet
        transactionOld.setPartyId(partyId);
        transactionOld.setCategoryId(categoryId);
        transactionOld.setSubCategoryId(subCategoryId);
        transactionOld.setBreakdown("0"); // not supported yet
        transactionOld.setNotes("(null)"); // not supported yet
        transactionOld.setPaiementMethodId("1"); // not supported yet
        transactionOld.setPaiementMethodContent("(null)"); // not supported yet
        transactionOld.setTransactionStatusId("0"); // not supported yet
        transactionOld.setArchiveNumber("0"); // not supported yet
        transactionOld.setAutomatic(false); // not supported yet
        transactionOld.setReconcileNumber("0"); // not supported yet
        transactionOld.setFinancialYearNumber("0");// not supported yet
        transactionOld.setBudgetImputationId("0");// not supported yet
        transactionOld.setSubCategoryId("0");// not supported yet
        transactionOld.setAccountingDocument(null);// not supported yet
        transactionOld.setBankReferences("(null)");// not supported yet
        transactionOld.setForeignTransactionId("0"); // not supported yet
        transactionOld.setTransactionParentId("0");// not supported yet

        return transactionOld;
    }

    /**
     * 
     * why synchronized ?
     * 
     * @param transactionOlds
     * @return
     */
    public synchronized List<TransactionResponse> mapTransactions(List<TransactionOld> transactionOlds) {

        transactionUIs = new ArrayList<>();
        transactionById = new HashMap<>();

        if (transactionOlds != null && !transactionOlds.isEmpty()) {

            cptTransactionAll = accountService.getInitialBalance(transactionOlds.get(0).getAccountId());

            for (TransactionOld transactionOld : transactionOlds) {
                mapTransaction(transactionOld);
            }
        }
        return transactionUIs;
    }

}
