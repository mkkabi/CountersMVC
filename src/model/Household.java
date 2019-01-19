package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableArrayList;
import javafx.collections.ObservableList;

<<<<<<< HEAD
public class Household <T extends Counter> implements Serializable, Comparable<Household> {
=======
public class Household implements Serializable, Comparable<Household>{
>>>>>>> origin/master

	public static final String SAVE_FILE = "houses.ser";
	private final String name;
<<<<<<< HEAD
//	public List<WaterCounter> householdCounters;
	private ArrayList<T> counters;
=======
	public List<WaterCounter> householdCounters;
>>>>>>> origin/master
	private final int ID;

	public Household(String n) {
		ID = (int) (Math.random() * 10000);
		name = n;
		System.out.println("created Household with name " + n);
<<<<<<< HEAD
		counters = new ArrayList();
=======
		householdCounters = new ArrayList();
>>>>>>> origin/master
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

<<<<<<< HEAD
	public void addCounter(T t) {
//		householdCounters.add(t);
		counters.add(t);
	}

	public ArrayList<T> getCounters() {
//		return this.householdCounters;
		return this.counters;
=======
	public  void addCounter(WaterCounter t) {
		householdCounters.add(t);
	}

	public List<WaterCounter> getCounters() {
		return this.householdCounters;
>>>>>>> origin/master
	}

}
