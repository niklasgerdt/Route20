package eu.route20.examples.imperative;

import java.util.*;

public class Router {
	private List<EventListener> listeners = new ArrayList<EventListener>();
	private Map<String, List<EventListener>> topicListenerMap = new HashMap<String, List<EventListener>>();

	public void registerListener(EventListener listener) {
		listeners.add(listener);
	}
	
	public void registerListener(EventListener listener, String topic) {
		if (topicListenerMap.containsKey(topic)){
			List<EventListener> topicListeners = topicListenerMap.get(topic);
			topicListeners.add(listener);
		}
		else {
			List<EventListener> topicListeners = new ArrayList<EventListener>();
			topicListenerMap.put(topic, topicListeners);
		}
	}

	public void push(Event e) {
		for (EventListener listener : listeners) {
			listener.onEvent(e);
		}
	}
}
