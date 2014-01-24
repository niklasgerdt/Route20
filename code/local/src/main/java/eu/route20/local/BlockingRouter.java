package route20.local;

import java.util.ArrayList;
import java.util.List;

public class BlockingRouter<E> implements Router<E> {
	private List<Subscriber<E>> subs = new ArrayList<Subscriber<E>>();

	@Override
	public void event(E event) {
		for (Subscriber<E> s : subs)
			s.onEvent(event);
	}

	@Override
	public void register(Subscriber<E> sub) {
		subs.add(sub);
	}

	@Override
	public void unRegister(Subscriber<E> sub) {
		subs.remove(sub);
	}

}
