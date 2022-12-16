/**********************************************************
 * EECS2011ON: Fundamentals of Data Structures,  Winter 2020
 * Assignment 1, Problem 3: Window.java
 * Student Name: Bryan Li
 * Student EECS account: [removed for privacy reasons]
 * Student ID number: [removed for privacy reasons]
 **********************************************************/
package A1;

/**
 * The purpose of this class is to count how many overlapping and how many
 * enclosing pairs of windows there are (without double counting), given an
 * array of windows.
 * 
 * The main method runs tests.
 * 
 */

public class Window {

	/**
	 * Nested class. Class for custom exception, InvalidWindowException.
	 * InvalidWindowException extends RuntimeException, in other words it is a
	 * subclass to RuntimeException superclass.
	 * 
	 */
	static class InvalidWindowException extends RuntimeException { // Class declaration. It is a subclass to Runtime
																	// Exception superclass.
		public InvalidWindowException(String errorMessage) { // InvalidWindowException class constructor.
																// String parameter for error message.
			super(errorMessage); // Invoke parent class constructor. Sends string error message as a parameter.
		}
	}

//Protected fields defining the state of the Window object. Protected fields are only accessibly by this class and subclasses.
	protected double left; // Declare field for left coordinate of window.
	protected double right; // Declare field for right coordinate of window.
	protected double bottom; // Declare field for bottom coordinate of window.
	protected double top; // Declare field for top coordinate of window.

	public Window() { // No Argument Constructor.
		// May cause issues as no argument constructor will automatically set Window dimensions all to 0, which already breaks 
		// class invariant but instructions were not clear as to what we needed to do in this scenario so it is left as is.
		// Instructions only states a constructor that is given values, implying that the values must be given at time of 
		// new Window object declaration (ex. new Window() will not occur in tests)
		System.out.println("\nWARNING!!! No-Argument Constructor was called, all fields of window set to 0");
		System.out.println("Please be careful with setting the fields of the window. Or else error will occur.");
		System.out.println("Class invariant already technically broken\n");
	}

	public Window(double leftA, double rightA, double bottomA, double topA) { // Argument Constructor
		if (leftA >= rightA || bottomA >= topA) { // If statement. Checks to see whether it breaks class invariant
													// Class invariant is left < right and bottom < top
			throw new InvalidWindowException("Invalid Window!!! Must follow class invariant of left < right and bottom < top"); 
		}																								// Throws InvalidWindowException
		this.left = leftA; // Assigns value to left coordinate of window.
		this.right = rightA; // Assigns value to right coordinate of window.
		this.bottom = bottomA; // Assigns value to bottom coordinate of window.
		this.top = topA; // Assigns value to top coordinate of window.
	}
	
	public Window(Window w) { // Copy Constructor, just in case you want to copy another window
		this.left = w.left; // Assigns value to left coordinate of window.
		this.right = w.right; // Assigns value to right coordinate of window.
		this.bottom = w.bottom; // Assigns value to bottom coordinate of window.
		this.top = w.top; // Assigns value to top coordinate of window.
	}
	

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
//Public Getter Methods

	/**
	 * getLeft() returns the value of the left field of the window
	 * 
	 * @return double the value of left field of the window
	 *
	 */
	public double getLeft() { // Method Header
		return this.left; // Return value of left coordinate of window
	}

	/**
	 * getRight() returns the value of the right field of the window
	 * 
	 * @return double the value of right field of the window
	 *
	 */
	public double getRight() { // Method Header
		return this.right; // Return value of right coordinate of window
	}

	/**
	 * getBottom() returns the value of the bottom field of the window
	 * 
	 * @return double the value of bottom field of the window
	 *
	 */
	public double getBottom() { // Method Header
		return this.bottom; // Return value of bottom coordinate of window
	}

	/**
	 * getTop() returns the value of the top field of the window
	 * 
	 * @return double the value of top field of the window
	 *
	 */
	public double getTop() { // Method Header
		return this.top; // Return value of top coordinate of window
	}

	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
//Public Setter Methods	

