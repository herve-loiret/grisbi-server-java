package grisbiweb.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import grisbiweb.server.controller.PartyController;

@SpringBootApplication
@ComponentScan(basePackageClasses = PartyController.class)
public class GrisbiWebServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrisbiWebServerApplication.class, args);
	}
}
