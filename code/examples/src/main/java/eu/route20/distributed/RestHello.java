package eu.route20.distributed;

import javax.ws.rs.*;

@Path("/id")
public class RestHello {
	private static final String JSON = "application/json";

	@POST
	@Consumes(JSON)
	@Produces(JSON)
	public String onEvent() {
		return "hello!";
	}
}
