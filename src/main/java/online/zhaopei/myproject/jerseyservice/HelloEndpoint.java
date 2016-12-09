package online.zhaopei.myproject.jerseyservice;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import online.zhaopei.myproject.resource.HelloResource;

@Component
@Path("/v1/hello")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "Hello API - say hello the world", produces = "application/json")
public class HelloEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloEndpoint.class);

	@GET						//JAX-RS Annotation
	@Path("/{name}")	//JAX-RS Annotation
	@ApiOperation(				//Swagger Annotation
			value = "提供一个名称", 
			response = HelloResource.class)  
	@ApiResponses(value = {		//Swagger Annotation
		@ApiResponse(code = 200, message = "成功", response = HelloResource.class),
	    @ApiResponse(code = 404, message = "找不到资源")
	})
	public Response sayHelloByGet(@ApiParam @PathParam("name") String name) {
		LOGGER.info("v1/hello/{} - {}", name, MediaType.APPLICATION_JSON);
		return this.constructHelloResponse(name, MediaType.APPLICATION_JSON);
	}
	
	private Response constructHelloResponse(String name, String via) {
		if ("404".equals(name)) {
			return Response.status(Status.NOT_FOUND).build();
		}
		HelloResource result = new HelloResource();
		result.setMsg(String.format("Hello %s - %s", name, via));
		return Response.status(Status.OK).entity(result).build();
	}
}