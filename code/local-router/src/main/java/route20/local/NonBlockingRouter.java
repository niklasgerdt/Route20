package route20.local;

import java.util.*;

public class NonBlockingRouter<E> implements Router<E> {
	private List<Subscriber<E>> subs = new ArrayList<>();
	private List<E> eventQ = new ArrayList<>();
	private String eventQueueLock = "eventQueueLock";

	public NonBlockingRouter(int eventQueueRouterThreads) {
		while (eventQueueRouterThreads > 0)
			new Thread(new Runnable() {
				public void run() {
					while (true) {
						synchronized (eventQ) {
							try {
								eventQ.wait();
							} catch (InterruptedException e1) {
								e1.printStackTrace();
							}
							E e = eventQ.remove(0);
							synchronized (subs) {
								for (Subscriber<E> s : subs)
									s.onEvent(e);
							}
						}
					}
				}
			}).start();
	}

	@Override
	public void event(E event) {
		synchronized (eventQ) {
			eventQ.add(event);
			eventQ.notify();
		}
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
