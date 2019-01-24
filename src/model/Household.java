package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;

public class Household <T extends Counter> implements Serializable, Comparable<Household> {

	public static final String SAVE_FILE = "houses.ser";
	private final String name;
//	public List<WaterCounter> householdCounters;
	private ArrayList<T> counters;
	private final int ID;
	private double previousData;

	public Household(String n) {
		ID = (int) (Math.random() * 10000);
		name = n;
		System.out.println("created Household with name " + n);
		counters = new ArrayList();
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

	@Override
	public String toString() {
		return this.name + " serial ID = " + ID;
	}

	@Override
	public int compareTo(Household o) {
		return this.name.compareTo(o.getName());
	}

	public void addCounter(T t) {
//		householdCounters.add(t);
		counters.add(t);
	}

	public ArrayList<T> getCounters() {
//		return this.householdCounters;
		return this.counters;
	}

}
