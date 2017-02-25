package grisbiweb.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "grisbiweb.server")
public class GrisbiWebServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrisbiWebServerApplication.class, args);
    }

}
