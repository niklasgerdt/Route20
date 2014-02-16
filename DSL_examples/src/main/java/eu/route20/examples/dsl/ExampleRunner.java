package eu.route20.examples.dsl;

import eu.route20.examples.imperative.*;

public class ExampleRunner {
	
	public static void main(String[] args) {
		EventListener loggingListener = new LoggingTemperatureEventListener();
		EventListener averagingListener = new AveragingTemperatureEventListener();
		Route route = new Route.Builder()
		                       .withEventListener(loggingListener)
		                       .withEventListener(averagingListener)
		                       .build();
		
		Event event1 = new TemperatureEvent("kitchen", 21);
		Event event2 = new TemperatureEvent("living room", 19);
		route.routeEvent(event1);
		route.routeEvent(event2);	
	}
}