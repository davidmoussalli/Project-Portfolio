package pack;

/** The Node class is a generic node class for the LinkedList class.
 * 
 * @author David Moussalli
 */
public class Node {
	private HDTestData testDataObject;
	Node next;
	
	/** Constructor for MyNode()
	 * Initializes the node's testDataObject to the given HDTestData object,
	 * 	and the next node to null.
	 */
	public Node(HDTestData hdTest) {
		this.testDataObject = hdTest;
		next = null;
	}
	
	/** Get function for the HDTestData object in the node.
	 * 
	 * @return returns the node's HDTestData object.
	 */
	public HDTestData getObject() {
		return this.testDataObject;
	}
	
	/** toString method used for testing...
	 */
	public String toString() {
		return testDataObject.toString(); //Call to HDTestData's toString() method
	}
}
