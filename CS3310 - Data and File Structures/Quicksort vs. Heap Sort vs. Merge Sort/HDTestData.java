package pack;

/** The HDTestData class is a class whose instance contains all the data from a single
 * 		hard drive test.
 * 
 * @author David Moussalli
 */
public class HDTestData {
	private String serialNum;
	private String modelNum;
	private String capacity;
	private String hours;
	
	/** Constructor for the HDTestData class
	 * 
	 * @param serial
	 * @param model
	 * @param capacity
	 * @param hours
	 */
	public HDTestData(String line) {
		String[] lineAttributes = line.split(",");
		
		this.serialNum = lineAttributes[0];
		this.modelNum = lineAttributes[1];
		this.capacity = lineAttributes[2];
		this.hours = lineAttributes[3];
	}
	
//Public GET methods:
	
	/** Get method for the serial number.
	 * 
	 * @return returns the serial number.
	 */
	public String getSerialNum() {
		return serialNum;
	}
	
	/** Get method for the model number.
	 * 
	 * @return returns the modelNum.
	 */
	public String getModelNum() {
		return modelNum;
	}
	
	/** Get method for the capacity.
	 * 
	 * @return returns the capacity.
	 */
	public String getCapacity() {
		return capacity;
	}
	
	/** Get method for the hours.
	 * 
	 * @return returns the hours.
	 */
	public String getHours() {
		return hours;
	}
	
	
	
	/** toString() method used for testing...
	 * Returns all attributes of the HDTestData object in a String format.
	 */
	public String toString() {
		return String.format(serialNum + "\t" + modelNum + "\t" + capacity + "\t" + hours); 
	}
}
