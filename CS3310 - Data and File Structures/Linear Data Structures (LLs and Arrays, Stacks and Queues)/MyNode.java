package hw2cs3310_moussalli_021218;

public class MyNode {
	private int myNum;
	MyNode next;
	
	/**
	 * Constructor for MyNode()
	 * 
	 */
	public MyNode(int num) {
		myNum = num;
		next = null;
	}
	
	public int showNode() {
		return myNum;
	}
}
