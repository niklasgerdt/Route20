package eu.route20.examples.dsl;

import eu.route20.examples.imperative.*;
import java.util.List;
import java.util.ArrayList;

public class Route {
	private Router router;

	private Route(List<EventListener> listeners){
		router = new Router();
		for (EventListener listener: listeners) {
			router.registerListener(listener);
		}
	}

	public void routeEvent(Event e){
		router.push(e);
	}

	public static class Builder {
		private List<EventListener> listeners = new ArrayList<EventListener>();

		public Builder(){
		}

		Builder withEventListener(EventListener listener){
			listeners.add(listener);
			return this;
		}

		Route build(){
			return new Route(listeners);
		}
	}
}