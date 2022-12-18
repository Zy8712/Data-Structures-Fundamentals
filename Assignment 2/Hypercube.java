/**********************************************************
 * EECS2011N: Fundamentals of Data Structures,  Winter 2020
 * Assignment 2, Problem 2: Hypercube.java
 * Student Name: Bryan Li
 * Student EECS account: [removed for privacy reasons]
 * Student ID number: [removed for privacy reasons]
 **********************************************************/
package A2;

//////////////////////////////////////////////////////////////////////
// Import Statements
import java.util.Scanner; // Import Scanner class for getting input from user in testing in the main method
import java.util.Queue; // Import Queue interface for Iterative method. Follows FIFO (First-In, First-Out) principles.
import java.util.concurrent.LinkedBlockingQueue; // Imports LinkedBlockingQueue class from java.util.concurrent package, 
													// containing a common useful utility classes. LinkedBlockingQueue is used as
													// the runtime type to the declared type of Queue when creating  a new Queue object.

public class Hypercube {

//////////////////////////////////////////////////////////////////////
// Nest Class.
	/**
	 * Nested class. An instance of this class is used to represent a corner of the
	 * Hypercube.
	 * 
	 */
	static class Corner { // Class declaration. Nested class.

/////////////////////////////////////////////////////////////////////////
// Fields of the Corner Object/Instance. Defines the state of the object.
		private int dimension; // Used to store the dimensional space declaration. n-dimensional space.
		private boolean[] coordSet; // Used to store the set of coordinates for the Corner/Instance object.
		// Each coordinate of a corner is either 0 or 1. In this case we use false as 0,
		// and true as 1.

//////////////////////////////////////////////////////////////////////////////////////////
// Constructors of the Corner Object/Instance. Used to initialize the state of the object.	
		/**
		 * Constructor. Initializes the state of the corner instance/object. Sets the
		 * coordinates to all false, where false is represents 0.
		 * 
		 * Operates with a runtime of O(n).
		 * 
		 * @param n the dimension value entered by the user
		 */
		public Corner(int n) {
			this.dimension = n; // Set field dimension equal to the value n
			this.coordSet = new boolean[n]; // Create new boolean array of length n
			for (int i = 0; i < n; i++) { // For Loop. Iterates through the indexes of the array.
				this.coordSet[i] = false; // Sets the current index of the array equal to false which represents 0 in
											// this instance.
			}
		}

		/**
		 * Constructor. Initializes the state of the corner object given dimension n and
		 * position of the corner object relative to one another. Used by the
		 * interativeWalk method in its use of Single Queue to hold Corner objects.
		 * 
		 * Operates with a runtime of O(n), n being the dimension of the Hypercube.
		 * 
		 * @param n   defines the dimension of the object.
		 * @param pos the position of the corner object relative to the others (in
		 *            decimal form) which will be converted to binary for use
		 */
		public Corner(int n, int pos) {
			this(n); // Constructor chaining. Calls the previous constructor.
			for (int i = 0; i < n; i++) { // For Loop. Allows us to iterate through the array.
				int bits = pos & 1; // Bitwise operation. And operation which produces a 1 only if both operands
									// have a 1.
				pos >>= 1; // Bitwise operation. Shifts the bits to the right by 1 bit. Dividing by 2.
				if (bits == 1) { // Conditional if statement. Checks to see if it is equal to 1.
					this.coordSet[n - i - 1] = true; // Sets the index equal to true, which represents 1.
				} else {
					this.coordSet[n - i - 1] = false; // Sets the index equal to false, which represents 0.
				}
			}
		}

//////////////////////////////////////////////////////////////////////
// Utility Methods. Assists the walk methods by carrying out operations.	
		/**
		 * Static factory method. Used by the iterativeWalk() method to create corner
		 * objects one dimension greater than the corner object sent as a parameter.
		 * 
		 * Operates with runtime of O(n), where n is equal to the dimension of the
		 * Hypercube.
		 *
		 * @param c   the original corner object that we are copying the coordinates of
		 *            and adding onto due to a change in dimension
		 * @param bit is the last bit that will be added onto the bit pattern /
		 *            coordinates of the corner
		 */
		public static Corner addDimension(Corner c, boolean bit) {
			Corner c2 = new Corner(c.dimension + 1); // Create a new corner object/instance which is one dimension
														// greater than the previous.
			for (int i = 0; i < c.dimension; i++) { // For Loop. Allows us to iterate through the array.
				c2.coordSet[i] = c.coordSet[i]; // Copy the coordinates of the original corner object.
			}
			c2.coordSet[c.dimension] = bit; // Sets the coordinate of index c.dimesion equal to pos which is adds on the
											// last bit
			return c2; // Return the new Corner object.
		}

