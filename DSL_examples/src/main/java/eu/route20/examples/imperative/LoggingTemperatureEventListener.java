package eu.route20.examples.imperative;

public class LoggingTemperatureEventListener implements EventListener {
	
	@Override
	public void onEvent(Event e){
		System.out.println(e.toString());
	}
}