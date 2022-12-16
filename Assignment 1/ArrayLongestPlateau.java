/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 1, Problem 2: ArrayLongestPlateau.java
 * Student Name: Bryan Li
 * Student EECS account: [removed for privacy reasons]
 * Student ID number: [removed for privacy reasons]
 **********************************************************/
package A1;

/**
 * The purpose of this class is to find the longest plateau of an array of ints.
 * 
 * The main method runs some tests.
 * 
 * @author andy
 * 
 */

public class ArrayLongestPlateau {

	/**
	 * longestPlateau() returns the longest plateau of an array of ints.
	 * 
	 * @return an array int[3] of the form {value, start, len} representing the
	 *         longest plateau of ints[] as a length len contiguous subarray
	 *         starting at index start with common element values value.
	 * 
	 *         For example, on the input array [2, 3, 3, 3, 3, 6, 6, 1, 1, 1], it
	 *         returns [6, 5, 2], indicating the longest plateau of this array is
	 *         the subarray [6, 6]; it starts at index 5 and has length 2.
	 * 
	 * @param ints the input array.
	 */

	public static int[] longestPlateau(int[] ints) {

		// TODO: Replace the following one line stub with your solution. Ours takes
		// linear time and is
		// 24 lines long, not counting blank/comment lines or lines already present in
		// this file.
//////////////////////////////////////		
///////////////////////IMPORTANT NOTE: Since instructions says we may break ties arbitrarily, when we encounter two plateaus of the same length, I have		
////////////////////////////////////// chosen to take the one encountered first
		
//Declare variables for value of current flat/plateau, starting index of current flat/plateau, length of current flat/plateau. Current != longest.
//Declare variables for value of longest plateau, starting index of longest plateau, length of longest plateau
		int cur_value = ints[0], cur_start = 0, cur_len = 1, value = ints[0], start = 0, len = 0;
		for (int i = 0; i < ints.length; i++) { // For loop. Used to loop through array.
			if (i - 1 >= 0 && ints[i - 1] < ints[i]) { // Conditional statement. If current index is at least the 2nd
														// index, and if value of previous index of array us less than
														// value in current index of array
				cur_value = ints[i]; // Sets value of current flat/plateau to value of current index
				cur_start = i; // Sets start index of current flat/plateau to current index number
			} else if (i - 1 >= 0 && ints[i - 1] > ints[i]) { // Conditional statement. If current index is a least the
																// 2nd index, and if value of previous index of array is
																// greater than the value in the current index of the array
				cur_start = -1; // Set start index of current flat/plateau to negative one
				cur_len = 1; // Set length of current flat/plateau to one
			}
			if (i + 1 < ints.length && ints[i] == ints[i + 1] && cur_start != -1) { //Conditional statement. If this index is no the last index
				                                                                    // and if current index value is equal to next index value
				                                                 // and if cur_start != -1. Checks to see whether this is a flat and possible plateau
				cur_len++; // Increment length of current flat/plateau by one.
			} else if (i + 1 < ints.length && ints[i] < ints[i + 1]) {//Conditional statement. If this index is no the last index
                                       // and if current index value is less than to next index value. If condition checks out, this is not a plateau.
				cur_start = -1; // Set start index of current flat/plateau to negative one, as it is confirmed to not be a plateau
				cur_len = 1; // Set length of current flat/plateau to one, as it is confirmed to no be a plateau
			} else if (i + 1 >= ints.length || ints[i] > ints[i + 1]) { // Conditional statement. If we are on the last
																		// index or if value in current index is greater
																		// than value in next index.
				if (len < cur_len) { // Conditional statement. If length of longest plateau is shorter than length of
										// a current plateau
					value = cur_value; // Set value of longest plateau to value of current plateau
					start = cur_start; // Set starting index of longest plateau to starting index of current plateau
					len = cur_len; // Set length of longest plateau to length of current plateau
				}
				cur_start = -1; // Set starting index of current plateau to negative one
				cur_len = 1; // Set length of current plateau to one
			}
		}
		return new int[] { value, start, len }; // Return new array of length 3, for which the indexes contains the
												// value of the longest plateau, the starting index of the longest plateau, 
		                                         //and the length for the longest plateau
	}

