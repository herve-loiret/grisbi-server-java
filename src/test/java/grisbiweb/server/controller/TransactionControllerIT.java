package grisbiweb.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import grisbiweb.server.config.WebMvcConfiguration;
import grisbiweb.server.dto.ListTransactionDto;
import grisbiweb.server.dto.TransactionDto;
import grisbiweb.server.utils.TestHelper;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
@Import({ WebMvcConfiguration.class })
public class TransactionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void should_return_transaction_by_account() {

        mockMvc.perform(get("/transactions/{accountId}", "1")) //
                .andExpect(jsonPath("$.totalItem", is(56)))//
                .andExpect(jsonPath("$.transactionsResponse[0].id", is(30))) //
                .andExpect(jsonPath("$.transactionsResponse[0].date", is(1317333600000L))) //
                .andExpect(jsonPath("$.transactionsResponse[0].party", is("Solde d'ouverture"))) //
                .andExpect(jsonPath("$.transactionsResponse[0].debit", isEmptyOrNullString())) //
                .andExpect(jsonPath("$.transactionsResponse[0].credit", is(1500.0))) //
                .andExpect(jsonPath("$.transactionsResponse[0].solde", is(1500.0)))
                .andExpect(jsonPath("$.transactionsResponse[0].category", is("Revenus : Divers")))
                .andExpect(jsonPath("$.transactionsResponse[0].currencyId", isEmptyOrNullString()))
                .andExpect(jsonPath("$.transactionsResponse[0].pr", is("true")))
                .andExpect(jsonPath("$.transactionsResponse[0].subTransactions", IsEmptyCollection.empty()))
                .andExpect(jsonPath("$.transactionsResponse[0].creditUI", is("1500.0 €")))
                .andExpect(jsonPath("$.transactionsResponse[0].dateUI", is("09/30/2011")))
                .andExpect(jsonPath("$.transactionsResponse[0].soldeUI", is("1500.0 €")));
    }

    @Test
    @SneakyThrows
    public void should_get_transactions_and_amount_should_work() {

        MvcResult mvcResult = mockMvc.perform(get("/transactions/{accountId}", "1"))
                .andExpect(status().isOk())
                .andReturn();

        ListTransactionDto listTransactionDto = TestHelper.MAPPER
                .readValue(mvcResult.getResponse().getContentAsString(), ListTransactionDto.class);

        List<TransactionDto> transactionsDto = listTransactionDto.getTransactionsResponse();
        assertThat(transactionsDto.get(0).getSolde()).isEqualTo(1500);
        assertThat(transactionsDto.get(1).getSolde()).isEqualTo(1478);
        assertThat(transactionsDto.get(2).getSolde()).isEqualTo(978);
        assertThat(transactionsDto.get(3).getSolde()).isEqualTo(708);
        assertThat(transactionsDto.get(4).getSolde()).isEqualTo(508);
        assertThat(transactionsDto.get(5).getSolde()).isEqualTo(4008);
        assertThat(transactionsDto.get(6).getSolde()).isEqualTo(3258);
        assertThat(transactionsDto.get(7).getSolde()).isEqualTo(2841.33);
        assertThat(transactionsDto.get(8).getSolde()).isEqualTo(2424.66);
        assertThat(transactionsDto.get(9).getSolde()).isEqualTo(2007.99);
        assertThat(transactionsDto.get(10).getSolde()).isEqualTo(1591.32);
        assertThat(transactionsDto.get(11).getSolde()).isEqualTo(1174.65);
        assertThat(transactionsDto.get(12).getSolde()).isEqualTo(757.98);
        assertThat(transactionsDto.get(13).getSolde()).isEqualTo(341.31);
        assertThat(transactionsDto.get(14).getSolde()).isEqualTo(-75.36);
        assertThat(transactionsDto.get(15).getSolde()).isEqualTo(-492.03);
        assertThat(transactionsDto.get(16).getSolde()).isEqualTo(-908.70);
        assertThat(transactionsDto.get(17).getSolde()).isEqualTo(-1325.37);
        assertThat(transactionsDto.get(18).getSolde()).isEqualTo(-1742.04);
        assertThat(transactionsDto.get(19).getSolde()).isEqualTo(-2158.71);
        assertThat(transactionsDto.get(55).getSolde()).isEqualTo(-17158.83);
    }

    @Test
    @SneakyThrows
    public void should_get_transactions_paginated_and_amount_should_still_work_page_1() {

        MvcResult mvcResult = mockMvc
                .perform(get("/transactions/{accountNumber}/page/{page}/perpage/{perpage}", "1", "1", "10"))
                .andExpect(status().isOk())
                .andReturn();

        ListTransactionDto listTransactionDto = TestHelper.MAPPER
                .readValue(mvcResult.getResponse().getContentAsString(), ListTransactionDto.class);

        List<TransactionDto> transactionsDto = listTransactionDto.getTransactionsResponse();
        assertThat(transactionsDto.size()).isEqualTo(10);
        assertThat(listTransactionDto.getTotalItem()).isEqualTo(56L);
        assertThat(transactionsDto.get(0).getSolde()).isEqualTo(1500);
        assertThat(transactionsDto.get(1).getSolde()).isEqualTo(1478);
        assertThat(transactionsDto.get(2).getSolde()).isEqualTo(978);
        assertThat(transactionsDto.get(3).getSolde()).isEqualTo(708);
        assertThat(transactionsDto.get(4).getSolde()).isEqualTo(508);
        assertThat(transactionsDto.get(5).getSolde()).isEqualTo(4008);
        assertThat(transactionsDto.get(6).getSolde()).isEqualTo(3258);
        assertThat(transactionsDto.get(7).getSolde()).isEqualTo(2841.33);
        assertThat(transactionsDto.get(8).getSolde()).isEqualTo(2424.66);
        assertThat(transactionsDto.get(9).getSolde()).isEqualTo(2007.99);

    }

    @Test
    @SneakyThrows
    public void should_get_transactions_paginated_and_amount_should_still_work_page_2() {

        MvcResult mvcResult = mockMvc
                .perform(get("/transactions/{accountNumber}/page/{page}/perpage/{perpage}", "1", 2, 10))
                .andExpect(status().isOk())
                .andReturn();

        ListTransactionDto listTransactionDto = TestHelper.MAPPER
                .readValue(mvcResult.getResponse().getContentAsString(), ListTransactionDto.class);

        List<TransactionDto> transactionsDto = listTransactionDto.getTransactionsResponse();
        assertThat(transactionsDto.size()).isEqualTo(10);
        assertThat(listTransactionDto.getTotalItem()).isEqualTo(56L);
        assertThat(transactionsDto.get(0).getSolde()).isEqualTo(1591.32);
        assertThat(transactionsDto.get(1).getSolde()).isEqualTo(1174.65);
        assertThat(transactionsDto.get(2).getSolde()).isEqualTo(757.98);
        assertThat(transactionsDto.get(3).getSolde()).isEqualTo(341.31);
        assertThat(transactionsDto.get(4).getSolde()).isEqualTo(-75.36);
        assertThat(transactionsDto.get(5).getSolde()).isEqualTo(-492.03);
        assertThat(transactionsDto.get(6).getSolde()).isEqualTo(-908.70);
        assertThat(transactionsDto.get(7).getSolde()).isEqualTo(-1325.37);
        assertThat(transactionsDto.get(8).getSolde()).isEqualTo(-1742.04);
        assertThat(transactionsDto.get(9).getSolde()).isEqualTo(-2158.71);
    }

}
