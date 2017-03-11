package grisbiweb.server.controller.it;

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
import grisbiweb.server.controller.CurrencyController;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
@Import({ WebMvcConfiguration.class })
public class CurrencyControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void should_return_list_of_currencies() {

        mockMvc.perform(get("/currencies")) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$", hasSize(2))) //
                .andExpect(jsonPath("$[0].id", is(1))) //
                .andExpect(jsonPath("$[0].name", is("Euro"))) //
                .andExpect(jsonPath("$[0].sign", is("€")));
    }

}
