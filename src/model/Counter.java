
package model;

import java.io.Serializable;
import java.util.Objects;

 public class Counter implements Serializable, Countable{
	private String name;
	private double rate, previousData, currentData;
	

	public void setName(String name) {
		this.name = name;
	}

	public void setRate(double rate) {
		this.rate = Objects.requireNonNull(rate);
	}
	
	public double getRate(){
		return this.rate;
	}
	
	public void setCurrentData(double data){
		currentData = Objects.requireNonNull(data);
	}
	
	public double calculate(){
		return (currentData-previousData)*rate;
	}

	@Override
	public String toString() {
		return name;
	}
}
