package code;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * CET-CS-Level 3 This is my own original work and is free from plagarism. 
 * This is the main class that will cycle through the menu items and call on methods
 * from the Numbers class to manipulate the dynamic array. 
 * Student Name: Daniel Falcone 
 * Student Number: 040993167 
 * Section: 300 
 * Course: CST8130 - Data
 * Structures Professor: James Mwangi PhD
 * 
 * @author Daniel Falcone
 * 
 */
public class Lab2 {

	/**
	 * Main method which contains all the method calls
	 * 
	 * @param args - Command line arguments
	 */
	public static void main(String[] args) {
		// declare a new Numbers object
		Numbers num = new Numbers();
		// boolean to break from loop, not just switch
		boolean exit = false;
		// declare file
		Formatter writer = null;

		while (true) {
			// declare and initialize a new Scanner object
			Scanner input = new Scanner(System.in);
			// integer variable to store the user's choice
			int choice = 0;
			displayMainMenu();

			// verify user input is an integer
			try {
				choice = input.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please only enter an integer\n");
			}

			switch (choice) {
			case 1:
				// initialize num using the default constructor
				num = new Numbers();
				System.out.println();
				break;
			case 2:
				System.out.print("Enter new size of array: ");
				// verify user input is an integer and a valid array size
				try {
					// instantiate num array to be the size of the user input
					num = new Numbers(input.nextInt());
					System.out.println();
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid integer\n");
				} catch (NegativeArraySizeException e) {
					System.out.println("Please enter a positive integer\n");
				}
				break;
			case 3:
				// validate user input is a valid number
				try {
					num.addValue(input);
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid number\n");
				}
				break;
			case 4:
				// output the array elements as a string
				System.out.println(num.toString());
				break;
			case 5:
				// verify that the array is not empty
				try {
					// call method that will display the average, min, max, min mod max, and max!
					num.findMinMax();
				} catch (NullPointerException e) {
					System.out.println("Array is empty!\n");
				}
				break;
			case 6:
				int itemsToAdd = 0;
				System.out.print("How many values do you wish to add? ");

				// check input is integer
				try {
					itemsToAdd = input.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("Please enter a valid integer\n");
				}

				// check input is a valid number
				if (itemsToAdd < 0) {
					System.out.println("Please enter a valid integer");
					break;
				}

				// cycle through the items to add
				for (int i = 0; i < itemsToAdd; i++) {
					try {
						// add items
						num.addValue(input);
					} catch (InputMismatchException ime) {
						System.out.println("Please enter a valid number\n");
					}
				}

				break;
			case 7:
				// to be initiated using the file name
				Scanner in = null;
				// to track which line is currently being read
				int line = 0;
				System.out.print("Name of the file to read from: ");
				String fileName = input.next();
				// create new file object to be read from
				File inFile = new File(fileName);

				try {
					// if the file exists read from it line by line
					if (inFile.exists()) {
						in = new Scanner(inFile);
						while (in.hasNextFloat()) {
							// if we have passed the first line then read
							if (line > 0) {
								num.addValues(in);
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
				break;
			case 8:
				String outFileName = "";
				System.out.print("Name of the file to save to:");

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
				num.fileOut(file, writer);
				break;
			case 9:
				// exit
				System.out.println("Goodbye!");
				exit = true;
				break;
			default:
				// if the user does not enter a valid option
				System.out.println("Please enter a valid menu option (1-9)\n");
			}

			// break from loop
			if (exit) {
				break;
			}

		}

	}

	/**
	 * Helper method that displays the main menu
	 */
	public static void displayMainMenu() {
		System.out.print("Please select one of the following:\n" + "1: Initialize a default array\n"
				+ "2: To specify the max size of the array\n" + "3: Add value to the array\n"
				+ "4: Display values in the array\n"
				+ "5: Display average of the values, minimum value, maximum value, max mod min, and the factorial of the max\n"
				+ "6: Enter multiple values\n" + "7: Read values from file\n" + "8: Save values to file\n" + "9: Exit\n"
				+ "> ");
	}

}
