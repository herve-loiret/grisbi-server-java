package grisbiweb.server.service;

import java.util.Comparator;
import java.util.Date;

import grisbiweb.server.model.Transaction;

/**
 * Permet de trier les transactions par leur date
 * 
 */
public class TransactionGwsComparator implements Comparator<Transaction> {

	@Override
	public int compare(Transaction transaction1, Transaction transaction2) {
		Date date1 = transaction1.getDate();
		Date date2 = transaction2.getDate();
		return date1.compareTo(date2);
	}

}
