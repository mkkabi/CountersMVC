
package model;

abstract class Counter {
	private String name;
	private int prevData, currentData, tarif;
	
	public Counter(String n){
		this.name = n;
	}
	
	abstract int calculate();
}
