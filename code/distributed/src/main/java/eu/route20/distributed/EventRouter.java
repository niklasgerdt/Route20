package eu.route20.distributed;

import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

@Path("/route")
public class EventRouter {
	private static final String JSON = "application/json";

	@POST
	@Consumes(JSON)
	public void onEvent() {
		Client c = ClientBuilder.newClient();
		String subUrl = c.target("http://localhost:8080/routes/r20/routes/test").request(MediaType.APPLICATION_JSON).get(String.class);
		Client cc = ClientBuilder.newClient();
		cc.target(subUrl).request(MediaType.APPLICATION_JSON).async().post(null);
		System.out.println("onEvent" + subUrl);

		// get urls-subs from routes
		// audit log
		// send to subs
		// omit dead clients -> routes
	}
}
