package route20.local;

import static org.junit.Assert.*;

import org.junit.*;

public class BlockingRouterTest {
	private Router<String> router = new BlockingRouter<>();
	private boolean subCalled = false;

	@Before
	public void setup() {
		subCalled = false;
	}

	@Test
	public void registeredSubsAreInvokedOnEvent() {
		router.register(new Sub<String>());
		router.event("event");
		assertTrue(subCalled);
	}

	@Test
	public void unRegisteredSubsAreNotInvokedOnEvent() {
		Subscriber<String> s = new Sub<>();
		router.register(s);
		router.unRegister(s);
		router.event("event");
		assertFalse(subCalled);
	}

	class Sub<E> implements Subscriber<E> {
		@Override
		public void onEvent(E e) {
			subCalled = true;
		}
	}
}
