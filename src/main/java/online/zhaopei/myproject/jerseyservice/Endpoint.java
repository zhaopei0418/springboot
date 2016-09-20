package online.zhaopei.myproject.jerseyservice;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

@Component
@Path("/hello")
public class Endpoint {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String message() {
		return "hello!!";
	}
}
