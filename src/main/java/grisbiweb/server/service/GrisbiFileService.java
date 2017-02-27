package grisbiweb.server.service;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import grisbiweb.server.config.GrisbiwebConfiguration;
import grisbiweb.server.exception.ConfigFileException;

@Service
public class GrisbiFileService {

    private static final String CLASSPATH_PREFIX = "classpath:";
    private static final String FILE_PREFIX = "file:";

    @Autowired
    private GrisbiwebConfiguration grisbiwebConfiguration;

    public File getGrisbiFile() {

        String fileUri = grisbiwebConfiguration.getFileUri();

        if (StringUtils.isEmpty(fileUri)) {
            throw new ConfigFileException("The grisbi file uri is not set in the config");
        }

        File file = null;

        if (fileUri.startsWith(CLASSPATH_PREFIX)) {
            file = openClasspathFile(fileUri);
        } else if (fileUri.startsWith(FILE_PREFIX)) {
            file = new File(fileUri.replaceFirst(FILE_PREFIX, ""));
        }

        return file;

    }

    private File openClasspathFile(String fileUri) {
        File file = null;
        try {
            String trimed = fileUri.replaceFirst(CLASSPATH_PREFIX, "");
            ClassPathResource classPathResource = new ClassPathResource(trimed);
            file = classPathResource.getFile();
        } catch (IOException e) {
            throw new ConfigFileException("can not read grisbi file uri : " + fileUri, e);
        }
        return file;
    }
}
