package grisbiweb.server.service;

import grisbiweb.server.model.TransactionOld;

import java.util.Comparator;
import java.util.Date;

/**
 * Permet de trier les transactions par leur date
 * 
 */
public class TransactionGwsComparator implements Comparator<TransactionOld> {

	@Override
	public int compare(TransactionOld transaction1, TransactionOld transaction2) {
		Date date1 = transaction1.getDate();
		Date date2 = transaction2.getDate();
		return date1.compareTo(date2);
	}

}
