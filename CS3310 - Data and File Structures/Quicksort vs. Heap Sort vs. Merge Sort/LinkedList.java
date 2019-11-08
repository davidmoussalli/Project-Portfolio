package pack;

/** The LinkedList class is a generic Linked List class.
 * 
 * @author David Moussalli
 */
public class LinkedList {
	Node first, last; //First and last nodes of the LL
	
	/** Constructor for LinkedList()
	 */
	public LinkedList() {
		first = null; //Initialized first node to null
		last = null; //Set last = first
	}
	
	/** Inserts a node into the end of the LL
	 */
	public void insert(HDTestData hdTest) {
		Node temp = new Node(hdTest); 	//Create temp node.
		if(first == null) {	//If the LL is empty,
			first = temp;	//  set first  equal to the new node.
			last = first;   //Set last = first
		}
		else { //If the LL is not empty: 
			last.next = temp;
			last = temp;
		}
	}
	
	/** toString() method used for testing...
	 */
	public void myToString() {
		Node temp = first;
		int counter=0;
		while(temp != null && counter++<5) {
			System.out.println(temp);  //Call Node's toString() method.
			temp = temp.next;
		}
	}
}
