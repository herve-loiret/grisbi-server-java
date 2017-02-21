package grisbiweb.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
//@ComponentScan(basePackages = "grisbiweb.server")
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

}
