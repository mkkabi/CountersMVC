package model;

public class ElectricityCounter extends Counter {

	private String name, csvFileName;
	private double rate, previousData, currentData;

	public ElectricityCounter(String n) {
//		super(n);
		this.name = n;
	}

	@Override
	public String getName(){
		return this.name;
	}
	
	public void setFileName(String f){
		csvFileName = f;
	}
	
	public String getFileName(){
		return csvFileName;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

}
