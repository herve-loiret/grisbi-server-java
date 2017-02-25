package grisbiweb.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.mapper.TransactionMapper;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.xml.GrisbiXmlManager;
import grisbiweb.server.xml.XmlWriter;
import grisbiweb.server.xml.model.TransactionXml;

@Service
public class TransactionService {

    @Autowired
    private GrisbiXmlManager grisbiXmlManager;
    
    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private XmlWriter xmlWriter;

    public void createTransaction(Transaction transaction) {
        transaction.setIdLong(findNextTransactionId());
        xmlWriter.writeTransaction(transaction);
    }

    private Long findNextTransactionId() {
        Long id = 0L;
        for (Transaction transaction : this.getTransactions()) {
            if (id.compareTo(transaction.getIdLong()) < 0) {
                id = transaction.getIdLong();
            }
        }
        return ++id;
    }

    public Transaction getForeignTransaction(Transaction transaction) {
        return this.getTransactionById(transaction.getForeignTransactionId());
    }

    public Transaction getTransactionById(String idTransaction) {
        for (TransactionXml oneTransaction : grisbiXmlManager.loadGrisbi().getTransaction()) {
            Transaction transaction = transactionMapper.transactionXmlToTransaction(oneTransaction);
            if (transaction.getId().equals(idTransaction)) {
                return transaction;
            }
        }
        return null;
    }

    private List<Transaction> getTransactions() {
        List<TransactionXml> transactionsXml = grisbiXmlManager.loadGrisbi().getTransaction();
        List<Transaction> transactions = new ArrayList<>();
        for (TransactionXml transactionXml : transactionsXml) {
            transactions.add(transactionMapper.transactionXmlToTransaction(transactionXml));
        }
        return transactions;
    }

    public List<Transaction> getTransactionsOrderedByAccountId(String accountId) {
        return getTransactionsOrderedByAccountId(accountId, null, null);
    }

    public List<Transaction> getTransactionsOrderedByAccountId(String accountId, Integer page, Integer perPage) {

        List<Transaction> transactionsAccount = new ArrayList<>();
        List<Transaction> transactions = this.getTransactions();

        for (Transaction transaction : transactions) {
            if (transaction.getAccountId().equals(accountId)) {
                transactionsAccount.add(transaction);
            }
        }

        Collections.sort(transactionsAccount, new TransactionGwsComparator());

        // manage pagination
        if (page != null && perPage != null) {
            int from = (page - 1) * perPage;
            if (from > transactionsAccount.size()) {
                throw new RuntimeException("This page doesn't exist !");
            }

            int to = from + perPage;
            if (to > transactionsAccount.size()) {
                to = transactionsAccount.size();
            }
            transactionsAccount = transactionsAccount.subList(from, to);
        }

        return transactionsAccount;
    }

}
