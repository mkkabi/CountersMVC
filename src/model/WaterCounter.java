package model;

public class WaterCounter extends Counter {

	private String name;
	private int prevData, currentData, tarif;

	public WaterCounter(String n) {
		super(n);
	}

	@Override
	int calculate() {
		return (currentData - prevData) * tarif;
	}

}
