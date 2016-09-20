package online.zhaopei.myproject.jerseyservice;

import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import online.zhaopei.myproject.resource.UserResource;

@Component
@Path("/u")
public class UserEndpoint {
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	public UserResource getUser() {
		UserResource ur = new UserResource();
		ur.setId(new Random().nextInt());
		ur.setUserName("username-1");
		ur.setDesc("desc-1");
		return ur;
	}
}
