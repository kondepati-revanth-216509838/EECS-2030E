package eecs2030.lab1;

import java.lang.Object;


/**
 * A class that represents a windowed view of Hounsfield units. A Hounsfield
 * window is defined by two values: (1) the window level, and (2) the window
 * width. The window level is the Hounsfield unit value that the window is
 * centered on. The window width is the range of Hounsfield unit values that the
 * window is focused on.
 * 
 * <p>
 * A window has a lower and upper bound. The lower bound is defined as the
 * window level minus half the window width:
 * 
 * <p>
 * lo = level - (width / 2)
 * 
 * <p>
 * The upper bound is defined as the window level plus half the window width:
 * 
 * <p>
 * hi = level + (width / 2)
 * 
 * <p>
 * Hounsfield units are mapped by the window to a real number in the range of
 * {@code 0} to {@code 1}. A Hounsfield unit with a value less than lo is mapped
 * to the value {@code 0}. A Hounsfield unit with a value greater than hi is
 * mapped to the value {@code 1}. A Hounsfield unit with a value v between lo
 * and hi is mapped to the value:
 * 
 * <p>
 * (v - lo) / width
 * 
 *
 */

/**
 *  Width = Hounsfield unit value range to be focused on
 *  Level = Hounsfield unit focused on
 * 
 * */


public class HounsfieldWindow extends Object {
	private static int level, width;
	
	private static double halfWidth = (double)width / 2.0;

	
	private static double hi, lo;
	
	public void updateInfo(int level, int width) {
		this.halfWidth = (double) width / 2.0;
		this.hi = this.level + this.halfWidth;
		this.lo = this.level - this.halfWidth;
	}
	
	public int getLevel() {
		return this.level;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	
	public static void checkWidth(int width) {
		if (width < 1) {
			throw new IllegalArgumentException();
		}
	}
	
	public static void checkLevel(int level) {
		if (level < Hounsfield.MIN_VALUE || level > Hounsfield.MAX_VALUE) {
			throw new IllegalArgumentException();
		}
	}
	
	public HounsfieldWindow (int level, int width)  {
			checkWidth(width);
			checkLevel(level);
			this.level = level;
			this.width = width;
			updateInfo(level, width);
	}
	
	public HounsfieldWindow() {
		this.level = 0;
		this.width = 400;
		updateInfo(this.level, this.width);
	}
	
	public int setLevel(int level) {
		checkLevel(level);
		
		int initialLevel = this.level;
		
		this.level = level;
		
		updateInfo(this.level, this.width);	
		
		return initialLevel;
	}
	
	public int setWidth(int width) {
		checkWidth(width);
		
		int initialWidth = this.width;
		
		this.width = width;
		
		updateInfo(this.level, this.width);
		
		return initialWidth;
	}
	
	public double map (Hounsfield h) {
		int hVal = h.get();
		
		if (hVal < lo) {
			return 0.0;
		} else if (hVal > hi) {
			return 1.0;
		} else {
			return (hVal - lo) / width;
		}
	}
}
