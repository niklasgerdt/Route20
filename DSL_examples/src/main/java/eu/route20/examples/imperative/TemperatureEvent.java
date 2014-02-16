package eu.route20.examples.imperative;

public class TemperatureEvent implements Event {
	private String source;
	private int temperature;

	public TemperatureEvent(String source, int temperature){
         this.source = source;
         this.temperature = temperature;
	}

	public int getTemperature(){
		return temperature;
	}

	@Override
	public String topic(){
		return "temperature";
	}

	@Override
	public String toString(){
        return "Temperature: " + temperature + "@" + source;
	}
}