	/**
	 * setLeft() changes the value of the field containing the left coordinate of
	 * the window. Given that the new value entered doesn't break the class
	 * invariant, the left coordinate should be assigned the new value specified by
	 * the user.
	 * 
	 * @param int value containing possible new value for left coordinate of window
	 *
	 */
	public void setLeft(int value) { // Method Header
		if (value >= this.right) { // If statement. Checks if value entered by user breaks class invariant.
									// Class invariant for this is left < right
			throw new InvalidWindowException("Creates Invalid Window!!! Please Follow Class Invariant of left < right"); // Throws InvalidWindowException
		}
		this.left = value; // Assign new value to the field containing the left coordinate of the window
	}

	/**
	 * setRight() changes the value of the field containing the right coordinate of
	 * the window. Given that the new value entered doesn't break the class
	 * invariant, the right coordinate should be assigned the new value specified by
	 * the user.
	 * 
	 * @param int value containing possible new value for right coordinate of window
	 *
	 */
	public void setRight(int value) { // Method Header
		if (value <= this.left) { // If statement. Checks if value entered by user breaks class invariant.
									// Class invariant for this is left < right
			throw new InvalidWindowException("Creates Invalid Window!!! Please Follow Class Invariant of left < right"); // Throws InvalidWindowException
		}
		this.right = value; // Assign new value to the field containing the right coordinate of the window
	}

	/**
	 * setBottom() changes the value of the field containing the bottom coordinate
	 * of the window. Given that the new value entered doesn't break the class
	 * invariant, the bottom coordinate should be assigned the new value specified
	 * by the user.
	 * 
	 * @param int value containing possible new value for bottom coordinate of
	 *            window
	 *
	 */
	public void setBottom(int value) { // Method Header
		if (value >= this.top) { // If statement. Checks if value entered by user breaks class invariant.
									// Class invariant for this is bottom < top
			throw new InvalidWindowException("Creates Invalid Window!!! Please Follow Class Invariant of bottom < top"); // Throws InvalidWindowException
		}
		this.bottom = value; // Assign new value to the field containing the bottom coordinate of the window
	}

	/**
	 * setTop() changes the value of the field containing the top coordinate of the
	 * window. Given that the new value entered doesn't break the class invariant,
	 * the top coordinate should be assigned the new value specified by the user.
	 * 
	 * @param int value containing possible new value for top coordinate of window
	 *
	 */
	public void setTop(int value) { // Method Header
		if (value <= this.bottom) { // If statement. Checks if value entered by user breaks class invariant.
									// Class invariant for this is bottom < top
			throw new InvalidWindowException("Creates Invalid Window!!! Please Follow Class Invariant of bottom < top"); // Throws InvalidWindowException
		}
		this.top = value; // Assign new value to the field containing the top coordinate of the window
	}

	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Enclose & Overlap for Pairs of Windows

	/**
	 * encloses() checks to see whether a window encloses another. More specifically whether if the instance
	 * window encloses the argument window.
	 * 
	 * @param Window object w, which is the argument window being check to see if it is enclosed by the instance window
	 * 
	 * @return boolean value depending on whether the instance window encloses the argument window
	 * true if instance window encloses the argument window, false if it doesn't
	 *
	 */
	public boolean encloses(Window w) { // Method Header
		if (this.left <= w.left && this.right >= w.right && this.bottom <= w.bottom && this.top >= w.top) { // If statement.
			                                                                                             //Checks for encloses.
			return true; // Return boolean value true
		}
		return false; // Return boolean value false
	}

	
	/**
	 * overlaps() checks to see whether a window overlaps another. More specifically whether if the instance
	 * window overlaps the argument window.
	 * 
	 * @param Window object w, which is the argument window being check to see if it is overlapped by the instance window
	 * 
	 * @return boolean value depending on whether the instance window overlaps the argument window
	 * true if instance window overlaps the argument window, false if it doesn't
	 *
	 */
	public boolean overlaps(Window w) { // Method Header
		if ((this.right <= w.left) || (this.left >= w.right) || (this.bottom >= w.top) || (this.top <= w.bottom)) { // If statement.
																											//Checks for overlaps.
			return false; // Return boolean value false
		}
		return true; // Return boolean value true
	}

	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Counter Methods for Enclose & Overlap of Pairs of Windows in an Array

