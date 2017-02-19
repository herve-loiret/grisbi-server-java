package grisbiweb.server.xml;

import grisbiweb.server.utils.FileUtils;
import grisbiweb.server.xml.model.GrisbiXml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public enum GrisbiXmlManager {

	INSTANCE;

	private final String BUSINESS_PACKAGE = "grisbiweb.server.xml.model";

	private GrisbiXml grisbi;

	private long lastModified;

	public GrisbiXml loadGrisbi() {
		if (grisbi == null) {
			reload();
		} else {
			File file = FileUtils.findGrisbiAccountFile();
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
			File file = FileUtils.findGrisbiAccountFile();
			lastModified = file.lastModified();
			grisbi = (GrisbiXml) unmarshaller.unmarshal(file);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
