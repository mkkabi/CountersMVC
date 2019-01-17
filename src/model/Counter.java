
package model;

import java.io.Serializable;

 public class Counter implements Serializable, Countable{
	private String name;
	private double rate;


	public void setName(String name) {
		this.name = name;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
	
	//abstract double calculate();

	@Override
	public String toString() {
		return name;
	}
}
