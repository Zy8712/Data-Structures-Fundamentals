/**********************************************************
 * EECS2011N: Fundamentals of Data Structures,  Winter 2020
 * Assignment 2, Problem 3: AugmentedStack.java
 * Student Name: Bryan Li
 * Student EECS account: [removed for privacy reasons]
 * Student ID number: [removed for privacy reasons]
 **********************************************************/
package A2;

import java.util.Stack; //Import Stack class. Runs on LIFO (Last-In, First-Out) principles.
						//Allows us to directly refer to the class when necessary.

public class AugmentedStack<E extends Comparable<? super E>> { //

//////////////////////////////////////////////////////////////////////
// Private auxiliary fields of the augmented data structure.
	private Stack<E> stackVal = new Stack<E>(); // Declare Stack instance/object. Used to hold the values of the
												// AugmentedStack object.
	private Stack<E> minVal = new Stack<E>(); // Declare Stack instance/object. Top of this stack indicates contains the
												// smallest value in stackVal. Elements are pushed onto the stack
												// whenever there is a new minimum value. Whenever that value is popped
												// from stackVal it is also popped from minVal.

//////////////////////////////////////////////////////////////////////
// Operations of the AugmentedStack.
	/**
	 * push(), pushes an object onto stack.
	 * 
	 * Operates with running time of O(1)
	 * 
	 * @param x is the value to be pushed onto the stack, specified by user.
	 */
	public void push(E x) {
		stackVal.push(x); // Uses push operation from Stack class to push the parameter x onto stack,
							// stackVal.
		if (minVal.isEmpty()) { // Conditional statement. Checks to see if there has been a minimum value
								// declared yet for the stack. If there hasn't then push x onto stack,
								// minVal.
			minVal.push(x); // Push x onto the stack minVal.
		} else if (minVal.peek().compareTo(x) >= 0) { // Conditional statement. Checks to see whether the value
														// currently being pushed on the the stack, is less than the
														// current declared minimum, i.e. the value currently at the top
														// of stack, minVal. This indicates that there is a new minimum
														// value that needs to be declared, i.e. push onto minVal.
			minVal.push(x); // Push x onto the stack minVal.
		}
	}

	/**
	 * pop(), pops an object from the stack.
	 * 
	 * Operates with running time of O(1)
	 * 
	 * @return null if the stack is empty
	 * 
	 * @return top element of the stack that was just popped (given that stack is
	 *         not empty)
	 */
	public E pop() {
		if (stackVal.isEmpty()) { // Conditional statement. Checks to see whether there are any values in the
									// stack. If there aren't, then there's nothing to pop.
			return null; // Return value of null since the stack is empty.
		} else { // Conditional statement. If there are exists values in the stack.
			E top = stackVal.pop(); // Declare variable that is the same type as the values in the stack. Set it
									// equal to the top element of the stack. Pop the top element off the stack.
			if (minVal.peek().equals(top)) { // Conditional Statement. If the minimum value is equal to the value that
												// was popped.
				minVal.pop(); // Pop the value off the stack.
			}
			return top; // Return the value of the element that was popped off the stack.
		}
	}

	/**
	 * getMin(), gets the minimum value of the stack.
	 * 
	 * Operates with running time of O(1)
	 * 
	 * @return null if the stack is empty
	 * 
	 * @return the minimum value of the stack (if stack is not empty)
	 */
	public E getMin() {
		if (minVal.isEmpty()) { // Conditional statement. Checks to see if stack is empty.
			return null; // Return value of null since the stack is empty.
		} else {
			return minVal.peek(); // Return the value at the top of stack, minVal, which is minimum value.
		}
	}

	/**
	 * isEmpty(), checks if the stack is empty.
	 * 
	 * Operates with running time of O(1)
	 * 
	 * @return true if stack is empty
	 * 
	 * @return false if stack is not empty
	 */
	public boolean isEmpty() {
		return stackVal.isEmpty(); // Return a boolean value depending on whether the stack is empty or not.
	}

