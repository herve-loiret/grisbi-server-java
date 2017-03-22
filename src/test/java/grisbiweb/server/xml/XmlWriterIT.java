package grisbiweb.server.xml;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import grisbiweb.server.mapper.PartyMapper;
import grisbiweb.server.model.Party;
import grisbiweb.server.service.PartyService;
import grisbiweb.server.utils.TestHelper;
import grisbiweb.server.xml.model.PartyXml;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlWriterIT {

	@Autowired
	private XmlWriter xmlWriter;

	@Autowired
	private PartyService partyService;

	@Autowired
	private PartyMapper partyMapper;

	@Test
	public void should_update_party_work() {
		PartyXml partyXml = TestHelper.manufacture(PartyXml.class);
		partyXml.setNb(partyService.getParties().get(3).getId());
		Party mappedParty = partyMapper.partyXmlToParty(partyXml);
		xmlWriter.updateParty(partyXml);

		Party retrievedParty = partyService.getPartyById(partyXml.getNb());

		Assertions.assertThat(retrievedParty).isEqualTo(mappedParty);
	}

}
