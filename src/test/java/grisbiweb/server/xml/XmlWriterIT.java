package grisbiweb.server.xml;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    private GrisbiXmlLoader grisbiXmlLoader;
    

    @Test
    public void should_update_party_work() {
        Party party = partyService.getParties().get(3);
        PartyXml partyXml = TestHelper.manufacture(PartyXml.class);
        partyXml.setNb(party.getId());
        System.out.println(partyXml);
        xmlWriter.updateParty(partyXml);
        
        fail();//TODO finish this IT test
        System.out.println(partyService.getParties());
    }
    
}
