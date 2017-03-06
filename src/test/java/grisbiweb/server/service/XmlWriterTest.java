package grisbiweb.server.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import grisbiweb.server.xml.XmlWriter;
import grisbiweb.server.xml.model.PartyXml;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlWriterTest {

    @Autowired
    private XmlWriter xmlWriter;

    private PodamFactory factory = new PodamFactoryImpl();

    @Test
    public void should_create_xml_string_party_work() {
        PartyXml partyXml = factory.manufacturePojo(PartyXml.class);
        String partyString = xmlWriter.createXmlStringParty(partyXml);
    }
}
