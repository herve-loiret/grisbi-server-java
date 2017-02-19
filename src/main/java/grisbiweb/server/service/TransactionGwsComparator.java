package grisbiweb.server.service;

import grisbiweb.server.model.Transaction;

import java.util.Comparator;
import java.util.Date;

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
