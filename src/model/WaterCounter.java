package model;

import java.io.Serializable;

public class WaterCounter extends Counter {

	private String name;
	private double prevData, currentData, tarif;

	public WaterCounter(String n) {
		super(n);
	}

	double calculate() {
		return (currentData - prevData) * tarif;
	}

}
