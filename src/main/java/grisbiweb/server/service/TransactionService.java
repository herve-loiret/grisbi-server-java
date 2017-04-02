package grisbiweb.server.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.annotations.VisibleForTesting;

import grisbiweb.server.mapper.TransactionMapper;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.xml.XmlReader;
import grisbiweb.server.xml.XmlWriter;

@Service
public class TransactionService {

	@Autowired
	private XmlReader xmlReader;

	@Autowired
	private TransactionMapper transactionMapper;

	@Autowired
	private XmlWriter xmlWriter;

	public void createTransaction(Transaction transaction) {
		transaction.setId(findNextTransactionId());
		xmlWriter.writeTransaction(transaction);
	}

	@VisibleForTesting
	protected Long findNextTransactionId() {
		Long lastId = this.getTransactions().stream()
				.mapToLong(t -> t.getId())
				.max()
				.orElse(0L);
		return ++lastId;
	}

	public Optional<Transaction> getForeignTransaction(Transaction transaction) {
		return this.getTransactionById(transaction.getForeignTransactionId());
	}

	public Optional<Transaction> getTransactionById(String idTransaction) {
		return this.getTransactions().stream()
				.filter(t -> t.getId().equals(Long.valueOf(idTransaction)))
				.findFirst();
	}

	private List<Transaction> getTransactions() {
		return xmlReader.getGrisbi().getTransaction().stream()
				.map(transactionMapper::transactionXmlToTransaction)
				.collect(Collectors.toList());
	}

	public List<Transaction> getTransactionsOrderedByAccountId(String accountId) {
		return this.getTransactions().stream()
				.filter(t -> accountId.equals(t.getAccountId()))
				.sorted((t1, t2) -> t1.getDate().compareTo(t2.getDate()))
				.collect(Collectors.toList());
	}

}
