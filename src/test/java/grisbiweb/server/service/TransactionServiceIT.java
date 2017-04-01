package grisbiweb.server.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import grisbiweb.server.model.Transaction;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionServiceIT {

	@Autowired
	private TransactionService transactionService;

	@Test
	public void should_get_transaction_by_id_return_the_correct_transaction() {
		Optional<Transaction> transaction = transactionService.getTransactionById("26");

		assertThat(transaction.get().getId()).isEqualTo(26L);
		assertThat(transaction.get().getDate()).isEqualTo(LocalDate.of(2011, 10, 10));
		assertThat(transaction.get().getAmount()).isEqualTo(new BigDecimal("416.67"));
	}

	@Test
	public void should_find_next_transaction_id_work() {
		long transactionId = transactionService.findNextTransactionId();

		assertThat(transactionId).isEqualTo(153L);
	}
}
