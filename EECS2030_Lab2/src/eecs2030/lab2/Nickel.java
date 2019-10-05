package eecs2030.lab2;

import java.util.Objects;

public class Nickel implements Comparable<Nickel> {

	private int year;

	/**
	 * The monetary value of a nickel in cents.
	 */
	public final int CENTS = 5;

	
	public Nickel (int year) {
		
		if (year < 1858) {
			throw new IllegalArgumentException("Not valid");
		}
		
		this.year = year;
	}
	public int compareTo(Nickel other) {
		return this.issueYear() - other.issueYear();
	}
	
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		} 
		
		if (this == obj) {
			return true;
		}
		
		if (obj.getClass() == this.getClass()) {
			return true;
		}
		
		return false;
	}
	
	public int issueYear() {
		return this.year;
	}
	
	public int hashCode() {
		return this.year; 
	}

}