	/**
	 * top(), gets the element at the top of the stack.
	 * 
	 * Operates with running time of O(1)
	 * 
	 * @return null if the stack is empty
	 * 
	 * @return value of the element at the top of the stack
	 */
	public E top() {
		if (stackVal.isEmpty()) { // Conditional statement. Checks to see if stack is empty.
			return null; // Return value of null since the stack is empty.
		}
		return stackVal.peek(); // Return value at the top of the stack.
	}

/////////////////////////////////////////////////////////////////////////////////////
// Main Method. Used to run test cases.	
	public static void main(String[] args) {
//////////////////////////////////////////////////////////////////////////////////////
//First Test. Uses Integer Stack.		
		System.out.println("============== TEST 1 ==============");
		AugmentedStack<Integer> intStack = new AugmentedStack<Integer>();
		intStack.push(420);
		System.out.println("Value Pushed: 420");
		intStack.push(720);
		System.out.println("Value Pushed: 720");
		intStack.push(360);
		System.out.println("Value Pushed: 360");

// First Run		
		System.out.println("\n<<< First Run >>>");
		System.out.println("\nMinimum Value Expected: 360");
		System.out.println("Minimum Value Received: " + intStack.getMin());

		System.out.println("\nTop of Stack Value Expected: 360");
		System.out.println("Top of Stack Value Received: " + intStack.top());

		System.out.println("\nStack Empty? Expected Value: False");
		System.out.println("Stack Empty? Received Value: " + intStack.isEmpty());

// Second Run		
		System.out.println("\n<<< Second Run >>>");
		System.out.println("\nExpected Value To Be Popped: 360");
		System.out.println("Value Popped Off Stack: " + intStack.pop());

		System.out.println("\nMinimum Value Expected: 420");
		System.out.println("Minimum Value Received: " + intStack.getMin());

		System.out.println("\nTop of Stack Value Expected: 720");
		System.out.println("Top of Stack Value Received: " + intStack.top());

		System.out.println("\nStack Empty? Expected Value: False");
		System.out.println("Stack Empty? Received Value: " + intStack.isEmpty());

// Third Run		
		System.out.println("\n<<< Third Run >>>");
		System.out.println("\nExpected Value To Be Popped: 720");
		System.out.println("Value Popped Off Stack: " + intStack.pop());

		System.out.println("\nMinimum Value Expected: 420");
		System.out.println("Minimum Value Received: " + intStack.getMin());

		System.out.println("\nTop of Stack Value Expected: 420");
		System.out.println("Top of Stack Value Received: " + intStack.top());

		System.out.println("\nStack Empty? Expected Value: False");
		System.out.println("Stack Empty? Received Value: " + intStack.isEmpty());

// Fourth Final Run
		System.out.println("\n<<< Fourth Run >>>");
		System.out.println("\nExpected Value To Be Popped: 420");
		System.out.println("Value Popped Off Stack: " + intStack.pop());

		System.out.println("\nMinimum Value Expected: null");
		System.out.println("Minimum Value Received: " + intStack.getMin());

		System.out.println("\nTop of Stack Value Expected: null");
		System.out.println("Top of Stack Value Received: " + intStack.top());

		System.out.println("\nStack Empty? Expected Value: True");
		System.out.println("Stack Empty? Received Value: " + intStack.isEmpty());

//////////////////////////////////////////////////////////////////////////////////////
// Second Test. Uses Double Stack.		
		System.out.println("\n============== TEST 2 ==============");
		AugmentedStack<Double> doubleStack = new AugmentedStack<Double>();
		doubleStack.push(420.28);
		System.out.println("Value Pushed: 420.28");
		doubleStack.push(420.2);
		System.out.println("Value Pushed: 420.2");
		doubleStack.push(420.3);
		System.out.println("Value Pushed: 420.3");

// First Run			
		System.out.println("\n<<< First Run >>>");
		System.out.println("\nMinimum Value Expected: 420.2");
		System.out.println("Minimum Value Received: " + doubleStack.getMin());

		System.out.println("\nTop of Stack Value Expected: 420.3");
		System.out.println("Top of Stack Value Received: " + doubleStack.top());

		System.out.println("\nStack Empty? Expected Value: False");
		System.out.println("Stack Empty? Received Value: " + doubleStack.isEmpty());

// Second Run		
		System.out.println("\n<<< Second Run >>>");
		System.out.println("\nExpected Value To Be Popped: 420.3");
		System.out.println("Value Popped Off Stack: " + doubleStack.pop());

		System.out.println("\nMinimum Value Expected: 420.2");
		System.out.println("Minimum Value Received: " + doubleStack.getMin());

		System.out.println("\nTop of Stack Value Expected: 420.2");
		System.out.println("Top of Stack Value Received: " + doubleStack.top());

		System.out.println("\nStack Empty? Expected Value: False");
		System.out.println("Stack Empty? Received Value: " + doubleStack.isEmpty());

// Third Run		
		System.out.println("\n<<< Third Run >>>");
		System.out.println("\nExpected Value To Be Popped: 420.2");
		System.out.println("Value Popped Off Stack: " + doubleStack.pop());

		System.out.println("\nMinimum Value Expected: 420.28");
		System.out.println("Minimum Value Received: " + doubleStack.getMin());

		System.out.println("\nTop of Stack Value Expected: 420.28");
		System.out.println("Top of Stack Value Received: " + doubleStack.top());

		System.out.println("\nStack Empty? Expected Value: False");
		System.out.println("Stack Empty? Received Value: " + doubleStack.isEmpty());

// Fourth and Final Run		
		System.out.println("\n<<< Fourth Run >>>");
		System.out.println("\nExpected Value To Be Popped: 420.28");
		System.out.println("Value Popped Off Stack: " + doubleStack.pop());

		System.out.println("\nMinimum Value Expected: null");
		System.out.println("Minimum Value Received: " + doubleStack.getMin());

		System.out.println("\nTop of Stack Value Expected: null");
		System.out.println("Top of Stack Value Received: " + doubleStack.top());

		System.out.println("\nStack Empty? Expected Value: True");
		System.out.println("Stack Empty? Received Value: " + doubleStack.isEmpty());

//////////////////////////////////////////////////////////////////////////////////////
//Third Test. Uses String Stack.				
		System.out.println("\n============== TEST 3 ==============");
		AugmentedStack<String> stringStack = new AugmentedStack<String>();
		stringStack.push("Z");
		System.out.println("Value Pushed: Z");
		stringStack.push("B");
		System.out.println("Value Pushed: B");
		stringStack.push("C");
		System.out.println("Value Pushed: C");
		stringStack.push("A");
		System.out.println("Value Pushed: A");
		stringStack.push("Bc");
		System.out.println("Value Pushed: Bc");

// First Run
		System.out.println("\n<<< First Run >>>");
		System.out.println("\nMinimum Value Expected: A");
		System.out.println("Minimum Value Received: " + stringStack.getMin());

		System.out.println("\nTop of Stack Value Expected: Bc");
		System.out.println("Top of Stack Value Received: " + stringStack.top());

		System.out.println("\nStack Empty? Expected Value: False");
		System.out.println("Stack Empty? Received Value: " + stringStack.isEmpty());

// Second Run
		System.out.println("\n<<< Second Run >>>");
		System.out.println("\nExpected Value To Be Popped: Bc");
		System.out.println("Value Popped Off Stack: " + stringStack.pop());

		System.out.println("\nMinimum Value Expected: A");
		System.out.println("Minimum Value Received: " + stringStack.getMin());

		System.out.println("\nTop of Stack Value Expected: A");
		System.out.println("Top of Stack Value Received: " + stringStack.top());

		System.out.println("\nStack Empty? Expected Value: False");
		System.out.println("Stack Empty? Received Value: " + stringStack.isEmpty());

// Third Run
		System.out.println("\n<<< Third Run >>>");
		System.out.println("\nExpected Value To Be Popped: A");
		System.out.println("Value Popped Off Stack: " + stringStack.pop());

		System.out.println("\nMinimum Value Expected: B");
		System.out.println("Minimum Value Received: " + stringStack.getMin());

		System.out.println("\nTop of Stack Value Expected: C");
		System.out.println("Top of Stack Value Received: " + stringStack.top());

		System.out.println("\nStack Empty? Expected Value: False");
		System.out.println("Stack Empty? Received Value: " + stringStack.isEmpty());

// Fourth Run
		System.out.println("\n<<< Fourth Run >>>");
		System.out.println("\nExpected Value To Be Popped: C");
		System.out.println("Value Popped Off Stack: " + stringStack.pop());

		System.out.println("\nMinimum Value Expected: B");
		System.out.println("Minimum Value Received: " + stringStack.getMin());

		System.out.println("\nTop of Stack Value Expected: B");
		System.out.println("Top of Stack Value Received: " + stringStack.top());

		System.out.println("\nStack Empty? Expected Value: False");
		System.out.println("Stack Empty? Received Value: " + stringStack.isEmpty());

// Fifth Run
		System.out.println("\n<<< Fifth Run >>>");
		System.out.println("\nExpected Value To Be Popped: B");
		System.out.println("Value Popped Off Stack: " + stringStack.pop());

		System.out.println("\nMinimum Value Expected: Z");
		System.out.println("Minimum Value Received: " + stringStack.getMin());

		System.out.println("\nTop of Stack Value Expected: Z");
		System.out.println("Top of Stack Value Received: " + stringStack.top());

		System.out.println("\nStack Empty? Expected Value: False");
		System.out.println("Stack Empty? Received Value: " + stringStack.isEmpty());

// Sixth Run
		System.out.println("\n<<< Sixth Run >>>");
		System.out.println("\nExpected Value To Be Popped: Z");
		System.out.println("Value Popped Off Stack: " + stringStack.pop());

		System.out.println("\nMinimum Value Expected: null");
		System.out.println("Minimum Value Received: " + stringStack.getMin());

		System.out.println("\nTop of Stack Value Expected: null");
		System.out.println("Top of Stack Value Received: " + stringStack.top());

		System.out.println("\nStack Empty? Expected Value: True");
		System.out.println("Stack Empty? Received Value: " + stringStack.isEmpty());

	}
}
