package route20.local;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

public class R20 {
	private final static Map<String, Router<?>> routers = new TreeMap<>();

	/**
	 * Creates a singleton BlockingRouter with event type <E>. Router can be
	 * queried with the given name.
	 * 
	 * @param name
	 *            of the router
	 */
	public static <E> void createNamedSimpleRouter(String name) {
		if (!routers.containsKey(name)) {
			routers.put(name, new BlockingRouter<E>());
		}
	}

	/**
	 * Creates a cached singleton NonBlockingRouter with event type <E>. This
	 * router has 2 event router threads by default, if you wish to set
	 * different number of threads use method
	 * {@link #createNamedFastRouter(String, int)}. Router can be queried with
	 * the given name. Consequent calls to this method has no meaning.
	 * 
	 * @param name
	 *            of the router
	 */
	public static <E> void createNamedFastRouter(String name) {
		if (!routers.containsKey(name)) {
			routers.put(name, new NonBlockingRouter<E>(2));
		}
	}

	/**
	 * Creates a singleton NonBlockingRouter with event type <E>. This router
	 * has 2 event router threads by default, if you wish to set different
	 * number of threads use method {@link #createNamedFastRouter(String, int)}.
	 * Router can be queried with the given name.
	 * 
	 * @param name
	 *            of the router
	 */
	public static <E> void createNamedFastRouter(String name,
			int eventRoutingThreads) {
		if (!routers.containsKey(name)) {
			routers.put(name, new NonBlockingRouter<E>(2));
		}
	}

	public static Map<String, Router<?>> routers() {
		return Collections.unmodifiableMap(routers);
	}

	@SuppressWarnings("unchecked")
	public static <E> Router<E> router(String name)
			throws UnknownRouterException {
		if (routers.containsKey(name))
			return (Router<E>) routers.get(name);
		throw new UnknownRouterException("Unknown router " + name);
	}

	/**
	 * Creates and returns a singleton NonBlockingRouter for event's type.
	 * 
	 * @return Router
	 */
	public static <E> Router<E> fastRouter() {
		return new NonBlockingRouter<>(2);
	}

	/**
	 * Creates and returns a singleton NonBlockingRouter for event's type. This
	 * method accepts a wish for the number of routing threads.
	 * 
	 * @param eventRoutingThreads
	 *            Number of routing threads. Lower bound is > 0 and upper bound
	 *            is < 1000.
	 * @return Router
	 */
	public static <E> Router<E> fastRouter(int eventRoutingThreads) {
		return new NonBlockingRouter<>(eventRoutingThreads);
	}
}
