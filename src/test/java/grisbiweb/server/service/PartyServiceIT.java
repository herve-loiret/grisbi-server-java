package grisbiweb.server.service;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import grisbiweb.server.model.Party;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PartyServiceIT {

    @Autowired
    private PartyService partyService;

    @Test
    public void should_delete_party_work() {
    	Party party = partyService.getParties().get(4);
    	
    	partyService.deleteParty(party.getId());
    	
    	Party retrievedParty = partyService.getPartyById(party.getId());
    	Assertions.assertThat(retrievedParty).isNull();
    }

}