		/**
		 * walk(), is used by recursiveWalk to move from one corner of the Hypercube to
		 * another corner of the Hypercube whose coordinates only has a difference of a
		 * single bit being flipped.
		 * 
		 * In other words this method is used to flip the bit coordinates of the corner
		 * objects. Every time this method is called one of the bits out of n bits is
		 * flipped indicating that we are walking to another corner of the Hypercube.
		 * 
		 * @param direction contains a number indicating which bit is to be flipped.
		 * 
		 */
		public void walk(int direction) {
			this.coordSet[direction] = !this.coordSet[direction]; // Flips a bit in the coordinate for a Corner object.
																	// Turns true to false (1 to 0), and false to true
																	// (0 to 1).
		}

		/**
		 * toString(), is used to print out the coordinates of the corner that we are
		 * currently at (i.e. the one we just moved to).
		 * 
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(); // Create a new StringBuilder object.
			for (int i = 0; i < this.dimension; i++) { // For Loop. Allows use to iterate through the array.
				if (this.coordSet[i]) { // Condition if statement. If the value in this index is true, we add 1 to the
										// string. We do that because we use true to represent a bit 1, and false to
										// represent a bit 0.
					sb.append(1); // Adds 1 onto the string.
				} else { // If value is false. Then add 0 onto the string, as we use false to represent a
							// bit 0.
					sb.append(0); // Adds 0 onto the string.
				}
			}
			return sb.toString(); // Return the string, which is the bit pattern or coordinates of the corner.
		}
	}

//////////////////////////////////////////////////////////////////////
// Nested Class.
	/**
	 * Nested class. ModQueue<E> is a queue class in which its only function is to
	 * act as a regular queue.
	 * 
	 * Only allows for enqueue and dequeue. So only first-in-first-out operations.
	 * 
	 * Used by the interativeWalk() method.
	 * 
	 */
	static class ModQueue<E> { // Class Declaration. Nested Class.
		private Queue<E> q = new LinkedBlockingQueue<E>(); // Create a new Queue object.

		/**
		 * enqueue(), is used to add to the queue.
		 * 
		 * @param obj an object to be added to the queue
		 */
		public void enqueue(E obj) {
			q.add(obj); // Adds item obj to the queue. Uses the add() method from the Queue interface.
		}

		/**
		 * dequeue(), is used to remove from the queue. Queue runs on FIFO principles so
		 * the thing at the front of the queue is what's removed.
		 */
		public E dequeue() {
			return q.poll(); // Removes item from the front of the Queue. If Queue is empty it returns null,
								// otherwise it returns the value removed.
								// Uses the poll() method from the Queue interface.
		}
	}

//////////////////////////////////////////////////////////////////////
// Recursive Method to Solving the Problem.
	/**
	 * recursiveWalk(), is a method that initializes the starting conditions for
	 * forming/walking along the Hypercube. Then it calls its recursive helper
	 * method to carry out the recursive operations.
	 * 
	 * The runtime of the method, or at least its recursive helper method is O((2^n)
	 * * n), where n equals dimension.
	 * 
	 * @param n the dimension of the Hypercube
	 */
	public void recursiveWalk(int n) {
		Corner firstCorner = new Corner(n); // Create a new corner object that will act as our starting point.
		System.out.println(firstCorner.toString()); // Call toString() method in order to create a string holding the
													// bit pattern/coordinates. Print that out.
		this.recursiveWalkHelper(n, firstCorner); // Call recursive helper method with n and firstCorner as arguments.
	}

