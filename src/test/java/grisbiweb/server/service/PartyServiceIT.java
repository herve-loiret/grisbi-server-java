package grisbiweb.server.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Random;

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
		List<Party> parties = partyService.getParties();
		int initialSize = parties.size();
		Party party = parties.get(new Random().nextInt(parties.size()));

		partyService.deleteParty(party.getId());

		assertThat(partyService.getPartyById(party.getId())).isNull();
		partyService.createParty(party);
		assertThat(partyService.getParties().size()).isEqualTo(initialSize);
	}

}
