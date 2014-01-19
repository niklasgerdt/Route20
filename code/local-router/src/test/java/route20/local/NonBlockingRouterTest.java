package route20.local;

import static org.junit.Assert.*;

import org.junit.*;

public class NonBlockingRouterTest {
	private Router<String> router = new NonBlockingRouter<>(10);
	private boolean subCalled;

	@Before
	public void setup() {
		subCalled = false;
	}

	@Test
	public void controlReturnsImmediatelyAfterCall() {
		Subscriber<String> s = new SlowSub<>();
		router.register(s);
		router.event("event");
		assertFalse(subCalled);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
		}
		assertTrue(subCalled);
	}

	class SlowSub<E> implements Subscriber<E> {
		@Override
		public void onEvent(E e) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
			}
			subCalled = true;
		}
	}

	class Sub<E> implements Subscriber<E> {
		@Override
		public void onEvent(E e) {
			subCalled = true;
		}
	}
}
