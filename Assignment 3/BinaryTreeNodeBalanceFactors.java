/**********************************************************
 * EECS2011N: Fundamentals of Data Structures,  Winter 2020
 * Assignment 3, Problem 2: BinaryTreeNodeBalanceFactors.java
 * Student Name: Bryan Li
 * Student EECS account: [removed for privacy reasons]
 * Student ID number: [removed for privacy reasons]
 **********************************************************/
package A3;

import java.lang.Math;

public class BinaryTreeNodeBalanceFactors {

////////////////////////////////////////////////////////////////////////////////////////////////////////
//Nested class, LinkedBinaryTree. Used to construct the tree given an array of values, and calculates balance node factors.
	/**
	 * Nested class, LinkedBinaryTree. Used to construct/build up the BinaryTree and
	 * calculate the balance factor of all of its internal nodes. The
	 * LinkedBinaryTree is completely made up of nodes, each of which carries an
	 * element, a link to its left child and a link to its right child. The Binary
	 * Tree is constructed through receiving an array of 2^n-1 values (n being the
	 * height of the tree), and then using those values to build up the structure of
	 * the tree. The parameter is essentially just an array-form of representing the
	 * Binary Tree. Nodes with a value of null, and its descendants will not be
	 * included in the counting/calculating the balance factor of a node.
	 */
	private static class LinkedBinaryTree<E> {
		private Node<E> root; // Declare node that'll act as the root of the tree.

		/**
		 * Constructor of the LinkedBinaryTree class. The constructor receives a
		 * parameter in the form of an array of values that will make up the structure
		 * of the tree. The parameter is essentially just an array-form of representing
		 * the Binary Tree. The array should have (2^n)-1 elements to make a full tree.
		 * 
		 * @param testTree is an array representation of the values of the Binary Tree
		 */
		public LinkedBinaryTree(E[] testTree) {
			this.root = treeConstruct(testTree, 0); // Sets the root equal to the node object returned from
													// treeConstruct() method.
		}

		/**
		 * treeConstruct(), is a recursive method that is used to construct the Binary
		 * Tree
		 * 
		 * @param treeRun  is an array of values that makes up the Binary Tree
		 * 
		 * @param arrIndex is the index of an element in the array
		 * 
		 * @return a node of the LinkedBinaryTree which is the root node which is
		 *         connected to its left child and right child. Plus this children are
		 *         linked to their own children and so on.
		 */
		private Node<E> treeConstruct(E[] treeRun, int arrIndex) {
			if (arrIndex < treeRun.length && treeRun[arrIndex] != null) { // Conditional if statement. Checks to see if
																			// we are still within the boundaries of the
																			// array, and checks to ensure that the
																			// value stored inside the index is not
																			// equal to null.
				return new Node<E>(treeRun[arrIndex], this.treeConstruct(treeRun, 2 * arrIndex + 1),
						this.treeConstruct(treeRun, 2 * arrIndex + 2)); // Return a new node object. This new node
																		// object has arguments of an element, a left
																		// child, and a right child. Where the the left
																		// child and right child are actually recursive
																		// calls to the method. The items being sent in
																		// the recursive call are the array and the
																		// address of the next index of the array to go
																		// to. Essentially it simulates the shifting
																		// along the array and we slowly build up our
																		// tree through breadth first search patterns of
																		// traversal.
			} else {
				return null; // Return value of null
			}
		}

		/**
		 * printBalanceFactor(), is used to calculate and print out the balance factors
		 * values of all the internal nodes of the Binary Tree. This method is invoked
		 * through the user of an object reference.
		 */
		public void printBalanceFactor() {
			this.printBalanceFactorHelper(this.root); // Invoke recursive helper method.
		}

