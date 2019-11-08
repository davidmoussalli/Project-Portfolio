package pack;
import java.io.PrintWriter;

/** This class is a generic implementation of an AVL tree. - See References in class main.
 * 
 * @author David Moussalli
 *
 * @param <T>
 "*/
public class AVLTree<T extends Comparable<T>> {
	AVLNode<T> root;
	private int nodeCount = 0;	// Counts the number of AVLNodes inside the AVL tree.
	

	/** Returns the height of the ALV tree. - See Reference 2
	 * 
	 * @return
	 */
	public int getHeight() {
		if (root == null) {
			return 0;
		}
		return root.height;
	}

	/** Returns the number of nodes in the tree. - See Reference 2
	 * 
	 * @return
	 */
	public int getSize() {
		return nodeCount;
	}

	/** Returns whether or not the tree is empty. - See Reference 2
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return getSize() == 0;
	}

	/** Return true or false depending on whether a value exists in the tree. - See Reference 2
	 * 
	 * @param value
	 * @return
	 */
	public boolean contains(T value) {
		return contains(root, value);
	}

	/** Recursive contains helper method. - See Reference 2
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	private boolean contains(AVLNode<T> node, T value) {
		if (node == null) {
			return false;
		}

		int cmp = value.compareTo(node.key);

		if (cmp < 0) {	// Search left subtree.
			return contains(node.lChild, value);
		}

		if (cmp > 0) {	// Search right subtree.
			return contains(node.rChild, value);
		}
		return true;	// Found value in tree.
	}

	/** Insert a value to the AVL tree. - See Reference 2
	 * 
	 * @param value
	 * @return
	 */
	public boolean insert(T value) {
		if (value == null) {
			return false;
		}
		root = insert(root, value);
		nodeCount++;
		return true;
	}

	/** Inserts a value inside the AVL tree. - See Reference 2
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	private AVLNode<T> insert(AVLNode<T> node, T value) {
		if (node == null) {
			return new AVLNode<T>(value);
		}
		int cmp = value.compareTo(node.key);	// Compare current value to the value in the AVLNode.
		
		if (cmp < 0) {
			node.lChild = insert(node.lChild, value);	// Insert node in left subtree.
		} 
		else {
			node.rChild = insert(node.rChild, value);	// Insert node in right subtree.
		}
		update(node);	// Update balance factor and height values.
		return balance(node);	// Re-balance the tree and return the root.
	}

	/** Update the provided node's height and balance factor. - See Reference 2
	 * 
	 * @param node
	 */
	private void update(AVLNode<T> node) {
		int leftChildHeight = (node.lChild == null) ? -1 : node.lChild.height;
		int rightChildHeight = (node.rChild == null) ? -1 : node.rChild.height;

		node.height = 1 + Math.max(leftChildHeight, rightChildHeight);	// Update the height.
		node.balanceFactor = rightChildHeight - leftChildHeight;	// Update the balance factor.
	}

	/**Re-balance a node if its balance factor is +2 or -2. - See Reference 2
	 * 
	 * @param node
	 * @return
	 */
	private AVLNode<T> balance(AVLNode<T> node) {
		if (node.balanceFactor == -2) {
			if (node.lChild.balanceFactor <= 0) {
				return leftLeftCase(node);
			} 
			else {
				return leftRightCase(node);
			}
		}	// Right heavy subtree needs balancing.
		else if (node.balanceFactor == +2) {
			if (node.rChild.balanceFactor >= 0) {
				return rightRightCase(node);
			} 
			else {
				return rightLeftCase(node);
			}
		}
		return node;	// The node either has a balance factor of 0, +1 or -1.
	}

	/** Perform a right rotation. - See Reference 2
	 * 
	 * @param node
	 * @return
	 */
	private AVLNode<T> leftLeftCase(AVLNode<T> node) {
		return rightRotation(node);
	}

	/** Perform a left rotation, then a right rotation. - See Reference 2
	 * 
	 * @param node
	 * @return
	 */
	private AVLNode<T> leftRightCase(AVLNode<T> node) {
		node.lChild = leftRotation(node.lChild);
		return leftLeftCase(node);
	}

	/** Perform a left rotation. - See Reference 2
	 * 
	 * @param node
	 * @return
	 */
	private AVLNode<T> rightRightCase(AVLNode<T> node) {
		return leftRotation(node);
	}

	/** Perform a right rotation, then a left rotation. - See Reference 2
	 * 
	 * @param node
	 * @return
	 */
	private AVLNode<T> rightLeftCase(AVLNode<T> node) {
		node.rChild = rightRotation(node.rChild);
		return rightRightCase(node);
	}

	/** Perform a left rotation on the subtree of the given node - See Reference 2
	 * 
	 * @param node
	 * @return
	 */
	private AVLNode<T> leftRotation(AVLNode<T> node) {
		AVLNode<T> newParent = node.rChild;
		node.rChild = newParent.lChild;
		newParent.lChild = node;
		update(node);
		update(newParent);
		return newParent;
	}