	/**
	 * recursiveWalkHelper(), is a helper method that carries out the recursive
	 * operations. How it works will explained in the PDF, as it'll take up too much
	 * space to explain it here.
	 * 
	 * @param n         the dimension of the Hypercube
	 * @param curCorner the current corner of the Hypercube that we are at
	 */
	private void recursiveWalkHelper(int n, Corner curCorner) {
		n--; // Decrement the value of the value stored in n
		if (n > 0) { // Conditional if statement. If the value of n is greater than 0 then run the
						// operations inside.
			this.recursiveWalkHelper(n, curCorner); // Recursive call. With a decreased n value and curCorner as
													// arguments.
		}
		curCorner.walk(n); // Calls the walk() method, with n as its parameter. Moves from one corner to
							// another corner whose bit pattern only differs in one bit.
		System.out.println(curCorner.toString()); // Call toString() method in order to create a string holding the bit
													// pattern/coordinates. Print that out.
		if (n > 0) { // Conditional if statement. If the value of n is greater than 0 then run the
						// operations inside.
			this.recursiveWalkHelper(n, curCorner); // Recursive call. With a decreased n value and curCorner as
													// arguments.
		}
	}

//////////////////////////////////////////////////////////////////////
// Iterative Method to Solving the Problem.
	/**
	 * iterativeWalk() uses iteration to tackle the problem. We start off with a
	 * dimension of 1 and work our way up to the dimension entered, n. Then we print
	 * out the results into the console.
	 * 
	 * iterativeWalk() operates with a runtime of O(2^(n + 1) * (n + 0.5)) time.
	 * 
	 * @param n the dimension of the Hypercube.
	 */
	public void iterativeWalk(int n) {
		ModQueue<Corner> cornerQueue = new ModQueue<Corner>(); // Declare new Queue, called cornerQueue of type ModQueue
																// which calls the custom Queue class we created and it
																// creates an instance of it
		boolean seq = true; // Declare boolean variable and initialize to true
		cornerQueue.enqueue(new Corner(1, 0)); // Add corner object with dimension of n = 1 to Queue, first point/corner
		cornerQueue.enqueue(new Corner(1, 1)); // Add corner object with dimension of n = 1 to Queue, second point/corner
		Corner cur = cornerQueue.dequeue(); // Pop/dequeue item off Queue and set cur equal to the item popped off
		while (cur != null) { // While Loop. Loops for as long as there is an item in the Queue
			if (cur.dimension == n) { // Conditional if statement. If the dimension of point cur is equal to the
										// dimension entered by the user.
				System.out.println(cur.toString()); // Call method toString(), and print out the coordinates
													// of the point
			} else { // Else statement. If previous condition was not satisfied.
				if (seq) { // If seq is equal to true.
					cornerQueue.enqueue(Corner.addDimension(cur, false)); // Add a new Corner to the Queue, that is one
																			// dimension greater than what is currently
																			// in the Queue, partially copies the
																			// coordinates of one currently
																			// in the queue
					cornerQueue.enqueue(Corner.addDimension(cur, true)); // Add a new Corner to the Queue, that is one
																			// dimension greater than what is currently
																			// in the Queue, partially copies the
																			// coordinates of one currently
																			// in the queue
				} else { // If seq is equal to false.
					cornerQueue.enqueue(Corner.addDimension(cur, true));// Add a new Corner to the Queue, that is one
																		// dimension greater than what is currently
																		// in the Queue, partially copies the
																		// coordinates of one currently
																		// in the queue
					cornerQueue.enqueue(Corner.addDimension(cur, false)); // Add a new Corner to the Queue, that is one
																			// dimension greater than what is currently
																			// in the Queue, partially copies the
																			// coordinates of one currently
																			// in the queue
				}
				seq = !seq; // Reverse the boolean value.
			}
			cur = cornerQueue.dequeue(); // Pop/dequeue item off Queue and set cur equal to item popped off
		}
	}

//////////////////////////////////////////////////////////////////////
// Main Method. Used to run test cases.
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in); // Declare scanner object. Used to store user's input.
		Hypercube hype = new Hypercube(); // Declare/create a new Hypercube object.
		System.out.println("Enter dimension of Hypercube:"); // Output prompt message to user
		int n = in.nextInt(); // Store user's input
		
		if(n <= 0) { // Conditional statement. If dimension entered by user is less than or equal to 0. Invalid.
			System.out.println("Please enter a dimension that is greater than 0"); // Print error message.
			System.exit(0); // Stop program.
		}

// Output the results from recursiveWalk		
		System.out.println("==============\nrecursiveWalk:\n=============="); // Title
		hype.recursiveWalk(n); // Send user's input to recursiveWalk() method
		System.out.println("==============\n");

// Output the results from iterativeWalk		
		System.out.println("==============\niterativeWalk:\n=============="); // Title
		hype.iterativeWalk(n); // Send user's input to iterativeWalk() method
		System.out.println("==============");

		in.close(); // Close scanner
	}
}
