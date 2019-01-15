
package model;

abstract class Counter {
	private String name;
	
	
	public Counter(String n){
		this.name = n;
	}
	
	abstract int calculate();
}
