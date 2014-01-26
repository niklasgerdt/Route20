package eu.route20.routes;

import static org.junit.Assert.*;
import org.junit.*;

public class RoutesTest {
	private Routes routes;

	@Before
	public void setup() {
		routes = new Routes();
	}

	@Test
	public void returnsTestSubUrl() {
		assertEquals("http://localhost:8080/examples/sub/" + "test", routes.subscriberUrls("test"));
	}
}
