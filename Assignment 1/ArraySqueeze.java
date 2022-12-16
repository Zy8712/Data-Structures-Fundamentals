/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 1, Problem 1: ArraySqueeze.java
 * Student Name: Bryan Li
 * Student EECS account: [removed for privacy reasons]
 * Student ID number: [removed for privacy reasons]
 **********************************************************/
package A1;

/**
 * The purpose of this class is to squeeze an array of ints.
 * 
 * The main method runs some tests.
 * 
 * @author andy
 * 
 */

public class ArraySqueeze {

	/**
	 * squeeze() takes an array of ints. On completion the array contains the same
	 * numbers, but wherever the array had two or more consecutive duplicate
	 * numbers, they are replaced by one copy of the number. Hence, after squeeze()
	 * is done, no two consecutive numbers in the array are the same.
	 * 
	 * Any unused elements at the end of the array are set to -1.
	 * 
	 * For example, if the input array is [ 4 , 1 , 1 , 3 , 3 , 3 , 1 , 1 ], it
	 * reads [ 4 , 1 , 3 , 1 , -1 , -1 , -1 , -1 ] after squeeze() completes.
	 * 
	 * @param ints the input array.
	 */
	public static void squeeze(int[] ints) {

		// TODO: Fill in your solution here. Ours takes linear time and is less than 10
		// lines long,
		// not counting blank/comment lines or lines already present in this file.

		int curVal = ints[0], current = 1; // Declare variables. curVal represents the value you are looking at, and
											// using to compare for consecutive duplicates.
											// current represents the index you may be manipulating
		for (int i = 1; i < ints.length; i++) { // For loops. Loops through for the length the array.
			if (ints[i] != curVal) { // If statement. If ints[i] is a different value from the value in curVal. Means
										// that the value in this index is different from the previous index. curVal is
										// the current value that you are using to look for consecutive duplicates
				curVal = ints[i]; // Reassigns curVal to the value of the current array index, ints[i] you are
							 // looking at, which will be used for comparison and determining consecutive duplicates
				ints[current++] = ints[i]; // Changes value stored in ints[current] to the value of the current index.
											// Increment counter by one.
			}
		}
		while (current < ints.length) { // While loop. Loops through the remaining indexes. Continues looping till the
										// end of the array
			ints[current++] = -1; // Sets ints[current] to negative one. Increments counter.
		}
	}

	/**
	 * main() runs test cases on your squeeze() method. Prints summary information
	 * on basic operations and halts with an error (and a stack trace) if any of the
	 * tests fail.
	 */

	public static void main(String[] args) {
		String result;

		System.out.println("Let's squeeze arrays!\n");

		int[] test1 = { 3, 7, 7, 7, 4, 5, 5, 2, 0, 8, 8, 8, 8, 5 };
		System.out.println("squeezing " + TestHelper.stringInts(test1) + ":");
		squeeze(test1);
		result = TestHelper.stringInts(test1);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 3 , 7 , 4 , 5 , 2 , 0 , 8 , 5 , -1 , -1 , -1 , -1 , -1 , -1 ]"),
				"BAD SQEEZE!!!  No cookie.");

		int[] test2 = { 6, 6, 6, 6, 6, 3, 6, 3, 6, 3, 3, 3, 3, 3, 3 };
		System.out.println("squeezing " + TestHelper.stringInts(test2) + ":");
		squeeze(test2);
		result = TestHelper.stringInts(test2);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 6 , 3 , 6 , 3 , 6 , 3 , -1 , -1 , -1 , -1 , -1 , -1 , -1 , -1 , -1 ]"),
				"BAD SQEEZE!!!  No cookie.");

		int[] test3 = { 4, 4, 4, 4, 4 };
		System.out.println("squeezing " + TestHelper.stringInts(test3) + ":");
		squeeze(test3);
		result = TestHelper.stringInts(test3);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 4 , -1 , -1 , -1 , -1 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test4 = { 0, 1, 2, 3, 4, 5, 6 };
		System.out.println("squeezing " + TestHelper.stringInts(test4) + ":");
		squeeze(test4);
		result = TestHelper.stringInts(test4);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 0 , 1 , 2 , 3 , 4 , 5 , 6 ]"), "BAD SQEEZE!!!  No cookie.");

		System.out.println("\nAdditional tests done by the student or TA:\n");

		// Insert your additional test cases here.

		int[] test5 = { 1, 1, 2, 2, 4, 5, 6 };
		System.out.println("squeezing " + TestHelper.stringInts(test5) + ":");
		squeeze(test5);
		result = TestHelper.stringInts(test5);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 1 , 2 , 4 , 5 , 6 , -1 , -1 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test6 = { 0, 0, 9, 9, 8, 7, 8, 8, 7, 7 };
		System.out.println("squeezing " + TestHelper.stringInts(test6) + ":");
		squeeze(test6);
		result = TestHelper.stringInts(test6);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 0 , 9 , 8 , 7 , 8 , 7 , -1 , -1 , -1 , -1 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test7 = { 1, 1, 1, 6, 7, 8, 4, 4, 2, 1 };
		System.out.println("squeezing " + TestHelper.stringInts(test7) + ":");
		squeeze(test7);
		result = TestHelper.stringInts(test7);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 1 , 6 , 7 , 8 , 4 , 2 , 1 , -1 , -1 , -1 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test8 = { 2, 2, 2, 2, 3, 3, 4, 4, 4, 4 };
		System.out.println("squeezing " + TestHelper.stringInts(test8) + ":");
		squeeze(test8);
		result = TestHelper.stringInts(test8);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 2 , 3 , 4 , -1 , -1 , -1 , -1 , -1 , -1 , -1 ]"),
				"BAD SQEEZE!!!  No cookie.");

		int[] test9 = { 9, 6, 3, 3, 4, 4, 4, 9, 7, 7 };
		System.out.println("squeezing " + TestHelper.stringInts(test9) + ":");
		squeeze(test9);
		result = TestHelper.stringInts(test9);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 9 , 6 , 3 , 4 , 9 , 7 , -1 , -1 , -1 , -1 ]"), "BAD SQEEZE!!!  No cookie.");

		int[] test10 = { 9, 0, 9, 0, 8 };
		System.out.println("squeezing " + TestHelper.stringInts(test10) + ":");
		squeeze(test10);
		result = TestHelper.stringInts(test10);
		System.out.println(result + "\n");
		TestHelper.verify(result.equals("[ 9 , 0 , 9 , 0 , 8 ]"), "BAD SQEEZE!!!  No cookie.");

	}
}