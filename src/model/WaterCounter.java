package model;

public class WaterCounter extends Counter {

	private String name;
 

	public WaterCounter(String n) {
		this.name = n;
	}
 

	@Override
	public String toString() {
		return this.name;
	}

}
