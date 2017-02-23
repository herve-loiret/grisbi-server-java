package grisbiweb.server.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.model.TransactionOld;
import grisbiweb.server.xml.GrisbiXmlManager;
import grisbiweb.server.xml.XmlWriter;
import grisbiweb.server.xml.model.TransactionXml;

@Service
public class TransactionService {

    @Autowired
    private GrisbiXmlManager grisbiXmlManager;

    private XmlWriter xmlWriter = XmlWriter.INSTANCE;

    public void createTransaction(TransactionOld transactionOld) {
        transactionOld.setId(findNextTransactionId());
        xmlWriter.writeTransaction(transactionOld);
    }

    private Long findNextTransactionId() {
        Long id = 0L;
        for (TransactionOld transactionOld : this.getTransactions()) {
            if (id.compareTo(transactionOld.getIdLong()) < 0) {
                id = transactionOld.getIdLong();
            }
        }
        return ++id;
    }

    public TransactionOld getForeignTransaction(TransactionOld transactionOld) {
        return this.getTransactionById(transactionOld.getForeignTransactionId());
    }

    public TransactionOld getTransactionById(String idTransaction) {
        for (TransactionXml oneTransaction : grisbiXmlManager.loadGrisbi().getTransaction()) {
            TransactionOld transactionOld = new TransactionOld(oneTransaction);
            if (transactionOld.getId().equals(idTransaction)) {
                return transactionOld;
            }
        }
        return null;
    }

    private List<TransactionOld> getTransactions() {
        List<TransactionXml> transactionsXml = grisbiXmlManager.loadGrisbi().getTransaction();
        List<TransactionOld> transactionOlds = new ArrayList<>();
        for (TransactionXml transactionXml : transactionsXml) {
            transactionOlds.add(new TransactionOld(transactionXml));
        }
        return transactionOlds;
    }

    public List<TransactionOld> getTransactionsOrderedByAccountId(String accountId) {
        return getTransactionsOrderedByAccountId(accountId, null, null);
    }

    public List<TransactionOld> getTransactionsOrderedByAccountId(String accountId, Integer page, Integer perPage) {

        List<TransactionOld> transactionsAccount = new ArrayList<>();
        List<TransactionOld> transactionOlds = this.getTransactions();

        for (TransactionOld transactionOld : transactionOlds) {
            if (transactionOld.getAccountId().equals(accountId)) {
                transactionsAccount.add(transactionOld);
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
