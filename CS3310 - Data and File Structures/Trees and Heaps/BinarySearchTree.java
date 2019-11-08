package pack;

import java.io.PrintWriter;

/** This class is an explicit Binary Search Tree.
 * 
 * @author David Moussalli
 * 
 * See References at the top of class Main.
 */
public class BinarySearchTree {
	Node root, minItem;	//Root node of the BST
	private long startTime, stopTime, 
		 avgInsertTime, maxSearchTime, minSearchTime;
	private int counter;
	PrintWriter outputStream;
	
	/** Constructor for class BinarySearchTree.
	 * 
	 * @param array - array of strings to insert into the BST.
	 */
	public BinarySearchTree(String[] array, PrintWriter outputStream) {
		root = null;
		counter = 0;
		avgInsertTime = 0;
		maxSearchTime = 0;
		minSearchTime = 0;
		this.outputStream = outputStream;
		
		root = insert(array[0], root); //Create root.
		for(int i=1; i<array.length; i++) { //Insert all values in the array into the BST.
			startTime = System.nanoTime();
			insert(array[i], root); //Insert name
			stopTime = System.nanoTime();
			avgInsertTime += (stopTime-startTime);
			counter++;
		}
		avgInsertTime /= counter; //Calculate average time to insert values into the BST

		minItem = root;
		
	}
	
	/** This recursive insert method inserts names into the BST. - See Reference 4
	 * 	
	 * @param name - name to insert
	 * @param currentNode - current node
	 */
	public Node insert(String name, Node currentNode) {
		if(currentNode == null) { //If root node is null, create a new Node and return it.
			currentNode = new Node(name, null);
			return currentNode;
		}
		else { 
			if(currentNode.getName().compareTo(name) > 0) { //If name is less than current node's name,
				currentNode.lChild = insert(name, currentNode.lChild); // insert recursively at left child.
				currentNode.lChild.parent = currentNode; //Set parent value
			}
			else if(currentNode.getName().compareTo(name) < 0) { //If name is greater than current node's name,
				currentNode.rChild = insert(name, currentNode.rChild); // insert recursively at right child.
				currentNode.rChild.parent = currentNode; //Set parent value
			}
			return currentNode; //Return the currentNode.
		}//End else
	}//End of insert
	
	/** Recursively prints the BST in in-order traversal. - See Reference 4
	 * 
	 * @param currentNode - current node
	 */
	public void printInorder(Node currentNode) {
		if(currentNode != null) {
			printInorder(currentNode.lChild); //Print left subtree
			System.out.print(currentNode.getName() + ", ");
			outputStream.print(currentNode.getName() + ", ");
			printInorder(currentNode.rChild); //Print right subtree
		}
	}
	
	/** Search for a given name in the BST and print out information about it's location.
	 * 
	 * @param name - name being searched for
	 * @param currentNode - current node
	 * @return returns true or false, based on whether the name was found, so it will only report the
	 * 		first if there are duplicates, and if it is not found at all it will tell the user.
	 */
	public Node searchForName(String name, Node currentNode) {
		if(currentNode == null) { //Checks if current node is null
			return currentNode;
		}
		if(currentNode.getName().compareTo(name) == 0) { //If name was found, output information
			System.out.println("\t\tFound.");
			System.out.println("\t\tDepth: " + findDepth(currentNode) + ".");
			outputStream.println("\t\tFound.");
			outputStream.println("\t\tDepth: " + findDepth(currentNode) + ".");
			if(currentNode.lChild == null && (currentNode.rChild) == null) {//If element is a leaf node.
				System.out.println("\t\tLeaf node.");
				outputStream.println("\t\tLeaf node.");
			}
			System.out.println("\t\tSubtree size: " + findSubtreeSize(currentNode) + ".");
			outputStream.println("\t\tSubtree size: " + findSubtreeSize(currentNode) + ".");
			return currentNode;
		}
		if(currentNode.getName().compareTo(name) > 0) {
			return searchForName(name, currentNode.lChild);
		}
		return searchForName(name, currentNode.rChild);
	}//End of searchForName
	
	/** Finds the depth of the given index. - See Reference 3
	 * 
	 * @param currentNode - current node
	 * @return returns the depth of the provided node
	 */
	public int findDepth(Node currentNode) {
		if(currentNode.parent == null) { //If currentNode == null
			return 0;
		}
		else {
			if(currentNode.parent != null) {
				return 1+findDepth(currentNode.parent);//Math.max(findDepth(currentNode.lChild), findDepth(currentNode.rChild));
			}
			return 0;
		}
	}//End of findDepth
	
	/** Finds the subtree size of the given node.
	 * 
	 * @param currentNode - current node
	 * @return returns the size of the subtree
	 */
	public int findSubtreeSize(Node currentNode) {
		if(currentNode == null) { //If currentNode == null
			return 0;
		}
		return 1 + findSubtreeSize(currentNode.lChild) + findSubtreeSize(currentNode.rChild);
	}//End of findSubtreeSize
	
	/** Returns the left-most node in the BST
	 * 
	 * @return returns the smallest value in the BST
	 */
	public String getMinimum() {
		Node temp = root;
		if(temp == null) { //If root is null, return null
			return null;
		}
		while(temp.lChild != null) { //Get smallest value (left-most node)
			temp = temp.lChild;
		}
		return temp.getName();
	}

	/** Returns the right-most node in the BST
	 * 
	 * @return returns the largest value in the BST
	 */
	public String getMaximum() {
		Node temp = root;
		if(temp == null) { //If root is null, return null
			return null;
		}
		while(temp.rChild != null) { //Get largest value (right-most node)
			temp = temp.rChild;
		}
		return temp.getName();
	}
	
	/**
	 * @return returns the average time it took to insert into the BST
	 */
	public long getAvgInsertTime() {
		return this.avgInsertTime;
	}

	/**
	 * @return returns the time it took to find the largest value in the BST.
	 */
	public long getMaxSearchTime() {
		return this.maxSearchTime;
	}

	/**
	 * @return returns the time it took to find the smallest value in the BST.
	 */
	public long getMinSearchTime() {
		return this.minSearchTime;
	}
	
	
}//End of BinarySearchTree
