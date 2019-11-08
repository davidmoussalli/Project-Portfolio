package hw2cs3310_moussalli_021218;

public class MyLinkedList {
	MyNode first; //First node of the LL
	
	/**
	 * Constructor for MyLinkedList()
	 * 
	 */
	public MyLinkedList() {
		first = null; //Initialized first node to null
	}
	
	/**
	 * Inserts a node into LL
	 */
	public void insert(int num) {
		
		MyNode temp = new MyNode(num);//Create new node...
		temp.next = first;
		first = temp;//Add it to the front of the LL
	}
	
	/**
	 * Deletes and returns the top node from the LL
	 * 
	 * @return returns the deleted node.
	 */
	public MyNode delete() {
		MyNode temp = first;
		first = first.next;
		return temp;
	}
	
	/**
	 * 
	 * @return returns a string containing the number stored in the Linked List
	 */
	public String showLL() {
		MyNode temp = first;
		String numString = "";
		while(temp != null) {
			numString += temp.showNode();
			temp = temp.next;
		}
		return numString;
	}
}
