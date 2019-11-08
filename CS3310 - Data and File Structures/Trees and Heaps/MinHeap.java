package pack;

import java.io.PrintWriter;

/** This class is an implicit min heap.
 * 
 * @author David Moussalli
 *
 * See References at the top of class Main.
 */
public class MinHeap { 
	String[] heap; 	//Heap array
	private int heapSize;	//Number of items in the heap
	private int maxIndex;	//Index of the element with the maximum value.
	private long startTime, //Timers
				 stopTime, 
				 avgInsertTime,
				 maxSearchTime,
				 minSearchTime;
	PrintWriter outputStream;
	
	/** Constructor for class MinHeap. Initializes variables, and fills the minHeap.  - See Reference 1
	 * 
	 * @param array - array to fill the heap with.
	 */
	public MinHeap(String[] array, PrintWriter outputStream) {
		heap = new String[array.length]; //Initialize heap
		heapSize = 0;	//Initialize heap size
		maxIndex = 0;
		avgInsertTime = 0;
		maxSearchTime = 0;
		minSearchTime = 0;
		this.outputStream = outputStream;
		
		int counter=0;
		for(int i=0; i<array.length; i++) {//Fill heap with data from string[] array and calculate average time to insert.
			startTime = System.nanoTime();
			insert(array[i]);	//Insert name into heap.
			stopTime = System.nanoTime();
			avgInsertTime += (stopTime-startTime);
			counter++;
		}

		avgInsertTime /= counter;

		startTime = System.nanoTime();
		for(int i=0; i<heap.length; i++) {//Find the maximum value in the heap.
			if(heap[maxIndex].compareTo(heap[i]) <= 0) {
				maxIndex = i;
			}
		}
		stopTime = System.nanoTime();
		maxSearchTime = (stopTime-startTime); //Calculate time it took to find max item
	}//End of MinHeap constructor

	
	/** Inserts a string value into the heap - See Reference 1
	 * 
	 * @param value - String to be inserted into the heap
	 */
	public void insert(String value) {
        if (heapSize == heap.length) {
        }
        else {
              heapSize++;
              heap[heapSize-1] = value;
              heapifyBottomUp(heapSize-1);
        }
	}//End of insert
	
	/** Heapifies the heap when a string is inserted. - See Reference 1
	 * 
	 * @param curIndex - current heap element index
	 */
	private void heapifyBottomUp(int curIndex) {
        int parentIndex;
        String tmp;
        if(curIndex != 0) {
              parentIndex = parentIndex(curIndex);
              if(heap[parentIndex].compareTo(heap[curIndex]) > 0) { //If parent value > temp value, swap the two elements.
                    tmp = heap[parentIndex];
                    heap[parentIndex] = heap[curIndex];
                    heap[curIndex] = tmp;
                    heapifyBottomUp(parentIndex);
              }
        }
	}//End of heapifyBottomUp
	
	/** Recursively prints the heap in pre-order traversal. - See Reference 2
	 * @param curIndex
	 */
	public void printPreOrder(int curIndex){
		if(curIndex >= heapSize) { //Checks if current index is larger than the size of the heap.
			return;
		}
		System.out.print(heap[curIndex] + ", ");
		outputStream.print(heap[curIndex] + ", ");
		printPreOrder(lChildIndex(curIndex)); //Print left subtree
		printPreOrder(rChildIndex(curIndex)); //Print right subtree
	}//End of printPreOrder
	
	/** Checks if the heap is empty. - See Reference 1
	 * @return returns true or false, based on whether heapSize is 0 or not.
	 */
	public boolean isEmpty() {
        return (heapSize == 0);
	}
	
