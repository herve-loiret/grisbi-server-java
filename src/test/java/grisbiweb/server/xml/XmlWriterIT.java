package grisbiweb.server.xml;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import grisbiweb.server.utils.TestHelper;
import grisbiweb.server.xml.model.PartyXml;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlWriterIT {

    @Autowired
    private XmlWriter xmlWriter;

    @Test
    public void should_update_party_work() {
        PartyXml partyXml = TestHelper.manufacture(PartyXml.class);
        xmlWriter.updateParty(partyXml);
    }
}
