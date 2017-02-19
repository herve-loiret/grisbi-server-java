package grisbiweb.server.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import grisbiweb.server.config.WebMvcConfiguration;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountController.class)
@Import({ WebMvcConfiguration.class })
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void should_delete_an_account() {
        mockMvc.perform(delete("/accounts/{accountNumber}", "1")).andExpect(status().isNotImplemented());
    }

    @Test
    @SneakyThrows
    public void should_get_one_account() {
        mockMvc.perform(get("/accounts/{accountId}", "1")) 
        .andExpect(jsonPath("$.id", is(1))) //
        .andExpect(jsonPath("$.name", is("Compte Monsieur"))) //
        .andExpect(jsonPath("$.typeAccount", is("BANK"))) //
        .andExpect(jsonPath("$.currencyId", is("1")));
        
    }

    @Test
    @SneakyThrows
    public void should_get_account_balance() {
        mockMvc.perform(get("/accounts/{accountId}/balance", "1")) //
                .andExpect(content().string("-17158.83"));
    }

    @Test
    @SneakyThrows
    public void should_get_account_reconciled_balance() {
        mockMvc.perform(get("/accounts/{accountId}/balance/reconciled", "1")) //
                .andExpect(content().string("2841.33"));
    }

    @Test
    @SneakyThrows
    public void should_get_all_accounts() {
        mockMvc.perform(get("/accounts/")) //
                .andExpect(jsonPath("$", hasSize(7)))//
                .andExpect(jsonPath("$[0].id", is(2))) //
                .andExpect(jsonPath("$[0].name", is("Emprunt voiture"))) //
                .andExpect(jsonPath("$[0].typeAccount", is("LIABILITY"))) //
                .andExpect(jsonPath("$[0].currencyId", is("1")));
    }

    @Test
    @SneakyThrows
    public void should_get_total_balance_by_account_type() {
        mockMvc.perform(get("/accounts/balance/{typeAccountStr}", "BANK")) //
                .andExpect(content().string("-38071.33"));
    }

    @Test
    @SneakyThrows
    public void should_get_reconciled_total_balance_by_account_type() {
        mockMvc.perform(get("/accounts/balance/reconciled/{typeAccountStr}", "BANK")) //
                .andExpect(content().string("3694.08"));
    }

    @Test
    @SneakyThrows
    public void should_create_an_account() {
        mockMvc.perform(post("/accounts")).andExpect(status().isNotImplemented());
    }

    @Test
    @SneakyThrows
    public void should_update_an_account() {
        mockMvc.perform(put("/accounts/1234")).andExpect(status().isNotImplemented());
    }

}
