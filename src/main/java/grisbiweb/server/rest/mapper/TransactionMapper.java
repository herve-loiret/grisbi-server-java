package grisbiweb.server.rest.mapper;

import grisbiweb.server.exception.TransactionRequestNotValidException;
import grisbiweb.server.model.Account;
import grisbiweb.server.model.Category;
import grisbiweb.server.model.Party;
import grisbiweb.server.model.SubCategory;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.rest.model.request.TransactionRequest;
import grisbiweb.server.rest.model.response.TransactionResponse;
import grisbiweb.server.service.AccountService;
import grisbiweb.server.service.CategoryService;
import grisbiweb.server.service.GrisbiService;
import grisbiweb.server.service.TransactionService;
import grisbiweb.server.utils.DateUtils;
import grisbiweb.server.utils.NumberUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionMapper {

	private static GrisbiService grisbiService = GrisbiService.INSTANCE;
	private static AccountService accountService = AccountService.INSTANCE;
	private static TransactionService transactionService = TransactionService.INSTANCE;
	private static CategoryService categoryManager = CategoryService.INSTANCE;

	/**
	 * permet de récuperer les transaction déjà mappé par leur id permet de rajouter les transaction
	 * enfantes aux parents
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

	private static void mapAmount(Transaction transaction, TransactionResponse transactionUI) {
		BigDecimal amount = transaction.getAmount();
		if (transaction.isDebit()) {
			transactionUI.setDebit(amount.doubleValue());
		} else {
			transactionUI.setCredit(amount.doubleValue());
		}
	}

	private static void mapParty(Transaction transaction, TransactionResponse transactionUI) {
		String partyId = transaction.getPartyId();
		if (partyId != null) {
			Party party = grisbiService.getPartyById(partyId);
			if (party != null) {
				transactionUI.setParty(party.getName());
			}
		}
	}

	private static TransactionResponse mapTransaction(Transaction transaction) {

		TransactionResponse transactionUI = new TransactionResponse();

		mapAmount(transaction, transactionUI);

		transactionUI.setId(transaction.getIdLong());
		transactionUI.setDate(transaction.getDate());
		transactionUI.setId(transaction.getIdLong());
		String categoryId = transaction.getCategoryId();
		String subCategoryId = transaction.getSubCategoryId();

		if (transaction.isATransfer()) {
			Transaction transactionDistante = transactionService.getForeignTransaction(transaction);
			if (transactionDistante != null) {
				Account accountDistant = accountService.getAccountById(transactionDistante
						.getAccountId());
				if (accountDistant != null) {
					transactionUI.setCategory("Virement : " + accountDistant.getName());
				}
			}
		} else {
			Category category = categoryManager.getCategoryById(categoryId);
			if (category != null) {
				transactionUI.setCategory(category.getName());
				SubCategory subCategory = categoryManager.getSubCategoryByIds(categoryId,
						subCategoryId);
				if (subCategory != null) {
					transactionUI.setCategory(transactionUI.getCategory() + " : "
							+ subCategory.getName());
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
			TransactionResponse transactionParent = transactionById.get(transactionParentId);
			if (transactionParent != null) {
				transactionParent.getSubTransactions().add(transactionUI);
			}
		} else {
			// c'est une transaction parente ou une transaction normale
			cptTransactionAll = cptTransactionAll.add(transaction.getAmount());
			transactionUI.setSolde(cptTransactionAll.doubleValue());

			// save in list
			transactionUIs.add(transactionUI);
			transactionById.put(transaction.getId(), transactionUI);
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
	public static Transaction mapTransactionRequest(TransactionRequest transactionRequest) {

		Transaction transaction = new Transaction();

		Date date;
		try {
			date = DateUtils.getFrenchDateFormat().parse(transactionRequest.getDate());
		} catch (ParseException | NullPointerException e) {
			throw new TransactionRequestNotValidException("date");
		}

		String categoryId = transactionRequest.getCategoryId();
		String subCategoryId = transactionRequest.getSubCategoryId();
		String partyId = transactionRequest.getPartyId();

		if ((transactionRequest.getDebit() != null && transactionRequest.getCredit() != null)
				|| (transactionRequest.getDebit() == null && transactionRequest.getCredit() == null)) {
			throw new TransactionRequestNotValidException("credit and debit");
		}

		BigDecimal amount = null;
		if (transactionRequest.getDebit() != null) {
			amount = NumberUtils.parseNumber(transactionRequest.getDebit());
			amount = amount.negate();
		} else {
			amount = NumberUtils.parseNumber(transactionRequest.getCredit());
		}

		String accountId = transactionRequest.getAccountId();
		// only to check and throw exception if account doesn't exist... (TODO change that)
		accountService.getAccountById(accountId);

		transaction.setAccountId(accountId);
		// Nb = (generated by service)
		transaction.setOfxId("(null)"); // not supported yet
		transaction.setDate(date);
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
	public static synchronized List<TransactionResponse> mapTransactions(
			List<Transaction> transactions) {

		transactionUIs = new ArrayList<>();
		transactionById = new HashMap<>();

		if (transactions != null && !transactions.isEmpty()) {

			cptTransactionAll = accountService
					.getInitialBalance(transactions.get(0).getAccountId());

			for (Transaction transaction : transactions) {
				mapTransaction(transaction);
			}
		}
		return transactionUIs;
	}

}
