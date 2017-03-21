package grisbiweb.server.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import grisbiweb.server.exception.GrisbiFileException;
import grisbiweb.server.xml.model.GrisbiXml;

@Service
public class GrisbiXmlLoader {

	private static final String BUSINESS_PACKAGE = "grisbiweb.server.xml.model";

	@Autowired
	private GrisbiXmlFileLocator grisbiXmlFileLocator;

	private GrisbiXml grisbi;

	private long lastModified;

	public GrisbiXml getGrisbi() {
		if (grisbi == null) {
			grisbi = loadGrisbi(grisbiXmlFileLocator.getGrisbiFile());
		} else {
			File file = grisbiXmlFileLocator.getGrisbiFile();
			if (lastModified < file.lastModified()) {
				grisbi = loadGrisbi(file);
			}
		}
		return grisbi;
	}

	public GrisbiXml loadGrisbi(File file) {
		GrisbiXml grisbi = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(BUSINESS_PACKAGE);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			lastModified = file.lastModified();
			grisbi = (GrisbiXml) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			throw new GrisbiFileException("Error while accessing grisbi file", e);
		}
		return grisbi;
	}

}
