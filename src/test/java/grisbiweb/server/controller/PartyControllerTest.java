package grisbiweb.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.type.TypeReference;

import grisbiweb.server.config.WebMvcConfiguration;
import grisbiweb.server.dto.PartyDto;
import grisbiweb.server.service.PartyService;
import grisbiweb.server.utils.TestHelper;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(PartyController.class)
@Import({ WebMvcConfiguration.class })
public class PartyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PartyService partyService;

    @Test
    @SneakyThrows
    @SuppressWarnings("unchecked")
    public void should_return_list_of_parties() {
        List<PartyDto> partyDtoInputs = TestHelper.FACTORY.manufacturePojo(ArrayList.class, PartyDto.class);
        when(partyService.getParties()).thenReturn(partyDtoInputs);

        String result = mockMvc.perform(get("/parties")) //
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        List<PartyDto> partyDtoOutputs = TestHelper.MAPPER.readValue(result,
                new TypeReference<List<PartyDto>>() {
                });

        assertThat(partyDtoInputs.size()).isEqualTo(partyDtoOutputs.size());
        partyDtoInputs.stream().forEach(partyInput -> assertThat(partyDtoOutputs.contains(partyInput)).isTrue());
    }

    @Test
    @SneakyThrows
    public void should_create_a_party() {
        PartyDto partyDtoInput = TestHelper.manufacture(PartyDto.class);
        PartyDto partyDtoOutput = TestHelper.manufacture(PartyDto.class);
        partyDtoInput.setId(null);
        String partyJson = TestHelper.serialize(partyDtoInput);
        when(partyService.createParty(partyDtoInput)).thenReturn(partyDtoOutput);

        mockMvc.perform(post("/parties", partyDtoInput).contentType(MediaType.APPLICATION_JSON).content(partyJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(partyDtoOutput.getId())))
                .andExpect(jsonPath("$.description", is(partyDtoOutput.getDescription())))
                .andExpect(jsonPath("$.name", is(partyDtoOutput.getName())));

    }

    @Test
    @SneakyThrows
    public void should_delete_a_party() {
        final long partyId = 123L;
        mockMvc.perform(delete("/parties/{partyId}", partyId)) //
                .andExpect(status().isOk());

        verify(partyService, times(1)).deleteParty(partyId);
    }
}
