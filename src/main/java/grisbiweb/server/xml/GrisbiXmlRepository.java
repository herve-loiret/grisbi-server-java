package grisbiweb.server.xml;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.mapper.PartyMapper;
import grisbiweb.server.model.Party;

@Service
public class GrisbiXmlRepository {

    @Autowired
    private GrisbiXmlLoader grisbiXmlLoader;

    @Autowired
    private PartyMapper partyMapper;

    public List<Party> getParties() {
        return partyMapper.partyXmlToParty(this.grisbiXmlLoader.loadGrisbi().getParty());
    }

}
