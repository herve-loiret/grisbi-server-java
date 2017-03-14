package grisbiweb.server.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import grisbiweb.server.config.WebMvcConfiguration;
import grisbiweb.server.controller.TransactionController;

@RunWith(SpringRunner.class)
@WebMvcTest(TransactionController.class)
@Import({ WebMvcConfiguration.class })
public class TransactionControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_transaction_by_account() throws Exception {

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
                .andExpect(jsonPath("$.transactionsResponse[0].pr", isEmptyOrNullString()))
                .andExpect(jsonPath("$.transactionsResponse[0].subTransactions", IsEmptyCollection.empty()))
                .andExpect(jsonPath("$.transactionsResponse[0].creditUI", is("1500.0 €")))
                .andExpect(jsonPath("$.transactionsResponse[0].dateUI", is("09/30/2011")))
                .andExpect(jsonPath("$.transactionsResponse[0].soldeUI", is("1500.0 €")));
    }

}
