package model;

public class ElectricityCounter extends Counter {

	private String name;
 

	public ElectricityCounter(String n) {
		this.name = n;
	}
 

	@Override
	public String toString() {
		return this.name;
	}

}
