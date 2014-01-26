package eu.route20.examples.restsubscriber;

import javax.ws.rs.*;

@Path("/test")
public class RestSubscriber {
	private static final String JSON = "application/json";

	@POST
	@Consumes(JSON)
	public void subscriberUrls(/* id */) {
		System.out.println("received message @/testsubscriber");
	}
}
