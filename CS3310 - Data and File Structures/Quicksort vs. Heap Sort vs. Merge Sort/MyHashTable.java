package pack;

/** The MyHashTable class primarily contains the "table" member, which is an array of
 * 		10,000 LinkedList objects. 
 * 
 * @author David Moussalli
 */
public class MyHashTable{
	private LinkedList[] table; //Array of LinkedList objects.
	
	private long startTime, stopTime,	//Basic timers
		avgHashSearchTime=0;	 		//Timers to calculate average hash table search time.
	int numHashSearches=0;				//Number of times the hash table is searched.
	
	/** Constructor for the MyHashTable class
	 */
	public MyHashTable(int size){
		table = new LinkedList[size];	//Initialize the table of 10,000 Linked lists.
	}
	
	/** Generates a hash value between 0 and 9999 based on the given key(sNum).
	 * 
	 * @param sNum - Serial number (key)
	 * @return returns a hash value to be used as a table index
	 */
	public int hash(String sNum){//See Reference 1 in class Main.
		char ch[] = sNum.toCharArray(); //Put string into a char[]
		int i, sum, hashedIndex = 0;
		for(sum=0, i=0; i<ch.length; i++){ //Sum up the ascii values for each character
			sum += ch[i];
		}
		hashedIndex = (int)(sum * 123.456); //Make it more random
		hashedIndex %= table.length; //mod by table size
		return hashedIndex; //Return the hash value that will be used for the index.
	} //End of hash()
	
	
	/** Inserts an HDTestData object into the hash table
	 *
	 * @param sNum - Serial number (key)
	 * @param hdTest - HDTestData object
	 */
	public void put(String sNum, HDTestData hdTest){
		int hashedIndex = hash(sNum);
		if(table[hashedIndex] == null){ //If linked list at table[hashedIndex]  is null,
			table[hashedIndex] = new LinkedList();	//Create new LinkedList
		}
		table[hashedIndex].insert(hdTest);	//Insert the HDTestData object into the LinkedList.
	} //End of put()
	
	
	/** Returns the full HDTestData object that has the given serial number.
	 * 
	 * @param sNum - Serial number (key)
	 * @return returns the associated HDTestData object
	 */
	public HDTestData get(String sNum){
		int hashedIndex = hash(sNum); 	//Hash the string, then find the object in "table"
		startTime = System.nanoTime();	//Start timer
		if(table[hashedIndex] != null){ //Check if linked list is null
			Node tempNode = table[hashedIndex].first; 	//Find correct LL.
			while(tempNode != null){					//Find correct node in the LL.
				if(tempNode.getObject().getSerialNum().compareTo(sNum) == 0){//Search the table for sNum's associated HDTest object. 
					return tempNode.getObject(); 		//If the given sNum's HDTest object is found, return it.
				}
				else{
					tempNode = tempNode.next; 			//Increment tempNode
				}
			}//End while
		}//End if
		stopTime = System.nanoTime();	//Stop timer
		this.avgHashSearchTime += (stopTime-startTime); //Add time to average hash search time
		this.numHashSearches++;	//Increment the number of hash searches
		
		return new HDTestData("null,null,null,null");	//If no serial number matches sNum, return a null HDTestData object. (Error handling)
	} //End of get()
	
	/** Get function for the average hash table search time
	 * 
	 * @return returns the average time to search the hash table.
	 */
	public float getAvgHashSearchTime(){
		return (this.avgHashSearchTime/this.numHashSearches);
	}
	
	/** toString() method used for testing...
	 */
	public void myToString() {
		for(int i=0; i<table.length; i++) {
			if(table[i] != null) {
				table[i].myToString(); //Call LinkedList's myToString() method.
			}
		}
	}
}
