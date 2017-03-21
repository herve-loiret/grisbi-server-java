package grisbiweb.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.mapper.TransactionMapper;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.xml.GrisbiXmlLoader;
import grisbiweb.server.xml.XmlWriter;
import grisbiweb.server.xml.model.TransactionXml;

@Service
public class TransactionService {

	@Autowired
	private GrisbiXmlLoader grisbiXmlLoader;

	@Autowired
	private TransactionMapper transactionMapper;

	@Autowired
	private XmlWriter xmlWriter;

	public void createTransaction(Transaction transaction) {
		transaction.setId(findNextTransactionId());
		xmlWriter.writeTransaction(transaction);
	}

	private Long findNextTransactionId() {
		Long id = 0L;
		for (Transaction transaction : this.getTransactions()) {
			if (id.compareTo(transaction.getId()) < 0) {
				id = transaction.getId();
			}
		}
		return ++id;
	}

	public Transaction getForeignTransaction(Transaction transaction) {
		return this.getTransactionById(transaction.getForeignTransactionId());
	}

	public Transaction getTransactionById(String idTransaction) {
		for (TransactionXml oneTransaction : grisbiXmlLoader.getGrisbi().getTransaction()) {
			Transaction transaction = transactionMapper.transactionXmlToTransaction(oneTransaction);
			if (transaction.getId().equals(idTransaction)) {
				return transaction;
			}
		}
		return null;
	}

	private List<Transaction> getTransactions() {
		List<TransactionXml> transactionsXml = grisbiXmlLoader.getGrisbi().getTransaction();
		List<Transaction> transactions = new ArrayList<>();
		for (TransactionXml transactionXml : transactionsXml) {
			transactions.add(transactionMapper.transactionXmlToTransaction(transactionXml));
		}
		return transactions;
	}

	public List<Transaction> getTransactionsOrderedByAccountId(String accountId) {

		List<Transaction> transactionsAccount = new ArrayList<>();
		List<Transaction> transactions = this.getTransactions();

		for (Transaction transaction : transactions) {
			if (transaction.getAccountId().equals(accountId)) {
				transactionsAccount.add(transaction);
			}
		}

		Collections.sort(transactionsAccount,
				(transaction1, transaction2) -> transaction1.getDate().compareTo(transaction2.getDate()));

		return transactionsAccount;
	}

	public long getTransactionTotal(String accountId) {
		return this.getTransactions().stream().filter(transaction -> accountId.equals(transaction.getAccountId()))
				.count();
	}

}
