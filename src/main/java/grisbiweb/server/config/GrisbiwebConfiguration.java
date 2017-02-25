package grisbiweb.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;

@Configuration
public class GrisbiwebConfiguration {

    @Getter
    @Value("${grisbiweb.fileuri}")
    private String fileUri;

}
