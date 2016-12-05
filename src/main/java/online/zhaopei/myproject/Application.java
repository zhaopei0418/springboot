package online.zhaopei.myproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

//	public Application() {
//		this.register(Endpoint.class);
//		this.register(UserEndpoint.class);
//	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

//	@Bean
//	public ServletRegistrationBean jerseyServlet() {
//		ServletRegistrationBean registration = new ServletRegistrationBean(new ServletContainer(), "/jersey");
//		registration.addInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, Application.class.getName());
//		return registration;
//	}

}
