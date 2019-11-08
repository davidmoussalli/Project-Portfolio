package hw2cs3310_moussalli_021218;

public class MyAStack {
	private int[] aStack;

	/** Constructor for the MyAStack class
	 */
	public MyAStack(){
		//Before aStack can be initialized, we need to know how large the array needs to be...
		//Because of this, aStack will only be initialized after we convert the integer
		//	to binary (in the store() function).
	}
	
	/** Takes an integer and stores its binary digits in the AStack
	 * 
	 * @param num - the number being inserted into the AStack
	 */
	public void store(int num) {
		try {
			//Convert to binary and store each digit...
			if(num == 0) { //If the number is 0, skip the conversion and store 0 as the number.
				aStack = new int[1];
				aStack[0] = 0;
			}
			else {
				String binString ="";//String that contains the binary equivalent of the integer.
				int remainder;
				while(num>0) {
					remainder = num%2;
					binString = remainder + binString;//Add to the binary string.
					num = num/2;
				}
	
				aStack = new int[binString.length()]; //Initialize aStack to be the size of binString
				for( int i=0; i<binString.length(); i++) {
					aStack[i] = Character.getNumericValue(binString.charAt(i)); //Store each digit of the integer in the LL
				}
			}
		}
		catch(Exception e) {
			System.out.println("Error in MyAStack.store(): " + e + "\nExiting Program.");
		}
	}
	
	/** Reads the data structure's stored binary digits and converts them into a decimal integer.
	 * 
	 * @return returns the decimal integer
	 */
	public int toDecimal() {
		String binaryString = showStack();
		int number = -1;
		try {
			number = Integer.parseInt(binaryString, 2); //Change the binaryString to decimal and return it.
		}
		catch(Exception e) {
			System.out.println("Error in MyAStack.toDecimal(): " + e + "\nExiting Program.");
		}		
		return number;
	}

	/** This method displays the contents of the AStack to the user.
	 * 
	 * @return returns a string holding the contents of the AStack.
	 */
	public String showStack() {
		String binString = "";
		for(int i=0; i<aStack.length; i++) {
			binString += aStack[i];
		}
		return binString;
	}
}
