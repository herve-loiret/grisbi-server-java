package grisbiweb.server.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import grisbiweb.server.dto.TransactionCreationDto;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.rest.mapper.TransactionMapper;
import grisbiweb.server.service.AccountService;
import grisbiweb.server.service.TransactionService;
import grisbiweb.server.utils.TestHelper;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @MockBean
    private AccountService accountService;

    @Autowired
    private TransactionMapper transactionMapper;

    @Test
    @SneakyThrows
    public void should_post_transactions_map_request() {
        TransactionCreationDto transactionCreationDto = TestHelper.manufacture(TransactionCreationDto.class);
        Transaction transaction = transactionMapper.mapTransactionRequest(transactionCreationDto);
        String json = TestHelper.serialize(transactionCreationDto);

        mockMvc.perform(post("/transactions").contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isCreated());

        verify(transactionService, times(1)).createTransaction(transaction);
    }

}
