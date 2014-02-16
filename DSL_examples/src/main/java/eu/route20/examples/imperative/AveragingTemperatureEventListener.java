package eu.route20.examples.imperative;

public class AveragingTemperatureEventListener implements EventListener {
	private int sum;
	private int events;

	@Override
	public void onEvent(Event e){
		TemperatureEvent te = (TemperatureEvent)e;
		sum += te.getTemperature();
		events++;
		System.out.println("Average temperature: " + sum/events);
	}
}