/**********************************************************
 * EECS2011N: Fundamentals of Data Structures,  Winter 2020
 * Assignment 3, Problem 3: PriorityTreeSearch.java
 * Student Name: Bryan Li
 * Student EECS account: [removed for privacy reasons]
 * Student ID number: [removed for privacy reasons]
 **********************************************************/
package A3;

public class PrioritySearchTree {

////////////////////////////////////
// Fields/instance variables. Defines the state of the PrioritySearchTree object/instance.	
	private Point[] binaryTreeNodes; // Declare array of point objects, used to hold the nodes of the binary tree.
	private int externNum; // Declare variable used to hold the number of external nodes (ie. the points).

	/**
	 * Constructor of the PrioritySearchTree class. Receives an array of points
	 * which will serve as the external nodes of the tree. Theses points will be
	 * added onto the array containing all the nodes of the tree. More specifically
	 * they will be added to the end of the array, as the nodes are assigned through
	 * breath first search order of traversal.
	 * 
	 * @param externalNodes an array of points that will serve as the external nodes
	 *                      of the tree. Should have a length of 2^n (which are the
	 *                      only appropriate number of external nodes for a binary
	 *                      tree)
	 */
	public PrioritySearchTree(Point[] externalNodes) { // Constructor. Initializes the state of the object.
		this.binaryTreeNodes = new Point[externalNodes.length * 2 - 1]; // Create an array of Point objects of a length
																		// equal to externalNodes.length*2 - 1 which is
																		// the appropriate amount of nodes/points
																		// required to fill out the tree based on the
																		// number of points given
		this.externNum = externalNodes.length; // Sets the externNum equal to the number of external nodes (ie. the
												// points)
		for (int i = 0; i < externalNodes.length; i++) { // For Loop. Iterates through the array.
			this.binaryTreeNodes[i + externalNodes.length - 1] = externalNodes[i]; // Adds the points given, to the end
																					// of the array holding all the
																					// nodes of the binary tree
		}
	}

	/**
	 * prioritySearch() method sorts the values of the tree in the way that the
	 * problem specifies. Where S is a set of n points in the plane with distinct
	 * integer x- and y-coordinates. T is a complete binary tree storing the points
	 * from S at its external nodes, such that the points are ordered left to right
	 * by increasing x-coordinates. For each node v in T, let S(v) denote the subset
	 * of S consisting of points stored in the subtree rooted at v. For the root r
	 * of T, define top(r) to be the point in S = S(r) with maximal y-coordinate.
	 * For every other node v, define top(r) to be the point in S with highest
	 * y-coordinate in S(v) that is not also the highest y-coordinate in S(u), where
	 * u is any proper ancestor of v in T (if such a point exists). Such
	 * labeling turns T into a priority search tree. This method has a time
	 * complexity of O(n).
	 */
	public void prioritySearch() {
		int i = this.externNum - 2; // Finds the largest index number for an internal node of the tree.
		while (i >= 0) { // While Loop. Loops through every index which is an internal node.
			sort(i); // Call sort method. Has an argument of i which is the index number of the
						// current node
			i--; // Decrements counter.
		}
	}

