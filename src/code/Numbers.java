package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.Scanner;

/**
 * CET-CS-Level 3 This is my own original work and is free from plagarism.
 * This class contains the dynamically allocated array and it's processing
 * Student Name: Daniel Falcone 
 * Student Number: 040993167
 * Section: 300 
 * Course: CST8130 - Data Structures
 * Professor: James Mwangi PhD
 * 
 * @author Daniel Falcone
 * 
 */
public class Numbers {
	/**
	 * Stores Float values.
	 */
	private Float[] numbers;

	/**
	 * Stores the size of the array.
	 */
	private int size;

	/**
	 * Store the number of items currently in the array.
	 */
	private int numItems;

	public int getNumItems() {
		return numItems;
	}

	/**
	 * Default Constructor.
	 */
	public Numbers() {
		// instantiate numbers to a default size of 3
		size = 3;
		numbers = new Float[size];
	}

	/**
	 * Constructor that initializes the numbers array.
	 * 
	 * @param size - Max size of the numbers array
	 */
	public Numbers(int size) {
		this.size = size;
		// instantiate numbers to the size of the the value of the parameter
		numbers = new Float[size];
	}

	/**
	 * Adds a value in the array.
	 * 
	 * @param keyboard - Scanner object to use for input
	 */
	public void addValue(Scanner keyboard) {
		System.out.print("Enter value: ");
		// validate that the number of elements doesn't exceed the size of the array
		try {
			// add the value passed into the parameter to the array
			numbers[numItems] = keyboard.nextFloat();
			numItems++;
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			System.out.println("Array full");
		}
		System.out.println();
	}

	public void addValues(Scanner keyboard) {
		try {
			// add the value passed into the parameter to the array
			numbers[numItems] = keyboard.nextFloat();
			numItems++;
		} catch (ArrayIndexOutOfBoundsException aiobe) {
			System.out.println("Array full");
		}
	}

	public void fileOut(File file, Formatter writer) {

		file.setWritable(true);

		try {
			writer = new Formatter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
		}

		for (int i = 0; i < numbers.length; i++) {
			writer.format("%f\n", numbers[i]);
		}
		writer.flush();
		writer.close();
	}

	public void fileIn(File file) {

	}

	/**
	 * Calculates the average of all the values in the numbers array.
	 * 
	 * @return - Float value that represents the average of all the array elements
	 */
	public Float calcAverage() {
		// variable to hold the total sum of the elements
		Float total = 0f;
		// add up all elements of the array
		for (int i = 0; i < numItems; i++) {
			total += numbers[i];
		}
		// return the average
		return total / numItems;
	}

	/**
	 * Finds the smallest element, the largest element, and the value of max % min.
	 */
	public void findMinMax() {
		// set max and min to the first element
		Float max = numbers[0];
		Float min = numbers[0];
		// declare variable to hold the value of max % min
		Float maxModMin;

		// cycle through the array
		for (int i = 0; i < numItems; i++) {
			// if the current index is bigger than the current max...
			if (numbers[i] > max) {
				// set max to the current index
				max = numbers[i];
			}
		}
		// cycle through the array
		for (int i = 0; i < numItems; i++) {
			// if the current index is smaller than the current min...
			if (numbers[i] < min) {
				// set min to the current index
				min = numbers[i];
			}
		}
		maxModMin = max % min;
		// print out average
		System.out.print("Average is: " + calcAverage());
		// print out values from this method
		System.out
				.print(", Minimum value is: " + min + ", Maximum value is: " + max + ", max mod min is: " + maxModMin);
		// print out factorial of the integer value of the max if the max is positive
		if (max >= 0) {
			System.out.println(", factorial of max is: " + getFactorialMax(Math.round(max)) + "\n");
		} else {
			System.out.println(", factorial of a negative is undefined.\n");
		}
	}

	/**
	 * Gets the factorial of the biggest number in the array as an integer.
	 * 
	 * @param max - Largest number in the array represented as an integer
	 * @return - Factorial of the largest number in the array
	 */
	public double getFactorialMax(int max) {
		// variable to store the factorial
		double factorial = 1.0;
		// calculate the factorial
		for (int i = 1; i <= max; i++) {
			factorial *= i;
		}
		return factorial;
	}

	/**
	 * Returns all elements of the array as one long string.
	 */
	@Override
	public String toString() {
		// variable to hold the array elements
		String elements = "";
		System.out.println("Numbers are: ");
		// cycle through the array
		for (int i = 0; i < numbers.length; i++) {
			// if the current element isn't null add it to the number string
			if (numbers[i] != null) {
				elements += numbers[i] + "\n";
			}
		}
		return elements;
	}
}