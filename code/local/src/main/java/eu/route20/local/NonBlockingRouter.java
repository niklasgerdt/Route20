package route20.local;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NonBlockingRouter<E> implements Router<E> {
	private static final int CAPACITY = 1000 * 1000;
	private List<Subscriber<E>> subs = new ArrayList<>();
	private BlockingQueue<E> eventQ = new ArrayBlockingQueue<>(CAPACITY);

	public NonBlockingRouter(int eventQueueRouterThreads) {
		while (eventQueueRouterThreads-- > 0)
			runEventQueueThread();
	}

	private void runEventQueueThread() {
		new Thread(new Runnable() {
			public void run() {
				try {
					handleEvent();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			private void handleEvent() throws InterruptedException {
				while (true) {
					E e = eventQ.take();
					synchronized (subs) {
						routeEvent(e);
					}
				}
			}

			private void routeEvent(E e) {
				for (Subscriber<E> s : subs)
					s.onEvent(e);
			}
		}).start();
	}

	@Override
	public void event(E event) {
		eventQ.add(event);
	}

	@Override
	public void register(Subscriber<E> sub) {
		synchronized (subs) {
			subs.add(sub);
		}
	}

	@Override
	public void unRegister(Subscriber<E> sub) {
		synchronized (subs) {
			subs.remove(sub);
		}
	}
}
