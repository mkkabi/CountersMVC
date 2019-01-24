
package model;

import java.io.Serializable;
import java.util.Objects;

 public class Counter implements Serializable, Countable{
	private String name, csvFileName;
	private double rate, previousData;
	
//	public Counter(String n){
//		this.name = n;
//	}
	

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	public void setPreviousData(double previousData) {
		this.previousData = previousData;
	}

	public double getPreviousData() {
		return previousData;
	}

	

	public void setRate(double rate) {
		this.rate = Objects.requireNonNull(rate);
	}
	
	public double getRate(){
		return this.rate;
	}

	public void setFileName(String f){
		csvFileName = f;
	}
	
	public String getFileName(){
		return csvFileName;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
