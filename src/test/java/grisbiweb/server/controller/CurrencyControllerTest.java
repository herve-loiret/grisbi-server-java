package grisbiweb.server.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
@Import({ WebMvcConfiguration.class })
public class CurrencyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void should_return_list_of_currencies() throws Exception {

        mockMvc.perform(get("/currencies")) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$", hasSize(2))) //
                .andExpect(jsonPath("$[0].id", is(1))) //
                .andExpect(jsonPath("$[0].name", is("Euro"))) //
                .andExpect(jsonPath("$[0].sign", is("€")));
    }

}
