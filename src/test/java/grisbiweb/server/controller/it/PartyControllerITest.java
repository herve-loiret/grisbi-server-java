package grisbiweb.server.controller.it;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
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
import grisbiweb.server.controller.PartyController;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(PartyController.class)
@Import({ WebMvcConfiguration.class })
public class PartyControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void should_return_list_of_parties() {
        mockMvc.perform(get("/parties")) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$", hasSize(26)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].description", isEmptyOrNullString()))
                .andExpect(jsonPath("$[0].name", is("Huguette Lefacteur")));
    }

}
