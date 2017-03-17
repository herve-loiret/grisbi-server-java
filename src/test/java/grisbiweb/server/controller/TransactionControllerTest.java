package grisbiweb.server.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;

import grisbiweb.server.dto.PartyDto;
import grisbiweb.server.dto.TransactionCreationDto;
import grisbiweb.server.model.Transaction;
import grisbiweb.server.rest.mapper.TransactionMapper;
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
    
    @Autowired
    private TransactionMapper transactionMapper;

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void should_return_list_of_parties() {
        TransactionCreationDto transactionCreationDto = TestHelper.manufacture(TransactionCreationDto.class);
        Transaction transaction = transactionMapper.mapTransactionRequest(transactionCreationDto);

        
        String result = mockMvc.perform(post("/transactions").content(TestHelper.serialize(transactionCreationDto)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<PartyDto> partyDtoOutputs = TestHelper.MAPPER.readValue(result,
                new TypeReference<List<PartyDto>>() {
                });

    }

}