	/**
	 * overlapCount() counts the number of unordered overlapping pairs of windows in an array of window objects, Window[]windows
	 * 
	 * @param array of Window objects, Window[]windows, which is being check for the number of unordered overlapping pairs 
	 * of Windows it contains
	 * 
	 * @return counter which counted the number of unordered overlapping pairs of Windows in an array of window objects
	 *
	 */
	public static int overlapCount(Window[] windows) { //Method Header. *Overlapping pairs are unordered*
		int counter = 0; // Declare variable. Counter variable.
		for (int i = 0; i < windows.length; i++) { // For loop.
			for (int j = i + 1; j < windows.length; j++) { // For loop
				if (windows[i].overlaps(windows[j]) == true) { // If statement. Uses overlaps(Window w) method to check
																// for overlaps.
					counter++; // Increment counter by one.
				}
			}
		}
		return counter; // Return integer value of variable counter.
	}

	/**
	 * enclosureCount() counts the number of ordered enclosing pairs of windows in an array of window objects, Window[]windows
	 * 
	 * @param array of Window objects, Window[]windows, which is being check for the number of ordered enclosing pairs 
	 * of Windows it contains
	 * 
	 * @return counter which counted the number of ordered enclosing pairs of Windows in an array of window objects
	 *
	 */
	public static int enclosureCount(Window[] windows) { //Method Header. *Enclosed pairs are ordered*
		int counter = 0; // Declare variable. Counter variable.
		for (int i = 0; i < windows.length; i++) { // For loop.
			for (int j = 0; j < windows.length; j++) { // For loop
				if (windows[i].encloses(windows[j]) == true && i != j) { // If statement. Users encloses(Window w) to
																			// check for encloses.
					counter++; // Increment counter by one.
				}
			}
		}
		return counter; // Return integer value of variable counter.
	}

