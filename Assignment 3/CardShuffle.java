/**********************************************************
 * EECS2011N: Fundamentals of Data Structures,  Winter 2020
 * Assignment 3, Problem 1: CardShuffle.java
 * Student Name: Bryan Li
 * Student EECS account: [removed for privacy reasons]
 * Student ID number: [removed for privacy reasons]
 **********************************************************/
package A3;

import java.util.Scanner; // Import scanner. Used to take in user's input.

public class CardShuffle {

////////////////////////////////////////////////////////////////////////////////////////////////////////
// Nested class, SinglyLinkedList. Used to store cards/elements entered by user.
	/**
	 * Nested class, SinglyLinkedList. Used to store cards/elements entered by the
	 * user. A concrete data structure that consists of a sequence of nodes starting
	 * from a head pointer. Each node contains an element and a link to the next
	 * node.
	 */
	private static class SinglyLinkedList<E> {
		private Node<E> head = null; // Declare head pointer. Points to the head of the SinglyLinkedList.
		private Node<E> tail = null; // Declare tail pointer. Points to the tail of the SinglyLinkedList.

		/**
		 * add(), is a mutator/update method used to add cards/elements into the
		 * SinglyLinkedList. It does so by creating a new node and making the node hold
		 * the value. Pointers for the head and tail of the SinglyLinkedList will be
		 * shifted.
		 * 
		 * @param val is an element/value of type E (same type as SinglyLinkedList and
		 *            Nodes) which is entered by the user
		 * 
		 */
		public void add(E val) {
			if (this.tail == null) { // Conditional if statement. If this is the first value being added to the list.
				this.tail = new Node<E>(val); // Create a new Node. Make tail pointer point to it.
				this.head = this.tail; // Make head pointer point to the node as well.
			} else { // Else statement. If this isn't the first value being added to the list.
				this.tail.next = new Node<E>(val); // Create a new Node. Make current tail node point to it.
				this.tail = this.tail.next; // Make tail pointer point to the new Node.
			}
		}

		/**
		 * getTail(), is an accessor method used to get the tail pointer of the
		 * SinglyLinkedList which points to the last node of the SinglyLinkedList, which
		 * is its tail.
		 * 
		 * @return this.tail which is the tail pointer of the SinglyLinkedList
		 * 
		 */
		public Node<E> getTail() {
			return this.tail; // Return the tail pointer which points to the tail of the SinglyLinkedList.
		}

		/**
		 * CardShuffle(), is a mutator/update method that is used to shuffle the
		 * cards/elements of the SinglyLinkedList using the method specified in the
		 * question. It performs a card shuffle on a list of 2n elements, by converting
		 * it into two lists. A card shuffle is a permutation where a list L is cut into
		 * two lists, L1 and L2, where L1 is the first half of L and L2 is the second
		 * half of L, and then these two lists are merged into one by taking the first
		 * element in L1, then the first element in L2, followed by the second element
		 * in L1, the second element in L2, and so on. The input list is a singly linked
		 * list. Your algorithm has to perform the shuffle in-place, i.e., it is allowed
		 * to use only O(1) additional memory cells. So, you are not allowed to use any
		 * additional (or duplicate) list of any kind that may use more than O(1) cells.
		 * Runs with a time complexity of O(n)
		 * 
		 * @param midPoint is a pointer the the middle of the SinglyLinkedList
		 * 
		 */
		public void CardShuffle(Node<E> midPoint) {
			Node<E> markerOne = this.head; // Declare a pointer to point to the head of the first half of the
										// SinglyLinkedList.
			Node<E> markerTwo = midPoint.getNext(); // Declare a pointer to point to the head of the second half of the
													// SinglyLinkedList.
			midPoint.setNext(null); // Remove link to next node for the midpoint node. Essentially cuts list into
									// two sections/sublists.

			while (markerTwo != null) { // While loop. Loops until it reaches the end of the SinglyLinkedList.
				Node<E> markerOneNext = markerOne.getNext(); // Declare a pointer. Points to the next node after
																// markerOne pointer.
				Node<E> markerTwoNext = markerTwo.getNext(); // Declare a pointer. Points to the next node after
																// markerTwo pointer.

				markerOne.setNext(markerTwo); // Both markerOne and markerTwo are point to their respective nodes. Make
												// markerTwo's node be the next node in the list after markerOne's node.
				markerTwo.setNext(markerOneNext); // Both markerOneNext and markerTwo are point to their respective
													// nodes. Make markerTwo's node be the next node in the list after
													// markerOneNext's node.
				markerOne = markerOneNext; // Make markerOne point to the next node.
				markerTwo = markerTwoNext; // Make markerTwo point to the next node.
			}
		}

