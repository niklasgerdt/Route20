package eu.route20.distributed;

import javax.ws.rs.*;

@Path("hello")
public class RestHello {

	@GET
	@Produces("text/plain")
	public String hello() {
		return "hello";
	}
}
