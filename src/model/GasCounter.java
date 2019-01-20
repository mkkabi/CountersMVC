package model;

public class GasCounter extends Counter {

	private String name;
 

	public GasCounter(String n) {
		this.name = n;
	}
 

	@Override
	public String toString() {
		return this.name;
	}

}
