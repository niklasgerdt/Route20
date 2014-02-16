package eu.route20.examples.imperative;

import java.util.*;

public class Router{
	private List<EventListener> listeners = new ArrayList<EventListener>();

	public void registerListener(EventListener listener){
		listeners.add(listener);
	}

	public void push(Event e){
		for (EventListener listener: listeners) {
			listener.onEvent(e);
		}
	}
}
