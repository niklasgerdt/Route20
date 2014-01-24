package eu.route20.local;

import static org.junit.Assert.*;
import org.junit.*;
import eu.route20.local.*;

public class NonBlockingRouterTest {
	private Router<String> router = new NonBlockingRouter<>(10);
	private boolean subCalled;
	private int calls;

	@Before
	public void setup() {
		subCalled = false;
		calls = 0;
	}

	@Test
	public void controlReturnsImmediatelyAfterCall()
			throws InterruptedException {
		Subscriber<String> s = new SlowSub<>();
		router.register(s);
		router.event("event");
		assertFalse(subCalled);
		Thread.sleep(1000);
		assertTrue(subCalled);
	}

	@SuppressWarnings("deprecation")
	@Test
	public void registeringStatusChangesWontBreakEventQueueThreads() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				Subscriber<String> s = new Sub<>();
				Subscriber<String> s2 = new Sub<>();
				while (true) {
					router.register(s);
					router.unRegister(s);
					router.register(s2);
					router.unRegister(s2);
				}
			}
		});
		t.start();
		int i = 0;
		while (i++ < 1000000)
			router.event("event");
		t.stop();
		System.out.println(calls);
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
			calls++;
			subCalled = true;
		}
	}
}
