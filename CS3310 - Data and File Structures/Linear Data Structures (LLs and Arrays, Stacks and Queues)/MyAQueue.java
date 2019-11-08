package hw2cs3310_moussalli_021218;

public class MyAQueue {
	private int[] aQueue;
	private int front, rear;
	
	/** Constructor for the MyAQueue class
	 */
	public MyAQueue(){
		front = 0; 
		rear = 0;
		aQueue = new int[100];
	}
	
	/** Adds each digit of the binary number to the rear of the AQueue
	 * 
	 * @param num - the number being inserted into the AQueue
	 */
	public void enqueue(int num) {
		store(num);
		
	}
	
	/** Deletes and returns the head node from the AQueue
	 */
	public int dequeue() {
		int temp = aQueue[front];
		aQueue[front] = 0;
		front++;
		return temp;
	}

	/** Takes an integer and stores its binary digits in the AQueue
	 * 
	 * @param num - the number being inserted into the AQueue
	 */
	public void store(int num) {
		try {
			//Convert to binary and store each digit...
			if(num == 0) { //If the number is 0, skip the conversion and store 0 as the number.
				aQueue = new int[1];
				aQueue[0] = 0;
			}
			else {
				String binString ="";//String that contains the binary equivalent of the integer.
				int remainder;
				while(num>0) {
					remainder = num%2;
					binString = remainder + binString;//Add to the binary string.
					num = num/2;
				}

				for( int i=0; i<binString.length(); i++) {
					aQueue[rear] = Character.getNumericValue(binString.charAt(i)); //Store each digit of the integer in the LL
					rear++;
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error in MyAQueue.store(): " + e + "\nExiting Program.");
		}
	}
	
	/** Reads the data structure's stored binary digits and converts them into a decimal integer.
	 * 
	 * @return returns the decimal equivalent of the binary integer
	 */
	public int toDecimal() {
		String binaryString = showQueue();
		int number = -1;
		try {
			number = Integer.parseInt(binaryString, 2); //Change the binaryString to decimal and return it.
		}
		catch(Exception e) {
			System.out.println("Error in MyAQueue.toDecimal(): " + e + "\nExiting Program.");
		}		
		return number;
	}

	/** This method displays the contents of the AQueue to the user.
	 * 
	 * @return returns a string holding the contents of the AQueue
	 */
	public String showQueue() {
		String binString = "";
		for(int i=front; i<rear; i++) {
			binString += aQueue[i];
		}
		return binString;
	}
}