	/**
	 * sort() is a recursive method which forms the PrioritySearchTree. It does so
	 * through retrieving the values of the left and right children of nodes, and
	 * comparing the Y values of the points in question. Then it sets the value of
	 * that node equal to the one which is greater. Starts from the bottom-right
	 * internal node and goes in reverse breadth first search order of traversal.
	 * Continues till we finally reach the root of the tree. This method has a time
	 * complexity of O(log(n)).
	 * 
	 * @param curNode is the index of the current node of the tree
	 */
	private void sort(int curNode) {
		int leftChild = Point.getLeftChild(curNode); // Declare variable for the index of the left child of this node
		int rightChild = Point.getRightChild(curNode); // Declare variable for the index of the right child of this node
		int maxResult = Point.compare(this.binaryTreeNodes[leftChild], this.binaryTreeNodes[rightChild]);
		// Determine which of the two nodes holds the greater Y value

		if (maxResult == 1) { // Conditional statement. If the left child has the greater Y value.
			this.binaryTreeNodes[curNode] = new Point(this.binaryTreeNodes[leftChild]); // Sets the node equal of the
																						// tree equal to the left child.
			if (this.binaryTreeNodes[leftChild].isExternal()) { // Checks to see if node is an external node.
				this.binaryTreeNodes[leftChild].setUsed(true); // Marks it as used. We can't use the same value twice
																// for this problem.
			} else {
				this.binaryTreeNodes[leftChild] = null; // Sets the value of this node equal to null.
				sort(leftChild); // Recursive call to the sort() method with the index of the right child as the
									// argument.
			}
		} else if (maxResult == -1) { // Conditional if statement. If the right child has the greater Y value.
			this.binaryTreeNodes[curNode] = new Point(this.binaryTreeNodes[rightChild]); // Sets the node equal of the
																							// tree equal to the right
																							// child.
			if (this.binaryTreeNodes[rightChild].isExternal()) { // Checks to see if node is an external node.
				this.binaryTreeNodes[rightChild].setUsed(true); // Marks it as used. We can't use the same value twice
																// for this problem.
			} else {
				this.binaryTreeNodes[rightChild] = null; // Sets the value of this node equal to null
				sort(rightChild); // Recursive call to the sort() method with the index of the right child as the
									// argument.
			}
		}
	}

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Nested class. Point class.
	/**
	 * Nested class. Point class. is used to create the points that are used as the
	 * external nodes of the tree. Then it also serves as the value of the internal
	 * nodes of the PrioritySearchTree. We compare the Y value of these points in
	 * order to determine where to place the points in the internal nodes of the
	 * PrioritySearchTree.
	 */
	private static class Point {
		private String pointRef; // String variable used to hold the name of the point.
		private boolean pointUsed, externNode; // Boolean variables used to mark whether a the points has been used or
												// not, and whether the node is an external node.
		private int x, y; // Integer variables used to hold the x and y values of the point.

		/**
		 * Constructor of the point class. Initializes the state of the point object
		 * with the values given. This constructor is mainly for the external nodes of
		 * the tree.
		 * 
		 * @param ref which is the name of the point
		 * 
		 * @param x   which is the x value of the point
		 * 
		 * @param y   which is the y value of the point
		 */
		public Point(String ref, int x, int y) { // Constructor of the Point class.
			this.externNode = true; // Initializes externNode equal to true. Marking it as a external, because all
									// the points are suppose to be used as the external nodes of the tree.
			this.pointUsed = false; // Initializes pointUsed equal to false. Marking it as false means that it has
									// not been used anywhere else in the PrioritySearchTree in aside from the
									// external node.
			this.pointRef = ref; // Initializes the name of the name of this point equal to ref.
			this.x = x; // Initializes the x value of this node equal to the x value given.
			this.y = y; // Initializes the y value of this node equal to the y value given.
		}

		/**
		 * Copy constructor of the point class. Initializes the state of the point
		 * object by copying the state of another point object.
		 * 
		 * @param p which is another Point object which we copy the state of
		 */
		public Point(Point p) {
			this.pointRef = p.getName(); // Initializes the name of the point equal to the name of the point object
											// given.
			this.externNode = false; // Initializes externNode equal to false, meaning that it isn't an external
										// node.
			this.pointUsed = false; // Initializes pointUsed equal to Marking it as false means that it has not been
									// used anywhere else in the PrioritySearchTree in aside from the
			this.x = p.getX(); // Initializes the x value of this node equal to the x value of the point object
								// given.
			this.y = p.getY(); // Initializes the y value of this node equal to the y value of the point object
								// given.
		}

		/**
		 * getName() is an accessor method that returns the name of the point.
		 * 
		 * @return this.pointRef which is the name of the point
		 */
		public String getName() {
			return this.pointRef; // Returns the name of the point.
		}

		/**
		 * isUsed() is an accessor method that returns a boolean value referring to
		 * whether or not the point has been used anywhere in the binary tree aside from
		 * the external nodes.
		 * 
		 * @return this.pointUsed which is the boolean indicator
		 */
		public boolean isUsed() {
			return this.pointUsed; // Returns a boolean value referring to whether or not the point has been used
									// anywhere in the binary tree aside from the external nodes.
		}

