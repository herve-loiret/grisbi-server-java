package grisbiweb.server.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.service.GrisbiFileService;
import grisbiweb.server.xml.model.GrisbiXml;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GrisbiXmlManager {

    private final String BUSINESS_PACKAGE = "grisbiweb.server.xml.model";

    @Autowired
    private GrisbiFileService grisbiFileService;

    private GrisbiXml grisbi;

    private long lastModified;

    public GrisbiXml loadGrisbi() {
        if (grisbi == null) {
            reload();
        } else {
            File file = grisbiFileService.getGrisbiFile();
            if (lastModified < file.lastModified()) {
                reload();
            }
        }
        return grisbi;
    }

    private void reload() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BUSINESS_PACKAGE);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            File file = grisbiFileService.getGrisbiFile();
            lastModified = file.lastModified();
            grisbi = (GrisbiXml) unmarshaller.unmarshal(file);
        } catch (JAXBException e) {
            log.error("Error while accessing grisbi file", e);
            throw new RuntimeException(e);
        }
    }

}
