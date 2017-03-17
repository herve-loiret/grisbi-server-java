package grisbiweb.server.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.File;
import java.io.FileInputStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.common.io.ByteStreams;

import lombok.SneakyThrows;

@RunWith(SpringRunner.class)
@WebMvcTest(FileController.class)
public class FileControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Value("classpath:account.gsb")
    private File file;

    @Test
    @SneakyThrows
    public void should_get_file_work() {
        byte[] response = mockMvc.perform(get("/files/download"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsByteArray();

        assertThat(response).isEqualTo(ByteStreams.toByteArray(new FileInputStream(file)));
    }
}
