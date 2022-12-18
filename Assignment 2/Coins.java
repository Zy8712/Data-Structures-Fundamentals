/**********************************************************
 * EECS2011N: Fundamentals of Data Structures,  Winter 2020
 * Assignment 2, Problem 1: Coins.java
 * Student Name: Bryan Li
 * Student EECS account: [removed for privacy reasons]
 * Student ID number: [removed for privacy reasons]
 **********************************************************/
package A2;

import java.util.Scanner; // Import Scanner class for getting input from user in testing in the main method

public class Coins {

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Variables/fields. All of which are used in I/O operations, and all hold constant number of elements/values.
	private int[] denom = { 1, 5, 10, 25 }; // Declare an array holding a constant number of elements. This array
											// contains the denominations of the coins used in this program.
	private String[] singularName = { "penny", "nickel", "dime", "quarter" }; // Declare array holding a constant number
																				// of elements. This array contains the
																				// singular names for coins used in this
																				// program.

	private String[] pluralName = { "pennies", "nickels", "dimes", "quarters" }; // Declare array holding a constant
																					// number of elements. This array
																					// contains the plural name for
																					// coins used in this program.

	private int wayChange; // Declare variable which is used to count the number of ways to make change.
							// This is used as the way number when printing out the results.

	private int[] coinCount = new int[denom.length]; // Declare an array that holds a constant number of elements. This
														// array is used as a counter to count the number of each coin
														// when making change. This is used in the output when showing
														// the all the different ways.

///////////////////////////////////////////////////////////////////////////////////////////////
// Constructor. Initializes the state of the Coin object.
	/**
	 * Coins class no-argument constructor. Initializes the state of the Coin
	 * object. Sets the indexes in the counter array to a value of 0.
	 * 
	 */
	public Coins() { // Constructor. Used to initialize the state of the object.
		for (int i = 0; i < denom.length; i++) { // For loop. Used to iterate through each index of the array.
			this.coinCount[i] = 0; // Sets the value of the current index of array to 0.
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////
// Print Methods. Used to print out the results from the enumeration of of ways to make change.
	/**
	 * printFirst(), used to print out the first line of output if amount entered is
	 * valid.
	 * 
	 */
	public void printFirst() {
		System.out.println("This amount can be changed in the following ways:"); // First line to be printed in console
																					// if amount entered is valid.
	}

	/**
	 * printError(), used to print an error message when the value entered is
	 * invalid. The value must be a positive number or else its invalid.
	 * 
	 */
	public void printError() {
		System.out.println("The entered amount should be a positive integer."); // Error message to be printed in
																				// console if amount entered is invalid.
	}

	/**
	 * printResult(), called each time a new way of making change is found. Prints
	 * out the combinations coins that amounts to the value entered. Takes constant time 
	 * since size of array is fixed.
	 */
	public void printResult() {
		StringBuilder sb = new StringBuilder(); // Declare new StringBuilder object.
		sb.append(String.format("\t%d)", ++this.wayChange)); // Creates beginning of string output
		for (int i = this.denom.length - 1; i >= 0; i--) { // For Loop. Used to iterate through array.
			if (this.coinCount[i] != 0) { // Conditional if statement. Checks if the value inside the current index of
											// the counter array is not equal to zero.
				sb.append(String.format(" %d %s,", this.coinCount[i],
						(this.coinCount[i] == 1 ? this.singularName[i] : this.pluralName[i]))); // Adds on the
																								// denomination and
																								// number of coins of
																								// that denomination on
																								// to the string output.
			}
		}
		sb.deleteCharAt(sb.length() - 1); // Removes the last char at the end
		System.out.println(sb.toString()); // Prints out the output string containing the combination of denominations
											// and number of each denomination that adds up to the given value.
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Recursion Methods. Methods involved in the recursive enumeration of the number of distinct ways change can be made.	
	/**
	 * ways(), method that checks conditions to see if value entered is valid. Then
	 * sends it to its helper method to do the recursive operations. This method takes
	 * O(n^4) where n is the amount entered and the 4 is # of types of coins.
	 * 
	 */
	public void ways(int amount) {
		if (amount <= 0) { // Conditional if statement. Checks to see if amount entered by user is valid.
							// Has to be greater than 0 to be valid.
			this.printError(); // Call method to output error message.
			return; // Return statement. Return statement in Void Method, causes program to exit
					// method.
		}
		this.printFirst(); // Print opening line for displaying ways to make change with the value entered.
		this.wayChange = 0; // Counts the number of ways to make change with value entered. Used in output.
		this.waysHelper(amount, this.denom.length - 1); // Call recursive helper method.
	}

	/**
	 * waysHelper(), uses recursion to enumerate the distinct ways in which a given
	 * amount can be made into change.
	 * 
	 */
	private void waysHelper(int curBal, int currentDenom) {
		if (currentDenom >= 0) { // Conditional if statement. Checks to see if value of currentDenom is equal to
									// or less than 0. currentDenom is used to indicate which index of the denom
									// array we are on. We start from the last index of currentDenom which contains
									// the value of 25 cents, and go down from there by going to the earlier indexes
									// of the array.
			if (curBal == 0) { // Conditional if statement. Checks to see if balance is equal to 0.
				this.printResult(); // Call method to print out one of the combination of coins that adds up to the
									// value entered.
			} else {
				if (curBal >= this.denom[currentDenom]) { // Conditional if statement. Checks to see if current balance
															// is greater than or equal to the current denomination we
															// are looking at.
					this.coinCount[currentDenom]++; // Increments counter for the denomination stored in the index of
													// currentDenom
					this.waysHelper(curBal - this.denom[currentDenom], currentDenom); // Recursive call, sends reduced
																						// balance and index of current
																						// denomination we are looking
																						// at.
					this.coinCount[currentDenom]--; // Decrements counter for the denomination stored in the index of
													// currentDenom
				}
				this.waysHelper(curBal, currentDenom - 1); // Recursive call, sends the balance and reduced index number
															// (i.e. goes to smaller denomination)
			}
		}
	}

//////////////////////////////////////////////////////////////////////
// Main Method. Used to run test cases.
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // Declare scanner object, used to take in scanner object.
		System.out.println("Enter an amount in cents:"); // Output prompt to use in console.
		int amount = scan.nextInt(); // Take user's input and stores it into integer variable amount.

		Coins c = new Coins(); // Create new  Coins object.
		c.ways(amount); // Call recursive method to enumerate the number of distinct ways we can make
						// change
		scan.close(); // Close scanner object, prevents resource leak.
	}
}
