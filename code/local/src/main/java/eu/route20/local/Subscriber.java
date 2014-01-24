package eu.route20.local;

public interface Subscriber<E> {

	void onEvent(E e);
}
