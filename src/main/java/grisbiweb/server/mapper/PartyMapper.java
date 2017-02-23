package grisbiweb.server.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import grisbiweb.server.model.Party;
import grisbiweb.server.rest.model.response.PartyResponse;
import grisbiweb.server.xml.model.PartyXml;

@Mapper(componentModel = "spring", uses = {})
public interface PartyMapper {

    List<PartyResponse> partyToPartyResponse(List<Party> party);

    @Mapping(source = "idLong", target = "id")
    PartyResponse partyToPartyResponse(Party party);

    @Mapping(source = "nb", target = "id")
    @Mapping(source = "na", target = "name")
    Party PartyXmlToParty(PartyXml partyXml);

}
