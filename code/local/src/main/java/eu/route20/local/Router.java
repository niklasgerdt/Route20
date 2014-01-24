package eu.route20.local;

public interface Router<E> {

	void event(E event);

	void register(Subscriber<E> sub);

	void unRegister(Subscriber<E> sub);
}