	/** Perform a right rotation on the subtree of the given node - See Reference 2
	 * 
	 * @param node
	 * @return
	 */
	private AVLNode<T> rightRotation(AVLNode<T> node) {
		AVLNode<T> newParent = node.lChild;
		node.lChild = newParent.rChild;
		newParent.rChild = node;
		update(node);
		update(newParent);
		return newParent;
	}

	/** Deletes a value from this AVL tree if it exists - See Reference 2
	 * 
	 * @param value
	 * @return
	 */
	public boolean delete(T value) {
		if (value == null) {
			return false; //The value was not found.
		}

		if (contains(root, value)) {
			root = remove(root, value); //Remove the value from the tree
			nodeCount--;
			return true;
		}
		return false; //The value was not found.
	}

	/** Recursively removes a value from the AVL tree. - See Reference 2
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	private AVLNode<T> remove(AVLNode<T> node, T value) {
		if (node == null) {
			return null;
		}
		int cmp = value.compareTo( node.key);

		if (cmp < 0) {	// Recusively call remove on the left subtree
			node.lChild = remove(node.lChild, value);
		} 
		else if (cmp > 0) {	// Recusively call remove on the right subtree
			node.rChild = remove(node.rChild, value);
		} 
		else {
			if (node.lChild == null) {
				return node.rChild;
			} 
			else if (node.rChild == null) {
				return node.lChild;
			} 
			else {
				if (node.lChild.height > node.rChild.height) {
					// Swap
					T successorValue = findMax(node.lChild);
					node.key = successorValue;
					node.lChild = remove(node.lChild, successorValue);
				} 
				else {
					// Swap
					T successorValue = findMin(node.rChild);
					node.key = successorValue;
					node.rChild = remove(node.rChild, successorValue);
				}
			}
		}
		update(node);	// Update node's balance factor and height values.
		return balance(node);	// Re-balance the tree.
	}

	/** Finds the node with the lowest value (left-most node) - See Reference 2
	 * 
	 * @param node
	 * @return
	 */
	private T findMin(AVLNode<T> node) {
		while (node.lChild != null) {
			node = node.lChild;
		}
		return node.key;
	}

	/** Finds the node with the greatest value (right-most node) - See Reference 2
	 * 
	 * @param node
	 * @return
	 */
	private T findMax(AVLNode<T> node) {
		while (node.rChild != null) {
			node = node.rChild;
		}
		return node.key;
	}


	/** Recursively prints the AVL tree using depth-first (in-order) traversal.
	 * 
	 * @param node - current node
	 */
	public void printDepthFirst(AVLNode<T> node, PrintWriter outputStream) {
		if (node != null) {
			printDepthFirst(node.lChild, outputStream); // Print left subtree
			System.out.println("\t\t"+node.key +" - height: " + node.height);
			outputStream.println("\t\t"+node.key +" - height: " + node.height);
			printDepthFirst(node.rChild, outputStream); // Print right subtree
		}
	}//End of printDepthFirst
	
	// Uses a queue to traverse all the nodes in the AVL tree using breadth first traversal. - See Reference 3
	public void printBreadthFirst(AVLNode<T> root, PrintWriter outputStream) {
		if(root != null) {
			MyQueue<T> Q = new MyQueue<T>(nodeCount);
			Q.enqueue(root); //Enqueue the root node
			
			while(!Q.isEmpty()) {
				AVLNode<T> node = Q.dequeue();
				
				if(node.lChild != null) {
					Q.enqueue(node.lChild);
				}
				if(node.rChild != null) {
					Q.enqueue(node.rChild);
				}
				System.out.println("\t\t"+node.key +" - height: " + node.height);
				outputStream.println("\t\t"+node.key +" - height: " + node.height);
			}
		}
	}//End of printBreadthFirst
	
	/** Search for the given node, and print information about the first occurrence you find.
	 * 
	 * @param node
	 * @param value
	 * @return
	 */
	public boolean search(AVLNode<T> node, T value, PrintWriter outputStream) {
		if (node == null) {
			return false;
		}

		int cmp = value.compareTo(node.key);

		if (cmp < 0) {	// Search left subtree.
			return search(node.lChild, value, outputStream);
		}

		if (cmp > 0) {	// Search right subtree.
			return search(node.rChild, value, outputStream);
		}
		System.out.print("Found " + node.key + " with height: " + node.height);
		outputStream.print("Found " + node.key + " with height: " + node.height);
		return true;	// Found value in tree.
	}
	
	/** Uses a depth first (post-order) traversal to recursively delete every node in the tree.
	 * 
	 * @param node - Node currently being searched/ deleted
	 */
	public void deleteTree(AVLNode<T> node) {
		if (node != null) {
			deleteTree(node.lChild); // Traverse left subtree
			deleteTree(node.rChild); // Traverse right subtree
			node.lChild = null;
			node.rChild = null;
			node = null;	//Delete node.
		}
	}//End of deleteTree
	
	
}//" End of class AVLTree - See References in class main.
