package grisbiweb.server.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import grisbiweb.server.dto.PartyDto;
import grisbiweb.server.model.Party;
import grisbiweb.server.xml.model.PartyXml;

@Mapper(componentModel = "spring", uses = {})
public interface PartyMapper {

    List<PartyDto> partyToPartyResponse(List<Party> party);

    @Mapping(source = "idLong", target = "id")
    PartyDto partyToPartyResponse(Party party);

    List<Party> partyXmlToParty(List<PartyXml> partiesXml);

    @Mapping(source = "nb", target = "id")
    @Mapping(source = "na", target = "name")
    Party partyXmlToParty(PartyXml partyXml);

}
