package grisbiweb.server.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.mapstruct.factory.Mappers;

import grisbiweb.server.dto.PartyDto;
import grisbiweb.server.model.Party;
import grisbiweb.server.xml.model.PartyXml;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

public class PartyMapperTest {

	private PartyMapper mapper = Mappers.getMapper(PartyMapper.class);

	private PodamFactory podam = new PodamFactoryImpl();

	@Test
	public void should_mapper_map_party_list() {
		List<Party> parties = new ArrayList<>();
		Party party = podam.manufacturePojo(Party.class);
		party.setId("1234");
		parties.add(party);

		List<PartyDto> partiesDto = mapper.partyToPartyDto(parties);

		assertThat(partiesDto.size()).isEqualTo(1);
		assertThat(partiesDto.get(0).getName()).as("name").isEqualTo(party.getName());
		assertThat(partiesDto.get(0).getId()).as("id").isEqualTo(party.getIdLong());
		assertThat(partiesDto.get(0).getDescription()).as("description").isEqualTo(party.getDescription());
	}

	@Test
	public void should_mapper_map_partyxml_list() {
		List<PartyXml> partiesXml = new ArrayList<>();
		PartyXml partyXml = podam.manufacturePojo(PartyXml.class);
		partyXml.setNb("123");
		partiesXml.add(partyXml);

		List<Party> parties = mapper.partyXmlToParty(partiesXml);

		assertThat(parties.size()).isEqualTo(1);
		assertThat(parties.get(0).getName()).as("name").isEqualTo(partyXml.getNa());
		assertThat(parties.get(0).getId()).as("id").isEqualTo(partyXml.getNb());
		assertThat(parties.get(0).getDescription()).as("description").isEqualTo(partyXml.getTxt());
	}

	@Test
	public void should_mapper_map_party_dto_to_party_xml() {
		PartyDto partyDto = podam.manufacturePojo(PartyDto.class);

		PartyXml partyXml = mapper.partyDtoToPartyXml(partyDto);

		assertThat(partyXml.getNa()).isEqualTo(partyDto.getName());
		assertThat(partyXml.getNb()).isEqualTo(String.valueOf(partyDto.getId()));
		assertThat(partyXml.getSearch()).isEqualTo("");
		assertThat(partyXml.getTxt()).isEqualTo(partyDto.getDescription());
	}

	@Test
	public void should_mapper_map_party_to_party_xml() {
		Party party = podam.manufacturePojo(Party.class);

		PartyXml partyXml = mapper.partyToPartyXml(party);

		assertThat(partyXml.getNa()).isEqualTo(party.getName());
		assertThat(partyXml.getNb()).isEqualTo(String.valueOf(party.getId()));
		assertThat(partyXml.getSearch()).isEqualTo("");
		assertThat(partyXml.getTxt()).isEqualTo(party.getDescription());
	}
	
	@Test
	public void should_mapper_map_party_to_party_xml_and_set_description_to_empty_string() {
		Party party = podam.manufacturePojo(Party.class);
		party.setDescription(null);
		
		PartyXml partyXml = mapper.partyToPartyXml(party);

		assertThat(partyXml.getNa()).isEqualTo(party.getName());
		assertThat(partyXml.getNb()).isEqualTo(String.valueOf(party.getId()));
		assertThat(partyXml.getSearch()).isEqualTo("");
		assertThat(partyXml.getTxt()).isEqualTo("");
	}
	
}
