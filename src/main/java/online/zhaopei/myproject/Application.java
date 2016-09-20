package online.zhaopei.myproject;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import online.zhaopei.myproject.jerseyservice.Endpoint;
import online.zhaopei.myproject.jerseyservice.UserEndpoint;

@SpringBootApplication
public class Application extends ResourceConfig {

	public Application() {
		this.register(Endpoint.class);
		this.register(UserEndpoint.class);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public ServletRegistrationBean jerseyServlet() {
		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/*");
		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, Application.class.getName());
		return registration;
	}

}
