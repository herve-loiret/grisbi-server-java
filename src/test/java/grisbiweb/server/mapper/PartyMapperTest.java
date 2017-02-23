package grisbiweb.server.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import grisbiweb.server.model.Party;
import grisbiweb.server.rest.model.response.PartyResponse;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class PartyMapperTest {
    @Test
    public void should_mapper_map_party_list() {
        PartyMapper mapper = Mappers.getMapper(PartyMapper.class);
        PodamFactory podam = new PodamFactoryImpl();
        List<Party> parties = new ArrayList<>();
        Party party = podam.manufacturePojo(Party.class);
        party.setId("1234");
        parties.add(party);

        List<PartyResponse> partiesResponses = mapper.partyToPartyResponse(parties);

        assertThat(partiesResponses.size()).isEqualTo(1);
        assertThat(partiesResponses.get(0).getName()).as("name").isEqualTo(party.getName());
        assertThat(partiesResponses.get(0).getId()).as("sign").isEqualTo(party.getIdLong());
    }
}
