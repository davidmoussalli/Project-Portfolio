package hw2cs3310_moussalli_021218;

public class MyLLStack {
	private MyLinkedList LL;
	
	/** Constructor for the MyLLStack class
	 */
	public MyLLStack() {
		LL = new MyLinkedList();
	}
	
	/** Takes a decimal integer and calls the store function to store the binary digits in the LLStack
	 * 
	 * @param num - the number being inserted into the LLStack
	 */
	public void push(int num) {
		store(num);
	}
	
	/** Deletes and returns the last node of the LLStack
	 * 
	 * @return returns the last node
	 */
	public MyNode pop() {
		String binString="";
		
		MyNode temp = LL.first;
		int counter=0;
		while(LL.first.next != null) {
			binString += LL.first.showNode();
			LL.first = LL.first.next;
			counter++;
		}
		while(LL.first!=null) {
			LL.delete();
		}
		for(int i=counter-1; i>=0; i--) {
			LL.insert(Character.getNumericValue(binString.charAt(i)));
		}
		return temp;
	}

	/** Takes an integer and stores its binary digits in the LLStack
	 * 
	 * @param num - the number being inserted into the LLStack
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
			System.out.println("Error in MyLLStack.store(): " + e + "\nExiting Program.");
		}
	}
	
	/** Reads the data structure's stored binary digits and converts them into a decimal integer.
	 *  
	 * @return returns the decimal number
	 */
	public int toDecimal() {
		String binaryString = LL.showLL();
		int number = -1;
		try {
			number = Integer.parseInt(binaryString, 2); //Change the binaryString to decimal and return it.
		}
		catch(Exception e) {
			System.out.println("Error in MyLLStack.toDecimal(): " + e + "\nExiting Program.");
		}		
		return number;
	}
	
	/** This method displays the contents of the LLStack to the user.
	 * 
	 * @return returns a string holding the contents of the LLStack.
	 */
	public String showStack() {
		return LL.showLL();
	}
}
