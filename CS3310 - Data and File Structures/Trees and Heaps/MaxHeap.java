package pack;

import java.io.PrintWriter;

/** This class is an explicit max heap.
 * 
 * @author David Moussalli
 * 
 * See References at the top of class Main.
 */
public class MaxHeap {
	Node root, minItem;	//Root node of the heap
	private long startTime, stopTime, 
		 avgInsertTime, maxSearchTime, minSearchTime;
	private int counter;
	PrintWriter outputStream;
	
	/** Constructor for class MaxHeap.
	 *  Initializes variables, loads the maxHeap, heapifies it, and sets heights for all nodes.
	 * 
	 * @param array - array of strings to insert into the heap.
	 */
	public MaxHeap(String[] array, PrintWriter outputStream) {
		root = null;
		counter = 0;
		avgInsertTime = 0;
		maxSearchTime = 0;
		minSearchTime = 0;
		this.outputStream = outputStream;
		
		for(int i=0; i<array.length; i++) {
			startTime = System.nanoTime();
			insert(array[i], root);	//Insert the name into the heap
			stopTime = System.nanoTime();
			avgInsertTime += (stopTime-startTime);
			counter++;
		}
		avgInsertTime /= counter; //Calculate average time to insert values

		minItem = root;
		//Set root height based on the heights of the left and right children.
		root.setHeight( 1 + ((root.lChild.getHeight()>root.rChild.getHeight())? root.lChild.getHeight(): root.rChild.getHeight()) );
	}
	
	/** This recursive insert method inserts names into the heap and keeps it height balance. - See Reference 1
	 * 	
	 * @param name - name to insert
	 * @param currentNode - current node
	 */
	public void insert(String name, Node currentNode) {
		if(currentNode == null) { //If root node is null, create a new Node and set it as root.
			currentNode = new Node(name, null);
			root = currentNode;
		}
		else {
			if(currentNode.lChild == null) { //If lChild is null, insert and heapify.
				currentNode.lChild = new Node(name, currentNode);
				heapify(currentNode.lChild);
				return; //Return after inserting
			}
			else if(currentNode.rChild == null) { //If rChild is null, insert and heapify.
				currentNode.rChild = new Node(name, currentNode);
				heapify(currentNode.rChild);
				return; //Return after inserting
			}
			else {//Otherwise, search children recursively, keeping track of each subtree's height.
				int leftH = currentNode.lChild.getHeight();
				int rightH = currentNode.rChild.getHeight();
				
				if(leftH <= rightH) { //If lChild's height is <= rChild's height, insert at lChild...increment height
					currentNode.lChild.setHeight(leftH+1);
					insert(name, currentNode.lChild);
				}
				else { //Otherwise, insert at rChild...increment height
					currentNode.rChild.setHeight(rightH+1);
					insert(name, currentNode.rChild);
				}
			}
		}
	}//End of insert
	
	/** Heapifies the heap when a string is inserted. - See Reference 1
	 * 
	 * @param currentNode - current node
	 */
	public void heapify(Node currentNode) {
		Node parent;
		if(currentNode != null) {
			parent = currentNode.parent;
			if(parent != null && parent.getName().compareTo(currentNode.getName()) < 0) { //If parent value < temp value, swap the two elements.
				String tempname = parent.getName();
				parent.setName(currentNode.getName());
				currentNode.setName(tempname);
				heapify(parent);
			}
		}
	}
	
	/** Recursively prints the heap in pre-order traversal. - See Reference 2
	 * 
	 * @param currentNode - current node
	 */
	public void printPostorder(Node currentNode) {
		if(currentNode != null) {
			printPostorder(currentNode.lChild); //Print left subtree
			printPostorder(currentNode.rChild); //Print right subtree
			System.out.print(currentNode.getName() + ", ");
			outputStream.print(currentNode.getName() + ", ");
		}
	}
	
	/** Search for a given name in the heap and print out information about it's location in the heap.
	 * 
	 * @param name - name being searched for
	 * @param currentNode - current node
	 * @return returns true or false, based on whether the name was found, so it will only report the
	 * 		first if there are duplicates, and if it is not found at all it will tell the user.
	 */
	public boolean searchForName(String name, Node currentNode) {
		if(currentNode == null) { //Checks if current node is null
			return false;
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
			return true;
		}
		else {
			if(searchForName(name, currentNode.lChild) == false) { //If name was NOT found in left subtree,
				return searchForName(name, currentNode.rChild);    //	search the right subtree
			}
		}
		return true;
	}//End of searchForName
	
	/** Finds the depth of the given index. - See Reference 3
	 * 
	 * @param currentNode - current node
	 * @return returns the depth of the provided node
	 */
	public int findDepth(Node currentNode) {
		if(currentNode == root) { //If currentNode == null
			return 0;
		}
		else {
			return 1+findDepth(currentNode.parent);//Math.max(findDepth(currentNode.lChild), findDepth(currentNode.rChild));
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
	
	/** GET method which returns the string with the lowest value in the heap.  - See Reference 1
	 * 
	 * @param currentNode - current node
	 * @return returns the string with the lowest value in the heap.
	 */
	public String getMinimum(Node currentNode) {
		startTime = System.nanoTime();
		if(currentNode != null) {
			if(currentNode.getName().compareTo(minItem.getName()) < 0) {
				minItem = currentNode;
			}
			getMinimum(currentNode.lChild);			
			getMinimum(currentNode.rChild);
		}
		stopTime = System.nanoTime();
		minSearchTime = (stopTime-startTime); //Calculate time it took to find min item
		return minItem.getName();
	}//End of getMinimum
	
	/**GET method which returns the string with the highest value in the heap.  - See Reference 1
	 * 
	 * @return returns the string with the lowest value in the heap.
	 */
	public String getMaximum() {
		startTime = System.nanoTime();
		if(root == null) {
		}
		stopTime = System.nanoTime();
		maxSearchTime = (stopTime-startTime); //Calculate time it took to find min item
		return this.root.getName();
	}//End of getMaximum
	
	/** GET method which returns the height of the node (value stored in the Node object).
	 * 
	 * @param node - node to get the height of.
	 * @return returns the height of the node
	 */
	public int getHeight(Node node) {
		return node.getHeight(); //Call to the getHeight() method in class Node.
	}
	
	/**
	 * @return Returns the time it took to find the minimum value
	 */
	public long getMinSearchTime() {
		return this.minSearchTime;
	}

	/**
	 * @return Returns the time it took to find the maximum value
	 */
	public long getMaxSearchTime() {
		return this.maxSearchTime;
	}
	
	/**
	 * @return Returns the average time it took to insert into the maxHeap
	 */
	public long getAvgInsertTime() {
		return this.avgInsertTime;
	}
}//End of class MaxHeap