	/**
	 * main() runs test cases created by me on all created methods. Prints summary
	 * information on basic operations and halts with an error (and a stack trace)
	 * if any of the tests fail.
	 */
	public static void main(String[] args) {
		boolean resultBoolean; //Declare boolean variable. Will be used in 
		                        //all tests to store boolean value and check for correctness of results

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Test Getter Methods		
		System.out.println("\n/////////////////////////////////////////////////////");
		System.out.println("TEST GETTER METHODS");
		System.out.println("/////////////////////////////////////////////////////\n");

//Test 1		
		System.out.println("TEST 1 ===========================");
		Window p = new Window(1, 3, 4, 7);
		resultBoolean = (p.getLeft() == 1.0);
		System.out.println("Expected Left Window Value: 1.0  Recieved Left Window Value: " + p.getLeft());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		resultBoolean = (p.getRight() == 3.0);
		System.out.println("Expected Right Window Value: 3.0  Recieved Right Window Value: " + p.getRight());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		resultBoolean = (p.getBottom() == 4.0);
		System.out.println("Expected Bottom Window Value: 4.0  Recieved Bottom Window Value: " + p.getBottom());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		resultBoolean = (p.getTop() == 7.0);
		System.out.println("Expected Top Window Value: 7.0  Recieved Bottom Window Value: " + p.getTop());
		TestHelper.verify(resultBoolean == true, "Incorrect");

//Test 2		
		System.out.println("\nTEST 2 ===========================");
		Window p1 = new Window(-9, -1, 0, 3);
		resultBoolean = (p1.getLeft() == -9.0);
		System.out.println("Expected Left Window Value: -9.0  Recieved Left Window Value: " + p1.getLeft());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		resultBoolean = (p1.getRight() == -1.0);
		System.out.println("Expected Right Window Value: -1.0  Recieved Right Window Value: " + p1.getRight());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		resultBoolean = (p1.getBottom() == 0.0);
		System.out.println("Expected Bottom Window Value: 0.0  Recieved Bottom Window Value: " + p1.getBottom());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		resultBoolean = (p1.getTop() == 3.0);
		System.out.println("Expected Top Window Value: 3.0  Recieved Bottom Window Value: " + p1.getTop());
		TestHelper.verify(resultBoolean == true, "Incorrect");

//Test 3		
		System.out.println("\nTEST 3 ===========================");
		Window p2 = new Window(0, 6, -4, 8);
		resultBoolean = (p2.getLeft() == 0.0);
		System.out.println("Expected Left Window Value: 0.0  Recieved Left Window Value: " + p2.getLeft());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		resultBoolean = (p2.getRight() == 6.0);
		System.out.println("Expected Right Window Value: 6.0  Recieved Right Window Value: " + p2.getRight());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		resultBoolean = (p2.getBottom() == -4.0);
		System.out.println("Expected Bottom Window Value: -4.0  Recieved Bottom Window Value: " + p2.getBottom());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		resultBoolean = (p2.getTop() == 8.0);
		System.out.println("Expected Top Window Value: 8.0  Recieved Bottom Window Value: " + p2.getTop());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//Test Setter Methods		
		System.out.println("\n/////////////////////////////////////////////////////");
		System.out.println("TEST SETTER METHODS");
		System.out.println("/////////////////////////////////////////////////////\n");
		
//Test 4		
		System.out.println("TEST 4 ===========================");
		Window s = new Window(1, 3, 4, 7);
		System.out.println("Initial Set Left Value: " +s.getLeft());
		s.setLeft(-1);
		System.out.println("New Set Left Value: " +s.getLeft());
		resultBoolean = (s.getLeft() == -1.0);
		System.out.println("Expected Left Window Value: -1.0  Recieved Left Window Value: " + s.getLeft());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		
		System.out.println("\nInitial Set Right Value: " +s.getRight());
		s.setRight(5);
		System.out.println("New Set Right Value: " +s.getRight());
		resultBoolean = (s.getRight() == 5.0);
		System.out.println("Expected Right Window Value: 5.0  Recieved Right Window Value: " + s.getRight());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		
		System.out.println("\nInitial Set Bottom Value: " +s.getBottom());
		s.setBottom(1);
		System.out.println("New Set Bottom Value: " +s.getBottom());
		resultBoolean = (s.getBottom() == 1.0);
		System.out.println("Expected Bottom Window Value: 1.0  Recieved Bottom Window Value: " + s.getBottom());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		
		System.out.println("\nInitial Set Top Value: " +s.getTop());
		s.setTop(9);
		System.out.println("New Set Top Value: " +s.getTop());
		resultBoolean = (s.getTop() == 9.0);
		System.out.println("Expected Top Window Value: 9.0  Recieved Bottom Window Value: " + s.getTop());
		TestHelper.verify(resultBoolean == true, "Incorrect");		

//Test 5		
		System.out.println("\nTEST 5 ===========================");
		Window s1 = new Window(0, 1, 0, 9);
		System.out.println("Initial Set Left Value: " +s1.getLeft());
		s1.setLeft(-9);
		System.out.println("New Set Left Value: " +s1.getLeft());
		resultBoolean = (s1.getLeft() == -9.0);
		System.out.println("Expected Left Window Value: -9.0  Recieved Left Window Value: " + s1.getLeft());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		
		System.out.println("\nInitial Set Right Value: " +s1.getRight());
		s1.setRight(0);
		System.out.println("New Set Right Value: " +s1.getRight());
		resultBoolean = (s1.getRight() == 0.0);
		System.out.println("Expected Right Window Value: 0.0  Recieved Right Window Value: " + s1.getRight());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		
		System.out.println("\nInitial Set Bottom Value: " +s1.getBottom());
		s1.setBottom(8);
		System.out.println("New Set Bottom Value: " +s1.getBottom());
		resultBoolean = (s1.getBottom() == 8.0);
		System.out.println("Expected Bottom Window Value: 8.0  Recieved Bottom Window Value: " + s1.getBottom());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		
		System.out.println("\nInitial Set Top Value: " +s1.getTop());
		s1.setTop(12);
		System.out.println("New Set Top Value: " +s1.getTop());
		resultBoolean = (s1.getTop() == 12.0);
		System.out.println("Expected Top Window Value: 12.0  Recieved Bottom Window Value: " + s1.getTop());
		TestHelper.verify(resultBoolean == true, "Incorrect");	
	
//Test 6		
		System.out.println("\nTEST 6 ===========================");
		Window s2 = new Window(2, 10, -6, 3);
		System.out.println("Initial Set Left Value: " +s2.getLeft());
		s2.setLeft(9);
		System.out.println("New Set Left Value: " +s2.getLeft());
		resultBoolean = (s2.getLeft() == 9.0);
		System.out.println("Expected Left Window Value: 9.0  Recieved Left Window Value: " + s2.getLeft());
		TestHelper.verify(resultBoolean == true, "Incorrect");
				
		System.out.println("\nInitial Set Right Value: " +s2.getRight());
		s2.setRight(11);
		System.out.println("New Set Right Value: " +s2.getRight());
		resultBoolean = (s2.getRight() == 11.0);
		System.out.println("Expected Right Window Value: 11.0  Recieved Right Window Value: " + s2.getRight());
		TestHelper.verify(resultBoolean == true, "Incorrect");
		
		System.out.println("\nInitial Set Bottom Value: " +s2.getBottom());
		s2.setBottom(1);
		System.out.println("New Set Bottom Value: " +s2.getBottom());
		resultBoolean = (s2.getBottom() == 1.0);
		System.out.println("Expected Bottom Window Value: 1.0  Recieved Bottom Window Value: " + s2.getBottom());
		TestHelper.verify(resultBoolean == true, "Incorrect");
				
		System.out.println("\nInitial Set Top Value: " +s2.getTop());
		s2.setTop(8);
		System.out.println("New Set Top Value: " +s2.getTop());
		resultBoolean = (s2.getTop() == 8.0);
		System.out.println("Expected Top Window Value: 8.0  Recieved Bottom Window Value: " + s2.getTop());
		TestHelper.verify(resultBoolean == true, "Incorrect");			

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
//Test encloses(Window w) Method		
		System.out.println("\n/////////////////////////////////////////////////////");
		System.out.println("TEST encloses(Window w) METHOD");
		System.out.println("/////////////////////////////////////////////////////\n");
		
//Test 7		
		System.out.println("TEST 7 ===========================");
		Window z = new Window();
		z.setLeft(-1);
		z.setRight(1);
		z.setBottom(-1);
		z.setTop(1);
		
		Window z1 = new Window();
		z1.setLeft(-3);
		z1.setRight(3);
		z1.setBottom(-3);
		z1.setTop(3);
		
		System.out.println("Window z Coodinates:");	
		System.out.println("Left: " +z.getLeft());	
		System.out.println("Right: " +z.getRight());	
		System.out.println("Bottom: " +z.getBottom());	
		System.out.println("Top: " +z.getTop());	
		
		System.out.println("\nWindow z1 Coodinates:");	
		System.out.println("Left: " +z1.getLeft());	
		System.out.println("Right: " +z1.getRight());	
		System.out.println("Bottom: " +z1.getBottom());	
		System.out.println("Top: " +z1.getTop());	

		resultBoolean = z1.encloses(z);
		System.out.println("\nz1 encloses z: " +resultBoolean);
		System.out.println("Expected Outcome: true");
		TestHelper.verify(resultBoolean == true, "Incorrect");

		resultBoolean = z.encloses(z1);
		System.out.println("\nz encloses z1: " +resultBoolean);
		System.out.println("Expected Outcome: false");
		TestHelper.verify(resultBoolean == false, "Incorrect");
		
//Test 8	
		System.out.println("\nTEST 8 ===========================");
		Window z2 = new Window(4, 5, 6, 7);
		Window z3 = new Window(4, 5, 6, 7);
			
		System.out.println("Window z2 Coodinates:");	
		System.out.println("Left: " +z2.getLeft());	
		System.out.println("Right: " +z2.getRight());	
		System.out.println("Bottom: " +z2.getBottom());	
		System.out.println("Top: " +z2.getTop());	
			
		System.out.println("\nWindow z3 Coodinates:");	
		System.out.println("Left: " +z3.getLeft());	
		System.out.println("Right: " +z3.getRight());	
		System.out.println("Bottom: " +z3.getBottom());	
		System.out.println("Top: " +z3.getTop());	

		resultBoolean = z3.encloses(z2);
		System.out.println("\nz3 encloses z2: " +resultBoolean);
		System.out.println("Expected Outcome: true");
		TestHelper.verify(resultBoolean == true, "Incorrect");

		resultBoolean = z2.encloses(z3);
		System.out.println("\nz2 encloses z3: " +resultBoolean);
		System.out.println("Expected Outcome: true");
		TestHelper.verify(resultBoolean == true, "Incorrect");
				
//Test 9	
		System.out.println("\nTEST 9 ===========================");
		Window z4 = new Window(1, 3, 1, 3);
		Window z5 = new Window(-9, -7, -9, -7);
					
		System.out.println("Window z4 Coodinates:");	
		System.out.println("Left: " +z4.getLeft());	
		System.out.println("Right: " +z4.getRight());	
		System.out.println("Bottom: " +z4.getBottom());	
		System.out.println("Top: " +z4.getTop());	
					
		System.out.println("\nWindow z5 Coodinates:");	
		System.out.println("Left: " +z5.getLeft());	
		System.out.println("Right: " +z5.getRight());	
		System.out.println("Bottom: " +z5.getBottom());	
		System.out.println("Top: " +z5.getTop());	

		resultBoolean = z5.encloses(z4);
		System.out.println("\nz5 encloses z4: " +resultBoolean);
		System.out.println("Expected Outcome: false");
		TestHelper.verify(resultBoolean == false, "Incorrect");

		resultBoolean = z4.encloses(z5);
		System.out.println("\nz4 encloses z5: " +resultBoolean);
		System.out.println("Expected Outcome: false");
		TestHelper.verify(resultBoolean == false, "Incorrect");		

//Test 10	
		System.out.println("\nTEST 10 ===========================");
		Window z6 = new Window(1, 5, -2, 7);
		Window z7 = new Window(2, 6, -3, 2);
							
		System.out.println("Window z6 Coodinates:");	
		System.out.println("Left: " +z6.getLeft());	
		System.out.println("Right: " +z6.getRight());	
		System.out.println("Bottom: " +z6.getBottom());	
		System.out.println("Top: " +z6.getTop());	
							
		System.out.println("\nWindow z7 Coodinates:");	
		System.out.println("Left: " +z7.getLeft());	
		System.out.println("Right: " +z7.getRight());	
		System.out.println("Bottom: " +z7.getBottom());	
		System.out.println("Top: " +z7.getTop());	

		resultBoolean = z7.encloses(z6);
		System.out.println("\nz7 encloses z6: " +resultBoolean);
		System.out.println("Expected Outcome: false");
		TestHelper.verify(resultBoolean == false, "Incorrect");

		resultBoolean = z6.encloses(z7);
		System.out.println("\nz6 encloses z7: " +resultBoolean);
		System.out.println("Expected Outcome: false");
		TestHelper.verify(resultBoolean == false, "Incorrect");	
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
//Test overlaps(Window w) Method	
		System.out.println("\n/////////////////////////////////////////////////////");
		System.out.println("TEST overlaps(Window w) METHOD");
		System.out.println("/////////////////////////////////////////////////////\n");
		
//Test 11		
		System.out.println("TEST 11 ===========================");
		Window t = new Window(-1, 1, -1, 1);
		Window t1 = new Window(-3, 3, -3, 3);
		
		System.out.println("Window t Coodinates:");	
		System.out.println("Left: " +t.getLeft());	
		System.out.println("Right: " +t.getRight());	
		System.out.println("Bottom: " +t.getBottom());	
		System.out.println("Top: " +t.getTop());	
		
		System.out.println("\nWindow t1 Coodinates:");	
		System.out.println("Left: " +t1.getLeft());	
		System.out.println("Right: " +t1.getRight());	
		System.out.println("Bottom: " +t1.getBottom());	
		System.out.println("Top: " +t1.getTop());	

		resultBoolean = t1.overlaps(t);
		System.out.println("\nt1 overlaps t: " +resultBoolean);
		System.out.println("Expected Outcome: true");
		TestHelper.verify(resultBoolean == true, "Incorrect");

		resultBoolean = t.overlaps(t1);
		System.out.println("\nt overlaps t1: " +resultBoolean);
		System.out.println("Expected Outcome: true");
		TestHelper.verify(resultBoolean == true, "Incorrect");
		
//Test 12		
		System.out.println("\nTEST 12 ===========================");
		Window t2 = new Window(-6, -1, 0, 8);
		Window t3 = new Window(-3, 3, -3, 1);
				
		System.out.println("Window t2 Coodinates:");	
		System.out.println("Left: " +t2.getLeft());	
		System.out.println("Right: " +t2.getRight());	
		System.out.println("Bottom: " +t2.getBottom());	
		System.out.println("Top: " +t2.getTop());	
			
		System.out.println("\nWindow t3 Coodinates:");	
		System.out.println("Left: " +t3.getLeft());	
		System.out.println("Right: " +t3.getRight());	
		System.out.println("Bottom: " +t3.getBottom());	
		System.out.println("Top: " +t3.getTop());	

		resultBoolean = t3.overlaps(t2);
		System.out.println("\nt3 overlaps t2: " +resultBoolean);
		System.out.println("Expected Outcome: true");
		TestHelper.verify(resultBoolean == true, "Incorrect");

		resultBoolean = t2.overlaps(t3);
		System.out.println("\nt2 overlaps t3: " +resultBoolean);
		System.out.println("Expected Outcome: true");
		TestHelper.verify(resultBoolean == true, "Incorrect");		
			
//Test 13		
		System.out.println("\nTEST 13 ===========================");
		Window t4 = new Window(-6, -1, -6, -1);
		Window t5 = new Window(1, 6, 1, 6);
						
		System.out.println("Window t4 Coodinates:");	
		System.out.println("Left: " +t4.getLeft());	
		System.out.println("Right: " +t4.getRight());	
		System.out.println("Bottom: " +t4.getBottom());	
		System.out.println("Top: " +t4.getTop());	
					
		System.out.println("\nWindow t5 Coodinates:");	
		System.out.println("Left: " +t5.getLeft());	
		System.out.println("Right: " +t5.getRight());	
		System.out.println("Bottom: " +t5.getBottom());	
		System.out.println("Top: " +t5.getTop());	

		resultBoolean = t5.overlaps(t4);
		System.out.println("\nt5 overlaps t4: " +resultBoolean);
		System.out.println("Expected Outcome: false");
		TestHelper.verify(resultBoolean == false, "Incorrect");

		resultBoolean = t4.overlaps(t5);
		System.out.println("\nt4 overlaps t5: " +resultBoolean);
		System.out.println("Expected Outcome: false");
		TestHelper.verify(resultBoolean == false, "Incorrect");				
		
//Test 14		
		System.out.println("\nTEST 14 ===========================");
		Window t6 = new Window(-5, 5, -5, 5);
		Window t7 = new Window(4, 8, 4, 8);
								
		System.out.println("Window t6 Coodinates:");	
		System.out.println("Left: " +t6.getLeft());	
		System.out.println("Right: " +t6.getRight());	
		System.out.println("Bottom: " +t6.getBottom());	
		System.out.println("Top: " +t6.getTop());	
							
		System.out.println("\nWindow t7 Coodinates:");	
		System.out.println("Left: " +t7.getLeft());	
		System.out.println("Right: " +t7.getRight());	
		System.out.println("Bottom: " +t7.getBottom());	
		System.out.println("Top: " +t7.getTop());	

		resultBoolean = t7.overlaps(t6);
		System.out.println("\nt7 overlaps t6: " +resultBoolean);
		System.out.println("Expected Outcome: true");
		TestHelper.verify(resultBoolean == true, "Incorrect");

		resultBoolean = t6.overlaps(t7);
		System.out.println("\nt6 overlaps t7: " +resultBoolean);
		System.out.println("Expected Outcome: true");
		TestHelper.verify(resultBoolean == true, "Incorrect");				
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
//Test overlapCounter(Window[]windows) Method		
		System.out.println("\n/////////////////////////////////////////////////////");
		System.out.println("TEST overlapCounter(Window[]windows) METHOD");
		System.out.println("/////////////////////////////////////////////////////\n");
		
//Test 15		
		System.out.println("TEST 15 ===========================");
		Window [] oc = new Window[2];
		oc[0] = new Window(3, 4, 5, 6);
		oc[1] = new Window(3, 4, 5, 6);

		resultBoolean = (overlapCount(oc) == 1);
		System.out.println("Returned overlapCount: " +overlapCount(oc));
		System.out.println("Expected overlapCount: 1");
		TestHelper.verify(resultBoolean == true, "Incorrect");
		
//Test 16		
		System.out.println("\nTEST 16 ===========================");
		Window [] oc1 = new Window[8];
		oc1[0] = new Window(-1, 1, -1, 1);
		oc1[1] = new Window(-2, 2, -2, 2);
		oc1[2] = new Window(-3, 3, -3, 3);
		oc1[3] = new Window(-4, 4, -4, 4);
		oc1[4] = new Window(-5, 5, -5, 5);
		oc1[5] = new Window(-6, 6, -6, 6);
		oc1[6] = new Window(-7, 7, -7, 7);
		oc1[7] = new Window(-8, 8, -8, 8);

		resultBoolean = (overlapCount(oc1) == 28);
		System.out.println("Returned overlapCount: " +overlapCount(oc1));
		System.out.println("Expected overlapCount: 28");
		TestHelper.verify(resultBoolean == true, "Incorrect");

//Test 17		
		System.out.println("\nTEST 17 ===========================");
		Window [] oc2 = new Window[4];
		oc2[0] = new Window(-9, 2, 1, 6);
		oc2[1] = new Window(-2, 6, 4, 5);
		oc2[2] = new Window(0, 1, -3, 2);
		oc2[3] = new Window(1, 4, 2, 4);

		resultBoolean = (overlapCount(oc2) == 3);
		System.out.println("Returned overlapCount: " +overlapCount(oc2));
		System.out.println("Expected overlapCount: 3");
		TestHelper.verify(resultBoolean == true, "Incorrect");		
				
//Test 18		
		System.out.println("\nTEST 18 ===========================");
		Window [] oc3 = new Window[3];
		oc3[0] = new Window(-2, 3, -1, 5);
		oc3[1] = new Window(-1, 5, -3, -1);
		oc3[2] = new Window(-7, 0, -2, 3);

		resultBoolean = (overlapCount(oc3) == 2);
		System.out.println("Returned overlapCount: " +overlapCount(oc3));
		System.out.println("Expected overlapCount: 2");
		TestHelper.verify(resultBoolean == true, "Incorrect");	
		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////			
//Test enclosureeCounter(Window[]windows) Method			
		System.out.println("\n/////////////////////////////////////////////////////");
		System.out.println("TEST enclosureCounter(Window[]windows) METHOD");
		System.out.println("/////////////////////////////////////////////////////\n");

//Test 19		
		System.out.println("TEST 19 ===========================");
		Window [] ec = new Window[2];
		ec[0] = new Window(3, 4, 5, 6);
		ec[1] = new Window(3, 4, 5, 6);

		resultBoolean = (enclosureCount(ec) == 2);
		System.out.println("Returned enclosureCount: " +enclosureCount(ec));
		System.out.println("Expected enclosureCount: 2");
		TestHelper.verify(resultBoolean == true, "Incorrect");		
		
//Test 20		
		System.out.println("\nTEST 20 ===========================");
		Window [] ec1 = new Window[5];
		ec1[0] = new Window(-1, 1, -1, 1);
		ec1[1] = new Window(-2, 2, -2, 2);
		ec1[2] = new Window(-3, 3, -3, 3);
		ec1[3] = new Window(-4, 4, -4, 4);
		ec1[4] = new Window(-5, 5, -5, 5);

		resultBoolean = (enclosureCount(ec1) == 10);
		System.out.println("Returned enclosureCount: " +enclosureCount(ec1));
		System.out.println("Expected enclosureCount: 10");
		TestHelper.verify(resultBoolean == true, "Incorrect");	
		
//Test 21		
		System.out.println("\nTEST 21 ===========================");
		Window [] ec2 = new Window[3];
		ec2[0] = new Window(0, 3, 4, 7);
		ec2[1] = new Window(-2, 7, -1, 2);
		ec2[2] = new Window(-5, 3, 0, 5);

		resultBoolean = (enclosureCount(ec2) == 0);
		System.out.println("Returned enclosureCount: " +enclosureCount(ec2));
		System.out.println("Expected enclosureCount: 0");
		TestHelper.verify(resultBoolean == true, "Incorrect");	

//Test 22		
		System.out.println("\nTEST 22 ===========================");
		Window [] ec3 = new Window[4];
		ec3[0] = new Window(1, 8, 4, 6);
		ec3[1] = new Window(2, 5, 4, 5);
		ec3[2] = new Window(-9, 3, -5, -1);
		ec3[3] = new Window(2, 3, 1, 7);

		resultBoolean = (enclosureCount(ec3) == 1);
		System.out.println("Returned enclosureCount: " +enclosureCount(ec3));
		System.out.println("Expected enclosureCount: 1");
		TestHelper.verify(resultBoolean == true, "Incorrect");		

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////				
//Test for Class Invariants		
//		Window ci = new Window(-1, 1, -2, 2);
//		ci.setLeft(2);
//		ci.setRight(-3);
//		ci.setBottom(9);
//		ci.setTop(-8);
		
//      Window ci1 = new Window(3, -3, 4, 7);
	}
}
