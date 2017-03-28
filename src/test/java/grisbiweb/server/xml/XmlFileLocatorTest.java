package grisbiweb.server.xml;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import grisbiweb.server.config.GrisbiwebConfiguration;
import grisbiweb.server.xml.XmlFileLocator;
import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@SpringBootTest
public class XmlFileLocatorTest {

    @Mock
    private GrisbiwebConfiguration grisbiwebConfiguration;

    @Autowired
    @InjectMocks
    private XmlFileLocator xmlFileLocator;

    private static final String GRISBI_FILE_NAME = "account.gsb";

    @Test
    public void should_get_grisbi_file_work_with_classpath_resources() {
        when(grisbiwebConfiguration.getFileUri()).thenReturn("classpath:" + GRISBI_FILE_NAME);

        File file = xmlFileLocator.getGrisbiFile();

        assertThat(file).isNotNull();
        assertThat(file.exists()).isTrue();
    }

    @Test
    @SneakyThrows
    public void should_get_grisbi_file_work_with_file_uri() {
        ClassPathResource classPathResource = new ClassPathResource(GRISBI_FILE_NAME);
        File resourceFile = classPathResource.getFile();
        when(grisbiwebConfiguration.getFileUri()).thenReturn("file:" + resourceFile.getAbsolutePath());

        File file = xmlFileLocator.getGrisbiFile();

        assertThat(file).isNotNull();
        assertThat(file.exists()).isTrue();
    }
}
