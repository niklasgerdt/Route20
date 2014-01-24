package eu.route20.api;

import java.util.*;
import route20.local.*;

public class R20 {
	private final static Map<Class<? extends Object>, Router<?>> routers = new HashMap<>();

	/**
	 * Creates a singleton {@link BlockingRouter} with event type <E>. Only one
	 * router per event type can be created so subsequent calls to this method
	 * has no meaning.
	 * 
	 * @param eventType
	 *            the type of the event
	 * @param <E>
	 *            the type of the event
	 */
	public static <E> void initBlockingRouter(Class<E> eventType) {
		final Class<E> e = Objects.requireNonNull(eventType);
		synchronized (routers) {
			if (!routers.containsKey(e))
				routers.put(e, new BlockingRouter<E>());
		}
	}

	/**
	 * Registers {@link Subscriber} to {@link Router} for given
	 * {@code eventType}
	 * 
	 * @param eventType
	 *            the type of the event
	 * @param subscriber
	 *            subscriber for events {@code eventType}
	 * @param <E>
	 *            the type of the event
	 * @throws RouterNotInitializedException
	 *             if can't find {@link Router} for {@code eventType}
	 */
	public static <E> void registerToEventType(Class<E> eventType, Subscriber<E> subscriber) {
		final Class<E> e = Objects.requireNonNull(eventType);
		final Subscriber<E> sub = Objects.requireNonNull(subscriber);
		synchronized (routers) {
			if (!routers.containsKey(e))
				throw new RouterNotInitializedException();
			@SuppressWarnings("unchecked")
			Router<E> r = (Router<E>) routers.get(e);
			r.register(sub);
		}
	}

	/**
	 * Routes the {@code event} to associated {@link Subscribers}
	 * 
	 * @param event
	 *            the event to be router to registered subscribers
	 * @param <E>
	 *            the type of the event
	 */
	public static <E> void fire(E event) {
		final E e = Objects.requireNonNull(event);
		synchronized (routers) {
			@SuppressWarnings("unchecked")
			Router<E> r = (Router<E>) routers.get(e.getClass());
			r.event(e);
		}
	}

	/**
	 * Flushes all routers for all event types
	 */
	public static void flushRouters() {
		synchronized (routers) {
			routers.clear();
		}
	}
}