		/**
		 * isExternal() is an accessor method that returns a boolean value referring to
		 * whether or not the node is an external node.
		 * 
		 * @return this.externNode which is the boolean indicator
		 */
		public boolean isExternal() {
			return this.externNode; // Returns a boolean value referring to whether or not the node is an external
									// node.
		}

		/**
		 * getX() is an accessor method that returns the x value of this point
		 * 
		 * @return this.x which is the x value
		 */
		public int getX() {
			return this.x; // Return the x value of this point
		}

		/**
		 * getY() is an accessor method that returns the y value of this point
		 * 
		 * @return this.y which is the y value
		 */
		public int getY() {
			return this.y; // Return the y value of this point
		}

		/**
		 * getLeftChild() is a method that returns the index of the left child of this
		 * node
		 * 
		 * @param index which is the index of the current node
		 * 
		 * @return the index of the left child
		 */
		public static int getLeftChild(int index) {
			return 2 * index + 1; // Return the index of the left child of the index provided
		}

		/**
		 * getRightChild() is a method that returns the index of the right child of this
		 * node
		 * 
		 * @param index which is the index of the current node
		 * 
		 * @return the index of the right child
		 */
		public static int getRightChild(int index) {
			return 2 * index + 2; // Return the index of the right child of the index provided
		}

		/**
		 * setName() is a mutator/update method that sets/changes the name of the
		 * point/node equal to the name specified.
		 * 
		 * @param ref the name to be set to
		 */
		public void setName(String ref) {
			this.pointRef = ref; // Set the name of the point equal to the value provided
		}

		/**
		 * setUsed() is a mutator/update method that sets/changes the state of the
		 * pointUsed.
		 * 
		 * @param used is the boolean value indicating whether this has been used yet
		 */
		public void setUsed(boolean used) {
			this.pointUsed = used; // Set the state of pointUsed equal to the boolean value provided
		}

		/**
		 * setExternal() is a mutator/update method that sets/changes the state of the
		 * externNode.
		 * 
		 * @param external is the boolean value indicating whether this is an external
		 *                 node.
		 */
		public void setExternal(boolean external) {
			this.externNode = external; // Set the state of externNode equal to the boolean value provided
		}

		/**
		 * setX() is a mutator/update method that sets/changes the x value.
		 * 
		 * @param x is the numerical value of x to be changed to
		 */
		public void setX(int x) {
			this.x = x; // Set the x value of the point equal to the value provided
		}

		/**
		 * setY() is a mutator/update method that sets/changes the y value.
		 * 
		 * @param y is the numerical value of y to be changed to
		 */
		public void setY(int y) {
			this.y = y; // Set the y value of the point equal to the value provided
		}

		/**
		 * compare() is a method that compares the Y value of the left and right child.
		 * Then returns a value depending on which one is greater.
		 * 
		 * @param l the left child of the node
		 * 
		 * @param r the right child of the node
		 * 
		 * @return a value indicating whether the left or right child has the greater Y
		 *         value
		 */
		public static int compare(Point l, Point r) {
			boolean isL = false, isR = false; // Declare two boolean variables, one for left child and one for right
												// child.
			Integer lYCoord = (l == null || l.isUsed()) ? null : l.getY(); // Check to see if left child has a value of
																			// null or has been used before.
			Integer rYCoord = (r == null || r.isUsed()) ? null : r.getY(); // Check to see if right child has a value of
																			// null or has been used before.
			if (lYCoord != null && rYCoord != null) { // Conditional if statement checks if the left child's Y value is
														// not equal to null, and checks to see if the right child's Y
														// value is not equal to null.
				if (lYCoord > rYCoord) { // If the value of the left child's Y coordinate is greater than the value of
											// the right child's Y coordinate.
					isL = true; // Set boolean variable for left child equal to true. Indicates left Y is
								// greater than right Y.
				} else { // If the value of the right child's Y coordinate is greater than, or equal to
							// the value of the left child's Y coordinate.
					isR = true; // Set boolean variable for right child equal to true. Indicates right Y is
								// greater or equal than left Y.
				}
			} else { // If the previous condition was not met.
				if (lYCoord != null && rYCoord == null) { // If the left child's Y coordinate is not equal to null, and
															// the right child's Y coordinate is equal to null.
					isL = true; // Set boolean variable for left child equal to true. Indicates left Y is
								// greater than right Y.
				} else if (lYCoord == null && rYCoord != null) {// If the left child's Y coordinate is equal to null,
																// and
					// the right child's Y coordinate is not equal to null.
					isR = true; // Set boolean variable for right child equal to true. Indicates right Y is
								// greater or equal than left Y.
				}
			}
			if (isL) { // If left child's Y coordinate is greater than left's
				return 1; // Return a value of -1
			} else if (isR) { // If right child's Y coordinate is greater than or equal to left's
				return -1; // Return a value of 1
			}
			return 0; // Return a value of 0
		}