	/** Search for a given name in the heap and print out information about it's location in the heap.
	 * 
	 * @param name - name being searched for
	 * @param curIndex - current index
	 * @return returns true or false, based on whether the name was found, so it will only report the
	 * 		first if there are duplicates, and if it is not found at all it will tell the user.
	 */
	public boolean searchForName(String name, int curIndex) {
		if(curIndex >= heapSize) { //Checks if current index is larger than the size of the heap.
			return false;
		}
		if(heap[curIndex].compareTo(name) == 0) { //If name was found, output information
			System.out.println("\t\tFound at index " + curIndex + ".");
			System.out.println("\t\tDepth: " + findDepth(curIndex) + ".");
			outputStream.println("\t\tFound at index " + curIndex + ".");
			outputStream.println("\t\tDepth: " + findDepth(curIndex) + ".");
			if((lChildIndex(curIndex)) >= heapSize && (rChildIndex(curIndex)) >= heapSize) {//If element is a leaf node.
				System.out.println("\t\tLeaf node.");
				outputStream.println("\t\tLeaf node.");
			}
			System.out.println("\t\tSubtree size: " + findSubtreeSize(curIndex) + ".");
			outputStream.println("\t\tSubtree size: " + findSubtreeSize(curIndex) + ".");
			return true;
		}
		else {
			if(searchForName(name, lChildIndex(curIndex)) == false) { //If name was NOT found in left subtree,
				return searchForName(name, rChildIndex(curIndex)); 	  //	search the right subtree
			}
			return true; //If name WAS found in left subtree, return true.
		}
		
	}//End of searchForName

	/** Finds the depth of the given index. - See Reference 3
	 * @param curIndex - current index
	 * @return returns the depth of the provided node
	 */
	public int findDepth(int curIndex) {
		if(curIndex == 0) { //If curIndex is root index
			return 0;
		}
		else {
			return 1+findDepth(parentIndex(curIndex));
		}
	}//End of findDepth
	
	/** Finds the subtree size of the given index.
	 * @param curIndex - current index
	 * @return returns the size of the subtree
	 */
	public int findSubtreeSize(int curIndex) {
		if(curIndex >= heapSize) { //If curIndex is larger than the heapSize
			return 0;
		}
		return 1 + findSubtreeSize(lChildIndex(curIndex)) + findSubtreeSize(rChildIndex(curIndex));
	}
	
//============================================= GET Methods ==============================
    private int lChildIndex(int curIndex) {
        return (2*curIndex+1);
    }

    private int rChildIndex(int curIndex) {
        return (2*curIndex+2);
    }

    private int parentIndex(int curIndex) {
       	return (curIndex-1)/2;
    }
	
	/** GET method which returns the string with the lowest value in the heap.  - See Reference 1
	 * @return returns the string with the lowest value in the heap.
	 */
	public String getMinimum() {
		startTime = System.nanoTime();
		if(isEmpty()) {
			stopTime = System.nanoTime();
			minSearchTime = (stopTime-startTime); //Calculate time it took to find min item
			return "null"; //If heap is empty, just return null???
			
		}
		else {
			stopTime = System.nanoTime();
			minSearchTime = (stopTime-startTime); //Calculate time it took to find min item
			return heap[0];
		}
	}
	
	/** GET method which returns the string with the highest value in the heap.  - See Reference 1
	 * @return returns the string with the lowest value in the heap.
	 */
	public String getMaximum() {
		if(isEmpty()) {
			return "null"; //If heap is empty, just return null???
		}
		else {
			return heap[maxIndex];
		}
	}
	
	/** GET method which returns the average time it took to insert a name into the heap.
	 * @return returns the average time it took to insert a name into the heap.
	 */
	public long getAvgInsertTime() {
		if(isEmpty()) {
			return -1; //If heap is empty, just return null???
		}
		else {
			return avgInsertTime;
		}
	}
	
	/**
	 * @return Returns the time it took to find the minimum value
	 */
	public long getMinSearchTime() {
		if(isEmpty()) {
			return -1; //If heap is empty, just return null???
		}
		else {
			return minSearchTime;
		}
	}
	
	/**
	 * @return Returns the time it took to find the maximum value
	 */
	public long getMaxSearchTime() {
		if(isEmpty()) {
			return -1; //If heap is empty, just return null???
		}
		else {
			return maxSearchTime;
		}
	}	
	
}//End of class MinHeap