	/**
	 * main() runs test cases on your longestPlateau() method. Prints summary
	 * information on basic operations and halts with an error (and a stack trace)
	 * if any of the tests fail.
	 */

	public static void main(String[] args) {
		String result;

		System.out.println("Let's find longest plateaus of arrays!\n");

		int[] test1 = { 4, 1, 1, 6, 6, 6, 6, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test1) + ":");
		result = TestHelper.stringInts(longestPlateau(test1));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 3 , 4 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test2 = { 3, 3, 1, 2, 4, 2, 1, 1, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test2) + ":");
		result = TestHelper.stringInts(longestPlateau(test2));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 3 , 0 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test3 = { 3, 3, 1, 2, 4, 0, 1, 1, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test3) + ":");
		result = TestHelper.stringInts(longestPlateau(test3));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 1 , 6 , 4 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test4 = { 3, 3, 3, 4, 1, 2, 4, 4, 0, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test4) + ":");
		result = TestHelper.stringInts(longestPlateau(test4));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 6 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test5 = { 7, 7, 7, 7, 9, 8, 2, 5, 5, 5, 0, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test5) + ":");
		result = TestHelper.stringInts(longestPlateau(test5));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 5 , 7 , 3 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test6 = { 4 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test6) + ":");
		result = TestHelper.stringInts(longestPlateau(test6));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 4 , 0 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test7 = { 4, 4, 4, 5, 5, 5, 6, 6 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test7) + ":");
		result = TestHelper.stringInts(longestPlateau(test7));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 6 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		System.out.println("\nAdditional tests done by the student or TA:\n");

		// Insert your additional test cases here.

		int[] test8 = { 4, 4, 4, 4, 4, 4, 6, 6 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test8) + ":");
		result = TestHelper.stringInts(longestPlateau(test8));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 6 , 6 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test9 = { 6, 6, 6, 6, 7, 8, 9, 6, 6, 6, 6 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test9) + ":");
		result = TestHelper.stringInts(longestPlateau(test9));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 9 , 6 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test10 = { 4, 4, 4, 5, 5, 5, 4, 4, 4, 9, 9, 9, 5, 5, 5 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test10) + ":");
		result = TestHelper.stringInts(longestPlateau(test10));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 5 , 3 , 3 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test11 = { 1, 1, 1, 1, 9, 9, 5, 7, 8, 0, 0, 3 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test11) + ":");
		result = TestHelper.stringInts(longestPlateau(test11));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 9 , 4 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test12 = { 3, 3, 3, 0, 1, 5, 5, 5, 2, 9, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test12) + ":");
		result = TestHelper.stringInts(longestPlateau(test12));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 3 , 0 , 3 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test13 = { 3, 3, 3, 3, 3, 3, 3, 3, 1, 1, 1 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test13) + ":");
		result = TestHelper.stringInts(longestPlateau(test13));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 3 , 0 , 8 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test14 = { 3, 3, 3, 5, 5, 5, 5, 5, 5, 9, 9 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test14) + ":");
		result = TestHelper.stringInts(longestPlateau(test14));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 9 , 9 , 2 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test15 = { 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test15) + ":");
		result = TestHelper.stringInts(longestPlateau(test15));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 7 , 10 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

		int[] test16 = { 8, 7, 6, 5, 4, 4, 3, 3, 2, 3, 2 };
		System.out.println("longest plateau of " + TestHelper.stringInts(test16) + ":");
		result = TestHelper.stringInts(longestPlateau(test16));
		System.out.println("[ value , start , len ] = " + result + " \n");
		TestHelper.verify(result.equals("[ 8 , 0 , 1 ]"), "Wrong: that's not the longest plateau!!!  No chocolate.");

	}
}