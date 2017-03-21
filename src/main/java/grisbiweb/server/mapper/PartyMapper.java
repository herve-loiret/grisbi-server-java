package grisbiweb.server.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import grisbiweb.server.dto.PartyDto;
import grisbiweb.server.model.Party;
import grisbiweb.server.xml.model.PartyXml;

@Mapper(componentModel = "spring", uses = {})
public interface PartyMapper {

	List<PartyDto> partyToPartyDto(List<Party> party);

	@Mapping(source = "idLong", target = "id")
	PartyDto partyToPartyDto(Party party);

	List<Party> partyXmlToParty(List<PartyXml> partiesXml);

	@Mapping(source = "nb", target = "id")
	@Mapping(source = "na", target = "name")
	@Mapping(expression = "java(\"(null)\".equals(partyXml.getTxt()) ? null : partyXml.getTxt())", target = "description")
	Party partyXmlToParty(PartyXml partyXml);

	@Mapping(source = "name", target = "na")
	@Mapping(source = "id", target = "nb")
	@Mapping(constant = "", target = "search") // TODO retrieve the existing one
	@Mapping(source = "description", target = "txt")
	PartyXml partyDtoToPartyXml(PartyDto partyDto);
}
