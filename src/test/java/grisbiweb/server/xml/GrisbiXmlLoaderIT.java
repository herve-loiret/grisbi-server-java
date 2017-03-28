package grisbiweb.server.xml;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import grisbiweb.server.xml.model.GrisbiXml;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrisbiXmlLoaderIT {
    
    @Autowired
    private XmlReader xmlReader;

    @Test
    public void should_load_grisbi_unmarshal_the_file(){
        GrisbiXml grisbiXml = xmlReader.getGrisbi();
        
        assertThat(grisbiXml).isNotNull();
    }
    
}