		/**
		 * printBalanceFactorHelper(), is the recursive helper method to
		 * printBalanceFactor(). Through using recursion we calculate the heights of the
		 * left and tree subtrees of a node, and then we calculate and output the
		 * balance factor. Where balance factors is the absolute value of the result
		 * from subtracting the height of the left subtree from the height of the right
		 * subtree. This method uses Euler's traversal method in shifts from node to
		 * node. The time complexity of this method is O(2^n).
		 * 
		 * @param curNode is a pointer to the current node of the Binary Tree
		 * 
		 * @return the height of a subtree of the current node
		 */
		private int printBalanceFactorHelper(Node<E> curNode) {
			if (curNode != null) { // Conditional if statement. Checks to ensure that the current node is not null.
				int heightLeftTree = this.printBalanceFactorHelper(curNode.getLeft()); // Get the height of the left
																						// subtree of this node by
																						// making recursive calls the
																						// sending the link to the left
																						// child as an argument.
				int heightRightTree = this.printBalanceFactorHelper(curNode.getRight()); // Get the height of the right
																							// subtree of this node by
																							// making recursive calls
																							// the sending the link to
																							// the right child as an
																							// argument.
				if ((heightLeftTree != 0) || (heightRightTree != 0)) { // Conditional if statement. Checks to ensure
																		// that the heights of the left subtree and the
																		// right subtree are not equal to 0.
					int balanceFactor = heightRightTree - heightLeftTree; // Calculates the balance factor of the
																			// current node.
					System.out.println("(" + curNode.getElement() + "," + Math.abs(balanceFactor) + ")");
					// Prints out the current node along with the balance factor of the
					// corresponding node.
				}
				return (heightLeftTree > heightRightTree) ? (heightLeftTree + 1) : (heightRightTree + 1);
				// Returns a value that simulates shifting up the tree. If height of left
				// subtree is greater than right subtree than we return heightLeftTree+1, or
				// else we will return heightRightTree+1. Essentially just returns the height of
				// the subtree/tree.
				
			} else {
				return 0; // Return value of 0.
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////
//Nested class, Node. Sequences of nodes makes up the LinkedBinaryTree.
	/**
	 * Nested class, Node. Used to create Node objects that makes up the
	 * LinkedBinaryTree. Each node carries an element, a link to its left child and
	 * a link to its right child.
	 */
	private static class Node<E> {
		private E element; // Declare a field/instance variable to hold the element of the node object.
		private Node<E> left; // Declare a field/instance variable to hold the link to the left child of this
								// node. A pointer to the left child..
		private Node<E> right; // Declare a field/instance variable to hold the link to the right child of this
								// node. A pointer to the right child.

		/**
		 * Constructor for the nested Node class. This constructor initializes the state
		 * of the Node object by setting the element, link to left child, and link to
		 * right child equal to the values received as parameters.
		 * 
		 * @param element    is value of type E that is held by the node
		 * 
		 * @param leftChild  is a link/pointer to the left child of the node
		 * 
		 * @param rightChild is a link/pointer to the right child of the node
		 */
		public Node(E element, Node<E> leftChild, Node<E> rightChild) { // Constructor for the Node class.
			this.element = element; // Initializes the element of this node equal to the value stored in the
									// parameter element.
			this.left = leftChild; // Initializes the link to the left child equal to the node specified by the
									// parameter leftChild.
			this.right = rightChild; // Initializes the link to the right child equal to the node specified by the
										// parameter rightChild.
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Accessor Methods of the Node class.
		/**
		 * getElement(), is an accessor method that returns the value of the element
		 * held by the node.
		 * 
		 * @return this.element the value of the element stored in this node
		 */
		public E getElement() {
			return this.element; // Returns the value of the element stored in this node.
		}

		/**
		 * getLeft(), is an accessor method that returns the link/pointer to the left
		 * child of the node.
		 * 
		 * @return this.left the link/pointer to the left child of this node
		 */
		public Node<E> getLeft() {
			return this.left; // Returns a link to the left child of this node.
		}

		/**
		 * getRight(), is an accessor method that returns the link/pointer to the right
		 * child of the node.
		 * 
		 * @return this.right the link/pointer to the right child of this node
		 */
		public Node<E> getRight() {
			return this.right; // Returns a link to the right child of this node.
		}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Mutator/update methods of the Node class.		
		/**
		 * setElement(), is a mutator/update method that changes/sets the value of the
		 * element held by the node.
		 * 
		 * @param element is value of type E that is going to be stored in the node
		 *                object
		 */
		public void setElement(E element) {
			this.element = element; // Sets the value of the element stored in this node equal to the value of
									// parameter element.
		}

		/**
		 * setLeft(), is a mutator/update method that sets/changes the link/pointer to
		 * the left child of the node.
		 * 
		 * @param leftChild is a link/pointer to a node that is going to be the left
		 *                  child of this node
		 */
		public void setLeft(Node<E> leftChild) {
			this.left = leftChild; // Sets the link to the left child of this node equal to the pointer leftChild.
		}

		/**
		 * setRight(), is a mutator/update method that sets/changes the link/pointer to
		 * the right child of the node.
		 * 
		 * @param rightChild is a link/pointer to a node that is going to be the right
		 *                   child of this node
		 */
		public void setRight(Node<E> rightChild) {
			this.right = rightChild; // Sets the link to the right child of this node equal to pointer rightChild.
		}

		/**
		 * toString(), is method that returns a string representation of the element
		 * held by the node.
		 * 
		 * @param this.element.toString() which is a string representation of the
		 *                                element of this node
		 */
		@Override
		public String toString() {
			return this.element.toString(); // Returns a string representation of the element of this node.
		}
	}

///////////////////////////////////////////////////////////////////////////////////////////////////	
	/**
	 * Main method. Used to run tests cases in the construction of the BinaryTree
	 * and the calculations of the balance factors of all the internal nodes of the
	 * Binary Tree.
	 */
	public static void main(String[] args) {
		LinkedBinaryTree<String> binaryTree; // Declare LinkedBinaryTree reference, called binaryTree.
		System.out.println("=========== / OUTPUT / ==========="); // Output title
		System.out.println("===== (node, balance factor) =====\n"); // Output formatting

		System.out.println("======== Test #1 ========"); // Output title
		String[] testBF1 = { "a", "b", "c" };
		binaryTree = new LinkedBinaryTree<String>(testBF1); // Construct binary tree using the array of values.
		binaryTree.printBalanceFactor(); // Invoke balance factor method using the object reference to print out all the
											// balance factors of the internal nodes.
		System.out.println("=========================\n");

		System.out.println("======== Test #2 ========"); // Output title
		String[] testBF2 = { "a", "b", "c", null, null, "d", "e" };
		binaryTree = new LinkedBinaryTree<String>(testBF2); // Construct binary tree using the array of values.
		binaryTree.printBalanceFactor(); // Invoke balance factor method using the object reference to print out all the
											// balance factors of the internal nodes.
		System.out.println("=========================\n");

		System.out.println("======== Test #3 ========"); // Output title
		String[] testBF3 = { "a", "b", "c", "d", "e", "f", null, "g", null, "h", "i", "j", null, null, null };
		binaryTree = new LinkedBinaryTree<String>(testBF3); // Construct binary tree using the array of values.
		binaryTree.printBalanceFactor(); // Invoke balance factor method using the object reference to print out all the
											// balance factors of the internal nodes.
		System.out.println("=========================\n");

		System.out.println("======== Test #4 ========"); // Output title
		String[] testBF4 = { "a", "b", null, "c", "d", null, null, "e", null, null, "f", null, null, null, null, "g",
				null, null, null, null, null, "h", null, null, null, null, null, null, null, null, null, };
		binaryTree = new LinkedBinaryTree<String>(testBF4); // Construct binary tree using the array of values.
		binaryTree.printBalanceFactor(); // Invoke balance factor method using the object reference to print out all the
											// balance factors of the internal nodes.
		System.out.println("=========================\n");

		System.out.println("======== Test #5 ========"); // Output title
		String[] testBF5 = { "a", "b", null, "c", "d", null, null, "e", "f", "g", "h", null, null, null, null, "i", "j",
				null, null, null, null, "k", null, null, null, null, null, null, null, null, null, };
		binaryTree = new LinkedBinaryTree<String>(testBF5); // Construct binary tree using the array of values.
		binaryTree.printBalanceFactor(); // Invoke balance factor method using the object reference to print out all the
											// balance factors of the internal nodes.
		System.out.println("=========================\n");
	}
}
