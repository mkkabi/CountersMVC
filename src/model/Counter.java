
package model;

import java.io.Serializable;

 public class Counter implements Serializable{
	private String name;
	private double rate;
	
	public Counter(String n){
		this.name = n;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	
	
	
	//abstract double calculate();
}