		/**
		 * toString() is a method that returns a string representation of the
		 * point/node.
		 * 
		 * @param a string representation of the point/node
		 */
		@Override
		public String toString() {
			if (this.externNode) { // If the node is an external node.
				return String.format("[" + this.pointRef + "=(" + this.x + "," + this.y + ")]"); // Return a string
																									// format of the
																									// point's name, x,
																									// y values.
			}
			return String.format("   [" + this.pointRef + "]"); // Return a string format with only the name of the
																// point. Used for internal nodes of the tree.
		}
	}

	/**
	 * printTree() is a method that uses string builder to print out a string
	 * representation of our PrioritySearchTree.
	 */
	public void printTree() {
		StringBuilder sb = new StringBuilder(); // Create a new string builder object. Used to create string output.
												// Which in this case is our tree.
		boolean nextLine = false; // Declare boolean variable and set equal to false.
		int spacing = (this.binaryTreeNodes.length + 1) / 2; // Declare a variable used for determining the amount of
																// spaces between things in the string.
		sb.append(String.format("%" + (12 * (spacing - 1)) + "s", "")); // Create an indentation in the string.
		int curHeight = 2, i = 0; // Create a variable indicating the number of nodes at the current height of the
		// tree. Also create a counter i.
		while (i < this.binaryTreeNodes.length) { // Loop. Loops for the length of the binaryTreeNodes array.
			if (nextLine) { // If we need to shift down to the next level of the tree.
				nextLine = false; // Set equal to false.
				curHeight *= 2; // Doubles the number of nodes at the current height of the tree.
				spacing /= 2; // Divides the spacing in half.
				sb.append("\n\n" + String.format("%" + (12 * (spacing - 1) + 1) + "s", "")); // Add spacing between
																								// nodes.
			}
			if (this.binaryTreeNodes[i] != null) { // If the value in the array is not equal to null
				sb.append(String.format("%-12s", this.binaryTreeNodes[i].toString())); // Add node onto the tree.
			} else {
				sb.append("[null]"); // Add a node with value null onto the tree
			}
			if ((curHeight - 2) == i) { // If curHeight minus two is equal to i.
				nextLine = true; // Set equal to true. Means we should be shifting to the next level of the tree.
			} else {
				sb.append(String.format("%" + (12 * (spacing * 2 - 1)) + "s", "")); // Add spacing between nodes.
			}
			i++; // Increment counter.
		}
		System.out.printf(sb.toString() + "\n"); // Print out the entire tree.
	}

	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Main method. Used to run tests of our PrioritySearchTree.	
	public static void main(String[] args) {
		PrioritySearchTree T; // Declare a PrioritySearchTree called T.
		System.out.println(
				"==============================================================================================");
		System.out.println("========== PrioritySearchTree 1 ==========\n"); // Output title
		Point[] pointSet1 = { new Point("p1", 7, 1), new Point("p2", 8, 2) };
		T = new PrioritySearchTree(pointSet1); // Create new PrioritySearchTree with array of points as argument.
		T.prioritySearch(); // Invoke prioritySearch() method with object reference T
		T.printTree(); // Invoke printTree() method with object reference T

		System.out.println(
				"\n\n============================================================================================");
		System.out.println("========== PrioritySearchTree 2 ==========\n"); // Output title
		Point[] pointSet2 = { new Point("p1", 1, 19), new Point("p2", 2, 100), new Point("p3", 3, 8),
				new Point("p4", 4, 28) };
		T = new PrioritySearchTree(pointSet2); // Create new PrioritySearchTree with array of points as argument.
		T.prioritySearch(); // Invoke prioritySearch() method with object reference T
		T.printTree(); // Invoke printTree() method with object reference T

		System.out.println(
				"\n\n============================================================================================");
		System.out.println("========== PrioritySearchTree 3 =========\n"); // Output title
		Point[] pointSet3 = { new Point("p1", 1, 18), new Point("p2", 2, 72), new Point("p3", 3, 16),
				new Point("p4", 4, 25), new Point("p5", 5, 4), new Point("p6", 6, 36), new Point("p7", 7, 28),
				new Point("p8", 8, 11) };
		T = new PrioritySearchTree(pointSet3); // Create new PrioritySearchTree with array of points as argument.
		T.prioritySearch(); // Invoke prioritySearch() method with object reference T
		T.printTree(); // Invoke printTree() method with object reference T

		System.out.println(
				"\n\n============================================================================================");
		System.out.println("========== PrioritySearchTree 4 ==========\n"); // Output title
		Point[] pointSet4 = { new Point("p1", -8, 6), new Point("p2", -7, 2), new Point("p3", -2, 3),
				new Point("p4", 1, 8), new Point("p5", 3, 8), new Point("p6", 7, 19), new Point("p7", 8, 1),
				new Point("p8", 10, 7) };
		T = new PrioritySearchTree(pointSet4); // Create new PrioritySearchTree with array of points as argument.
		T.prioritySearch(); // Invoke prioritySearch() method with object reference T
		T.printTree(); // Invoke printTree() method with object reference T

		System.out.println(
				"\n\n============================================================================================");
		System.out.println("========== PrioritySearchTree 5 ==========\n"); // Output title
		Point[] pointSet5 = { new Point("p1", 1, 3), new Point("p2", 2, 6), new Point("p3", 3, 9),
				new Point("p4", 4, 12), new Point("p5", 5, 15), new Point("p6", 6, 18), new Point("p7", 7, 21),
				new Point("p8", 8, 24) };
		T = new PrioritySearchTree(pointSet5); // Create new PrioritySearchTree with array of points as argument.
		T.prioritySearch(); // Invoke prioritySearch() method with object reference T
		T.printTree(); // Invoke printTree() method with object reference T
		
		System.out.println(
				"\n\n============================================================================================");
		System.out.println("========== PrioritySearchTree 6 ==========\n"); // Output title
		Point[] pointSet6 = { new Point("p1", -3, 3), new Point("p2", 2, -2), new Point("p3", 3, 5),
				new Point("p4", 4, 12)};
		T = new PrioritySearchTree(pointSet6); // Create new PrioritySearchTree with array of points as argument.
		T.prioritySearch(); // Invoke prioritySearch() method with object reference T
		T.printTree(); // Invoke printTree() method with object reference T
		
		System.out.println(
				"\n\n============================================================================================");
		System.out.println("========== PrioritySearchTree 7 ==========\n"); // Output title
		Point[] pointSet7 = { new Point("p1", 9, -3), new Point("p2", 12, -6), new Point("p3", 14, 9),
				new Point("p4", 16, 8)};
		T = new PrioritySearchTree(pointSet7); // Create new PrioritySearchTree with array of points as argument.
		T.prioritySearch(); // Invoke prioritySearch() method with object reference T
		T.printTree(); // Invoke printTree() method with object reference T
		
		System.out.println(
				"\n\n============================================================================================");
		System.out.println("========== PrioritySearchTree 8 ==========\n"); // Output title
		Point[] pointSet8 = { new Point("p1", -1, -15), new Point("p2", 6, 0), new Point("p3", 8, -8),
				new Point("p4", 16, -16)};
		T = new PrioritySearchTree(pointSet8); // Create new PrioritySearchTree with array of points as argument.
		T.prioritySearch(); // Invoke prioritySearch() method with object reference T
		T.printTree(); // Invoke printTree() method with object reference T
	}
}
