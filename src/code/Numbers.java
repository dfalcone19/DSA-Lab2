package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.InputMismatchException;
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

	/**
	 * Default constructor initializes array to a size of 3.
	 */
	public Numbers() {
		// instantiate numbers to a default size of 3
		size = 3;
		numbers = new Float[size];
	}

	/**
	 * Constructor that initializes the numbers array to a size entered by the user.
	 * 
	 * @param size - Max size of the numbers array
	 */
	public Numbers(int size) {
		this.size = size;
		// instantiate numbers to the size of the the value of the parameter
		numbers = new Float[size];
	}

	/**
	 * Adds a value in the array using user input.
	 * 
	 * @param keyboard - Scanner object to use for input
	 * @param isFile   - Determines if reading from a file or from user input
	 */
	public void addValue(Scanner keyboard, boolean isFile) {
		if (!isFile) {
			System.out.print("Enter value: ");
		}
		// validate that the number of elements doesn't exceed the size of the array
		try {
			// add the value passed into the parameter to the array
			numbers[numItems] = keyboard.nextFloat();
			numItems++;
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Array full");
		}
		if (!isFile) {
			System.out.print("\n");
		}
	}

	/**
	 * Adds a value to the array by reading from a file.
	 * 
	 * @param keyboard - Scanner object to use for input
	 */
	public void addValues(Scanner keyboard) {
		// to be initiated using the file name
		Scanner in = null;
		// to track which line is currently being read
		int line = 0;
		System.out.print("Name of the file to read from: ");
		String fileName = keyboard.next();
		// create new file object to be read from
		File inFile = new File(fileName);

		try {
			// if the file exists read from it line by line
			if (inFile.exists()) {
				in = new Scanner(inFile);
				while (in.hasNextFloat()) {
					// if we have passed the first line then read
					if (line > 0) {
						addValue(in, true);
					} else {
						// if we are on the first line skip it and increment counter
						in.nextLine();
						line++;
					}
				}
			} else {
				// if the file does not exist tell the user
				System.out.println("File not found");
			}
		} catch (IOException e) {
			System.out.println("Could not open file");
		}
		System.out.println();
	}

	/**
	 * Writes the values of the array to a file.
	 * 
	 */
	public void saveToFile() {
		Scanner input = new Scanner(System.in);
		Formatter writer = null;
		String outFileName = "";
		System.out.print("Name of the file to save to: ");

		// verify the name entered is a valid string
		try {
			outFileName = input.next();
		} catch (InputMismatchException e) {
			System.out.println("Please enter a valid file name");
		}

		// instantiate new file object to be used to write out to
		File file = new File(outFileName);

		// create a new file
		try {
			file.createNewFile();
		} catch (IOException e) {
			System.out.println("File could not be created");
		}
		// set the file to writable
		file.setWritable(true);
		// call method in numbers
		file.setWritable(true);

		// create new formatter object if the file exists
		try {
			writer = new Formatter(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File not found!");
		}

		// write the number of elements in the array to the first line
		writer.format("%d\n", numItems);

		// cycle through the numbers array and write each element to the file
		for (int i = 0; i < numbers.length; i++) {
			writer.format("%f\n", numbers[i]);
		}

		writer.flush();
		writer.close();
		System.out.println();
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

	/**
	 * Accessor for numItems.
	 * 
	 * @return value of numItems
	 */
	public int getNumItems() {
		return numItems;
	}
}