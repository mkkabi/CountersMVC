package model;

public class WaterCounter extends Counter {

	private String name;
	private double prevData, currentData, tarif;

	public WaterCounter(String n) {
		this.name = n;
	}

	double calculate() {
		return (currentData - prevData) * tarif;
	}

	@Override
	public String toString() {
		return name;
	}

}
