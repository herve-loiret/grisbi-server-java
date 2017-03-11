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
import grisbiweb.server.controller.CurrencyController;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(CurrencyController.class)
@Import({ WebMvcConfiguration.class })
public class CategoryControllerITest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @SneakyThrows
    public void should_return_list_of_categories() {

        mockMvc.perform(get("/categories")) //
                .andExpect(status().isOk()) //
                .andExpect(jsonPath("$", hasSize(29))) //
                .andExpect(jsonPath("$[0].idCategory", is(33))) //
                .andExpect(jsonPath("$[0].idSubCategory", isEmptyOrNullString())) //
                .andExpect(jsonPath("$[0].nameCategory", is("Débit CB DD")))//
                .andExpect(jsonPath("$[0].nameSubCategory", isEmptyOrNullString()))//
                .andExpect(jsonPath("$[0].completeId", is("33")))//
                .andExpect(jsonPath("$[0].completeName", is("Débit CB DD")));
    }

}
