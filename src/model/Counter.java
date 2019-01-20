
package model;

import java.io.Serializable;
import java.util.Objects;

 public class Counter implements Serializable, Countable{
	private String name, csvFileName;
	private double rate, previousData, currentData;
	
//	public Counter(String n){
//		this.name = n;
//	}
	

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
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
