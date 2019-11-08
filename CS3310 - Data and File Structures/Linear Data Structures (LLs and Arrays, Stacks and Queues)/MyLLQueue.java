package hw2cs3310_moussalli_021218;

public class MyLLQueue {
	private MyLinkedList LL;
	
	/**
	 * Constructor for the MyLLQueue class
	 */
	public MyLLQueue(){
		LL = new MyLinkedList();
	}
	
	/** Adds each digit of the binary number to the rear of the LLQueue
	 * 
	 * @param num - the number being inserted into the LLQueue
	 */
	public void enqueue(int num) {
		store(num);
	}
	
	/** Deletes and returns the head node from the LLQueue
	 */
	public MyNode dequeue() {
		return LL.delete();
	}
	
	/** Takes an integer and stores its binary digits in the LLQueue
	 * 
	 * @param num - the number being inserted into the LLQueue
	 */
	public void store(int num) {
		try {
			if(num == 0) { //If the number is 0, skip the conversion and store 0 as the number.
				LL.insert(0);
			}
			else {
				String binString ="";//String that contains the binary equivalent of the integer.
				int remainder;
				while(num>0) {
					remainder = num%2;
					binString += remainder;//Add the remainder to the binary string.
					num = num/2;
				}
				for(int i=0; i<binString.length(); i++) {//Store each digit of the integer in the LL
					LL.insert(Character.getNumericValue(binString.charAt(i))); 
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error in MyLLQueue.store(): " + e + "\nExiting Program.");
		}
	}
	
	/** Reads the data structure's stored binary digits and converts them into a decimal integer.
	 * 
	 * @return returns the decimal equivalent of the binary integer
	 */
	public int toDecimal() {
		String binaryString = LL.showLL();
		int number = -1;
		try {
			number = Integer.parseInt(binaryString, 2); //Change the binaryString to decimal and return it.
		}
		catch(Exception e) {
			System.out.println("Error in MyLLQueue.toDecimal(): " + e + "\nExiting Program.");
		}		
		return number;
	}
	
	/** This method displays the contents of the LLQueue to the user.
	 * 
	 * @return returns a string holding the contents of the LLQueue
	 */
	public String showQueue() {
		return LL.showLL();
	}
}
