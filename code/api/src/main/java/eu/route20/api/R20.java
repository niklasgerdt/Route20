package eu.route20.api;

import java.util.*;
import eu.route20.local.*;

/**
 * Utility class for routers and subscribers. Abstracts the use of event routing
 * to simple method calls on this class.
 * 
 * Creating routes and subscribers example.
 * 
 * <pre class="code">
 * <code class="java>
 * public void
 * configureMyApplication() R20.initBlockingRouter(Double.class);
 * R20.registerToEventType(Double.class, sub); }
 * </code>
 * </pre>
 * 
 */
public final class R20 {
	private static final Map<Class<? extends Object>, Router<?>> ROUTERS = new HashMap<>();

	private R20() {
	}

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
		synchronized (ROUTERS) {
			if (!ROUTERS.containsKey(e))
				ROUTERS.put(e, new BlockingRouter<E>());
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
		synchronized (ROUTERS) {
			if (!ROUTERS.containsKey(e))
				throw new RouterNotInitializedException();
			@SuppressWarnings("unchecked")
			Router<E> r = (Router<E>) ROUTERS.get(e);
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
		synchronized (ROUTERS) {
			@SuppressWarnings("unchecked")
			Router<E> r = (Router<E>) ROUTERS.get(e.getClass());
			r.event(e);
		}
	}

	/**
	 * Flushes all routers for all event types
	 */
	public static void flushRouters() {
		synchronized (ROUTERS) {
			ROUTERS.clear();
		}
	}
}