		/**
		 * getString(), is used to generate a string format of all the cards/elements
		 * stored in the SinglyLinkedList.
		 * 
		 * @return sb.toString() which is the string we created using the StringBuilder
		 *         object.
		 */
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(); // Declare StringBuilder object. Used to build the output string.
			sb.append("{ "); // Call the append method of the StringBuilder class. Adds on to the string.
			Node<E> curr = this.head; // Declare a pointer that points to the head of the SinglyLinkedList.
			while (curr != null) { // Loop. Loops until we reach the end of the SinglyLinkedList
				sb.append("["); // Call the append method of the StringBuilder class. Adds on to the string.
				sb.append(curr.element.toString()); // Call the append method of the StringBuilder class. Adds element
													// onto the string.
				sb.append("]"); // Call the append method of the StringBuilder class. Adds on to the string.
				curr = curr.next; // Shifts pointer onto the next node.
			}
			sb.append(" }"); // Call the append method of the StringBuilder class. Adds on to the string.
			return sb.toString(); // Returns the string output.
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////
//Nested class, Node. Sequences of nodes makes up the SinglyLinkedList.
	/**
	 * Nested class, Node. Used to create Node objects that makes up the
	 * SinglyLinkedList. Each node carries an element and a link to the next node.
	 */
	private static class Node<E> {
		private E element; // Field/instance variable of the Node object. Holds the element of the node.
		private Node<E> next; // Field/instance variable of the Node object. Pointer, that points to the next
								// node.

		/**
		 * Node class constructor. Sets the element/value of the node equal to the value
		 * entered/specified by the user.
		 * 
		 * @param val which is the card/element value entered by the user.
		 * 
		 */
		public Node(E val) {
			this.element = val; // Initializes the element held by the node equal to value specified.
		}

		/**
		 * getNext(), is an accessor method that returns the link to the next node.
		 * 
		 * @return this.next the link to the next node
		 * 
		 */
		public Node<E> getNext() {
			return this.next; // Returns the link to the next node.
		}

		/**
		 * setNext(), is an update/mutator method that sets the link to the next node
		 * for this node.
		 * 
		 * @param next is the link to the next node
		 * 
		 */
		public void setNext(Node<E> next) {
			this.next = next; // Sets the pointer of this node, to point to the value specified. Acts as a
								// link to the next node.
		}

		/**
		 * getElement(), is an accessor method that returns the value stored in the
		 * node.
		 * 
		 * @return this.element the element stored in the node
		 * 
		 */
		public E getElement() {
			return this.element; // Returns the element held in the node that invokes this method.
		}

		/**
		 * setElement(), is an update/mutator method that sets the element/value stored
		 * in the node to the value specified
		 * 
		 * @param element which is the element/value to be stored in this node
		 * 
		 */
		public void setElement(E element) {
			this.element = element; // Sets the element value held by the node.
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * Main method. Used to run tests on the CardShuffle program, through taking the
	 * user's input.
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in); // Declare scanner object. Used to take in user's input.
		System.out.println("========== Custom Test Cases <<<INTEGER VER.>>> ==========");
		while (true) { // Loops infinitely, or at least till a break statement.
			System.out.println("\n===============================================================================");
			System.out.println("Enter the total number of the cards/elements (size 2n = even number), -1 to exit:"); // Prompt
																														// user
			int num = scan.nextInt(); // Store user's integer input.
			if (num == -1) { // Conditional if statement. If user's input is -1.
				break; // Break statement. Exits loop immediately.
			} else if (num == 0) { // Conditional else if statement. If user's input is 0.
				System.out.println("\nBefore CardShuffle: { }"); // Print out empty deck of cards.
				System.out.println("After CardShuffle:  { }"); // Print out empty deck of cards.
			} else if (num % 2 != 0) { // Conditional else if statement. Checks to ensure that the input is of size 2n.
				System.out.println("Please enter an even number (list size = 2n)."); // Output error message.
			} else { // Else statement. If other conditions are not satisfied.
				System.out.println("\nPlease enter the " + num + " cards:"); // Prompt user to enter the cards.
				SinglyLinkedList<Integer> list = new SinglyLinkedList<Integer>(); // Declare a new SinglyLinkedList.
				Node<Integer> midPoint = null; // Declare a pointer of type Node<Integer> and initialize to null.
				for (int i = 0; i < num; i++) { // For Loop. Loops through for the number of cards/elements specified by
												// the user.
					if (i == num / 2) { // Conditional if statement. Checks to see if we have reached the halfway point
										// of entries.
						midPoint = list.getTail(); // Set pointer midPoint to point to the end of the first half of the
													// SinglyLinkedList, which'll serve as our halfway indicator.
					}
					System.out.print("Entry #" + (i + 1) + ": "); // Prompt user to enter value for card.
					list.add(scan.nextInt()); // Add in user's input for card/element into SinglyLinkedList.
				}
				System.out.println("\nBefore CardShuffle: " + list.toString()); // Output cards/elements order before
																				// shuffle.
				list.CardShuffle(midPoint); // Used object reference to invoke shuffle method.
				System.out.println("After CardShuffle:  " + list.toString()); // Output cards/elements order after
																				// shuffle.
			}
		}
		scan.close(); // Close scanner to avoid/prevent resource leaks.
	}
}
