package eu.route20.local;

import java.util.*;

public class BlockingRouter<E> implements Router<E> {
	private List<Subscriber<E>> subs = new ArrayList<Subscriber<E>>();

	@Override
	public synchronized void event(E event) {
		for (Subscriber<E> s : subs)
			s.onEvent(event);
	}

	@Override
	public synchronized void register(Subscriber<E> sub) {
		subs.add(sub);
	}

	@Override
	public synchronized void unRegister(Subscriber<E> sub) {
		subs.remove(sub);
	}
}
