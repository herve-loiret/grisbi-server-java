package grisbiweb.server.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import grisbiweb.server.exception.AccountNotFoundException;
import grisbiweb.server.model.Account;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceIT {

    @Autowired
    private AccountService accountService;

    @Test(expected = AccountNotFoundException.class)
    public void should_get_account_by_id_throw_exception() {
        accountService.getAccountById("1223234");
    }

    @Test
    public void should_get_account_by_id_work() {
        String accountId = "1";
        Account account = accountService.getAccountById(accountId);
        assertThat(account.getId()).isEqualTo(accountId);
    }
}
