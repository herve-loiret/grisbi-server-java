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

import grisbiweb.server.dto.TransactionCreationDto;
import grisbiweb.server.dto.TransactionDto;
import grisbiweb.server.exception.TransactionRequestNotValidException;
import grisbiweb.server.model.Account;
import grisbiweb.server.model.Category;
import grisbiweb.server.model.Party;
import grisbiweb.server.model.SubCategory;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.model.TransactionOld;
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
    private static Map<String, TransactionDto> transactionById = null;

    /**
     * permet de calculer le solde
     */
    private static BigDecimal cptTransactionAll = null;

    /**
     * la liste des transactions
     */
    private static List<TransactionDto> transactionUIs = null;

    private static void mapAmount(TransactionOld transactionOld, TransactionDto transactionUI) {
        BigDecimal amount = transactionOld.getAmount();
        if (transactionOld.isDebit()) {
            transactionUI.setDebit(amount.doubleValue());
        } else {
            transactionUI.setCredit(amount.doubleValue());
        }
    }

    private void mapParty(TransactionOld transactionOld, TransactionDto transactionUI) {
        String partyId = transactionOld.getPartyId();
        if (partyId != null) {
            Party party = grisbiService.getPartyById(partyId);
            if (party != null) {
                transactionUI.setParty(party.getName());
            }
        }
    }

    private TransactionDto mapTransaction(TransactionOld transactionOld) {

        TransactionDto transactionUI = new TransactionDto();

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
            TransactionDto transactionParent = transactionById.get(transactionParentId);
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
     * @param transactionCreationDto
     * @return
     * @throws ParseException
     */
    public Transaction mapTransactionRequest(TransactionCreationDto transactionCreationDto) {

        if ((transactionCreationDto.getDebit() != null && transactionCreationDto.getCredit() != null)
                || (transactionCreationDto.getDebit() == null && transactionCreationDto.getCredit() == null)) {
            throw new TransactionRequestNotValidException("credit and debit");
        }

        Date date;
        try {
            date = DateUtils.getFrenchDateFormat().parse(transactionCreationDto.getDate());
        } catch (ParseException | NullPointerException e) {
            throw new TransactionRequestNotValidException("date");
        }

        Transaction transactionOld = new Transaction();
        String categoryId = transactionCreationDto.getCategoryId();
        String subCategoryId = transactionCreationDto.getSubCategoryId();
        String partyId = transactionCreationDto.getPartyId();

        BigDecimal amount = null;
        if (transactionCreationDto.getDebit() != null) {
            amount = NumberUtils.parseNumber(transactionCreationDto.getDebit());
            amount = amount.negate();
        } else {
            amount = NumberUtils.parseNumber(transactionCreationDto.getCredit());
        }

        String accountId = transactionCreationDto.getAccountId();
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
    public synchronized List<TransactionDto> mapTransactions(List<TransactionOld> transactionOlds) {

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
