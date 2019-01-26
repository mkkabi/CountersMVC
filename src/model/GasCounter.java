package model;

public class GasCounter extends Counter {

	private String name, csvFileName;
	private double rate, previousData;

	public GasCounter(String n) {
//		super(n);
		this.name = n;
	}
	
	@Override
	public String getName(){
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setFileName(String f){
		csvFileName = f;
	}
	
	public String getFileName(){
		return csvFileName;
	}
	
	public void setPreviousData(double previousData) {
		this.previousData = previousData;
	}

	public double getPreviousData() {
		return previousData;
	}


	@Override
	public String toString() {
		return this.name;
	}

}
