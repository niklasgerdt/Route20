package eu.route20.routes;

import javax.ws.rs.*;

@Path("/{topic}")
public class Routes {
	private static final String JSON = "application/json";

	@GET
	@Consumes(JSON)
	@Produces(JSON)
	public String subscriberUrls(@PathParam("topic") String topic) {
		// TODO db logic, probably couchdb
		return "http://localhost:8080/examples/sub/" + topic;
	}
}
