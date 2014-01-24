package eu.route20.api;

import org.junit.*;
import org.mockito.*;
import route20.local.*;

public class R20Test {
	@Mock
	private Subscriber<Double> sub;
	@Mock
	private Subscriber<Double> subAlt;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		R20.flushRouters();
	}

	@Test(expected = NullPointerException.class)
	public void nullCheckForEventType() {
		R20.initBlockingRouter(null);
	}

	@Test
	public void createsRouterForEventType() {
		R20.initBlockingRouter("".getClass());
		R20.initBlockingRouter(Integer.class);
	}

	@Test(expected = NullPointerException.class)
	public void nullCheckForSubscriberToEventTypeRegistering() {
		R20.registerToEventType(String.class, null);
	}

	@Test(expected = NullPointerException.class)
	public void nullCheckForEventTypeOnSubscriberRegistering() {
		R20.registerToEventType(null, sub);
	}

	@Test(expected = RouterNotInitializedException.class)
	public void cantRegisterSubscriberToUninitializedRouter() {
		R20.registerToEventType(Double.class, sub);
	}

	@Test
	public void firedEventIsRoutedToRegisteredSubscriber() {
		R20.initBlockingRouter(Double.class);
		R20.registerToEventType(Double.class, sub);
		Double e = Double.valueOf(250.0);
		R20.fire(e);
		Mockito.verify(sub).onEvent(e);
	}

	@Test
	public void firedEventIsRoutedToAllRegisteredSubscribers() {
		R20.initBlockingRouter(Double.class);
		R20.registerToEventType(Double.class, sub);
		R20.registerToEventType(Double.class, subAlt);
		Double e = Double.valueOf(250.0);
		R20.fire(e);
		Mockito.verify(sub).onEvent(e);
		Mockito.verify(subAlt).onEvent(e);
	}

	@Test
	public void subsequentEventTypedRoutersNotCreated() {
		R20.initBlockingRouter(Double.class);
		R20.registerToEventType(Double.class, sub);
		R20.initBlockingRouter(Double.class);
		Double e = Double.valueOf(250.0);
		R20.fire(e);
		Mockito.verify(sub).onEvent(e);
	}
}
