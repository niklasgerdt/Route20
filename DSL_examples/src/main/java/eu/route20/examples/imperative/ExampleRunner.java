package eu.route20.examples.imperative;

public class ExampleRunner {
	
	public static void main(String[] args) {
		EventListener loggingListener = new LoggingTemperatureEventListener();
		EventListener averagingListener = new AveragingTemperatureEventListener();
		Router router = new Router();
		router.registerListener(loggingListener);
		router.registerListener(averagingListener);

		Event event1 = new TemperatureEvent("kitchen", 21);
		Event event2 = new TemperatureEvent("living room", 19);
		router.push(event1);
		router.push(event2);	
	}
}