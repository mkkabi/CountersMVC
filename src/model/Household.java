package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Household implements Serializable, Comparable<Household>{

	public static final String SAVE_FILE = "houses.ser";
	private final String name;
	public List<WaterCounter> householdCounters;
	private final int ID;

	public Household(String n) {
		ID = (int) (Math.random() * 10000);
		name = n;
		System.out.println("created Household with name " + n);
		householdCounters = new ArrayList();
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name + " serial ID = " + ID;
	}

	@Override
	public int compareTo(Household o) {
		return this.name.compareTo(o.getName());
	}

	public  void addCounter(WaterCounter t) {
		householdCounters.add(t);
	}

	public List<WaterCounter> getCounters() {
		return this.householdCounters;
	}

}
