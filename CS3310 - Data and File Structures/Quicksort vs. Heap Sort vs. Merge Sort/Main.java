package pack;
import java.io.*;
import java.util.*;

/**
 * Class: 	CS 3310 
 * Assignment 	3
 * Date:	3/22/2018
 * @author 	David Moussalli
 * 
 * 
 * The purpose of this program is to teach us to understand how hash tables work and 
 * 	to understand the advanced sorting mehods quick sort, merge sort, and heap sort.
 * 
 * This program reads data from "data_main.csv" into an object of the MyHashTable class, which 
 * 	contains a private "table" member that is an array of linked lists. The linked lists hold 
 *  HDTestData objects, which contains all the data from a single hard drive test. 
 * Once the data is read into the hash table, the program takes a command line argument
 * 	indicating a .csv file containing serial numbers, which are then read into a String array.
 * The serial numbers are then QuickSorted based on their corresponding model name in the 
 * 	hash table.
 * The same process is then performed using Merge Sort and Heap Sort.
 * These sorts are then looped m times and the average time for each sort algorithm is printed
 * 	to the user.
 * 
 * USAGE WARNING:
 * This program runs extremely slow when using serial number files larger than 10k. It is not
 * 	recommended to use any file larger than 10k, or have a value for n>10000 (in Part C).
 * 
 * 
 * REFERENCES: 
 * 		Reference 1: Hash Function for Strings:
	 		Title:	"15.03.02 - A Simple Hash Function for Strings"
	 		Published by: Canvas by Instructure, Inc.
		 	Date used: Thursday, March 15, 2018
  			Availability:
 	 			- https://canvas.instructure.com/courses/1266618/assignments/7759191
 	 	
 	 	Reference 2: Sort Functions:
 	 		2A: (Quick Sort)
			 	Title:	"Java Quick Sort"
			 	Channel: "Derek Banas"
			 	Published: March 16, 2013
			 	Date used: March 20, 2018
			 	Availability:
			 	 	- https://www.youtube.com/watch?v=mN5ib1XasSA&ab_channel=DerekBanas
			 
			2B: (Quick Sort)
				Title: "Quick Sort"
			    Publisher: GeeksforGeeks
			    Author: Rajat Mishra
			    Date used: 3/20/2018
				Availability:
			 	 	- https://www.geeksforgeeks.org/quick-sort/
				
			2C: (Merge Sort)
				Title: "Sorting (names) using Merge Sort" 
				Asked by: "jarnthrax"
				Date asked: December 27, 2013
				Answered by: "Ashish Aggarwal"
				Date answered: December 27, 2013
				Date used: 3/20/2018
				Availability:
			 	 	- https://stackoverflow.com/questions/20795158/sorting-names-using-merge-sort
			
			2D: (Heap Sort)
				Title: "Heap Sort"
			    Publisher: GeeksforGeeks
			    Author: N/A
			    Date used: 3/20/2018
				Availability:
			 	 	- https://www.geeksforgeeks.org/heap-sort/
 */
public class Main {
	public static MyHashTable hashTable = new MyHashTable(10000); //Create hash table object and give make the table have size 10,000.
	public static String[] snumArray;	//Array of serial numbers read in from the provided .csv file.
	
	/** Main method
	 * 
	 * @param args - Command line arguments
	 * @throws FileNotFoundException - in case the file is not found
	 */
	public static void main(String[] args) throws FileNotFoundException{
		//Timers:
		long startTime=0, stopTime=0, 
			avgInsertTime=0,					//Average time to insert an object into the hash table
			avgQuickSortTime=0,					//Average Quick Sort search time
			avgMergeSortTime=0,					//Average Merge Sort search time
			avgHeapSortTime=0;					//Average Heap Sort search time
		
		//Counters:
		int snumCounter=0,			//Number of serial numbers in the provided .csv file.
			
			numInserts=0,			//Number of objects read into the hash table
			numQuickSorts=0,		//Number of times Quick Sort was performed
			numMergeSorts=0,		//Number of times Merge Sort was performed
			numHeapSorts=0;			//Number of times Heap Sort was performed
			
//================================ Part A ================================
		File csvFile = new File("data_main.csv");	//Create a file object
		Scanner csvScanner = new Scanner(csvFile);  //Create a csv file scanner
		csvScanner.nextLine(); //This line is here so we don't use the header line.
		
		while(csvScanner.hasNextLine()){ //Fill hash table 
			startTime = System.nanoTime(); 		 //Start timer
			
			String line = csvScanner.nextLine();				//Read next line
			HDTestData tempHDTest = new HDTestData(line);		//Create an HDTestData object from the line
			String[] lineItems = line.split(",");				//Split line to get the serial number			
			hashTable.put(lineItems[0], tempHDTest); 			//Store it in the hash table.
			
			stopTime = System.nanoTime(); 		 //Stop timer
			avgInsertTime += stopTime-startTime; //Add time to averagetime
			numInserts++;						 //Increment numObjects
		}
		csvScanner.close(); //Close the file scanner
		
		avgInsertTime = avgInsertTime/numInserts;	//Divide the sum of the time it took to store all the objects by the numObjects
		System.out.println("\nAverage	time to	insert into hash table:\n\t" + avgInsertTime +
				" nanoseconds\n\n- - - - - - - - - - - - - - - - - - - - - - - -");

//================================ Part B ================================
		System.out.println("file to sort: "+args[0] + "\n\n- - - - - - - - - - - - - - - - - - - - - - - -");
		File countFile = new File(args[0]);			   //Create a file object
		Scanner countScanner = new Scanner(countFile); //Create a csv file scanner

		while(countScanner.hasNextLine()){ //Count the number of lines in the file
			countScanner.nextLine(); //Read line
			snumCounter++; //Increment counter
		}
		countScanner.close(); //Close countScanner		

		//Now create a new file and scanner using the same scanner - to actually read the data in.
		File sortFile = new File(args[0]);			 //Create a file object
		Scanner sortScanner = new Scanner(sortFile); //Create a csv file scanner
		snumArray = new String[snumCounter]; 		 //Initialize snumArray to have size snumCounter

		for(int i=0; sortScanner.hasNextLine(); i++){ //Read the file into the array
			snumArray[i] = sortScanner.nextLine();
		}
		sortScanner.close(); //Close sortScanner	

		
//=========== QUICK Sort called here ============
		
		startTime = System.nanoTime(); 		//Start timer
		quickSort(0, snumArray.length-1);	//Call to quickSort()
		stopTime = System.nanoTime();		//Stop timer
		avgQuickSortTime+= (stopTime-startTime); //Add time to the average time to Quick Sort
		numQuickSorts++; //Increment the number of times Quick Sort was performed

		System.out.println("first 4 after sorting on model name:");
		for(int i=0; i<4; i++){ 
			System.out.println("\t" + snumArray[i]);
		}
		
		System.out.println("\nlast 4 after sorting on model name:");
		for(int i=snumArray.length-4; i<snumArray.length; i++){ 
			System.out.println("\t" + snumArray[i]);
		}
		System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - -");

		

		
//=========== MERGE Sort called here =======

		startTime = System.nanoTime(); 		//Start timer
		myMergeSort(snumArray);				//Call to myMergeSort()
		stopTime = System.nanoTime();		//Stop timer
		avgMergeSortTime+= (stopTime-startTime); //Add time to the average time to Merge Sort
		numMergeSorts++; //Increment the number of times Merge Sort was performed

		System.out.println("first 4 after sorting on capacity:");
		for(int i=0; i<4; i++){ 
			System.out.println("\t" + snumArray[i]);
		}
		
		System.out.println("\nlast 4 after sorting on capacity:");
		for(int i=snumArray.length-4; i<snumArray.length; i++){ 
			System.out.println("\t" + snumArray[i]);
		}
		System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - -");



//=========== HEAP Sort called here =========
		myHeapSort(snumArray); //Call to myHeapSort()
		
		System.out.println("first 4 after sorting on powered on hours:");
		for(int i=0; i<4; i++){ 
			System.out.println("\t" + snumArray[i]);
		}
		
		System.out.println("\nlast 4 after sorting on powered on hours:");
		for(int i=snumArray.length-4; i<snumArray.length; i++){
			System.out.println("\t" + snumArray[i]);
		}
		System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - -");
		
		
		
		
//================================ Part C ================================
		
		File snum70kFile = new File("data_main.csv");	//Create a file object
		Scanner snum70kCountScanner = new Scanner(snum70kFile);  //Create file scanner
		
		snumCounter=0; //Initialize counter to 0
		while(snum70kCountScanner.hasNextLine()){ //Count the number of serial numbers in the file
			snum70kCountScanner.nextLine();
			snumCounter++;
		}
		snum70kCountScanner.close(); //Close the file
		
		Scanner snum70kScanner = new Scanner(snum70kFile);  //Create file scanner
		String[] snum70kArray = new String[snumCounter];	//Create array to hold the file's contents
		for(int i=0; snum70kScanner.hasNextLine(); i++){	//Read file into array
			snum70kArray[i] = snum70kScanner.nextLine();
		}
		snum70kScanner.close(); //Close the file
		
		
		int n=100, 	//Number of array items for the new array
			m=1;		//Number of loop iterations
		String[] snumArray2 = new String[n];	//New array to be sorted
		Random rand = new Random();

		for(int i=0; i<m; i++){ //Loop m times
			for(int j=0; j<n; j++){ //Fill the snumArray2 with random values before quick sorting.
				snumArray2[j] = snum70kArray[rand.nextInt(snum70kArray.length)];
			}
			startTime = System.nanoTime();
			quickSort2(snumArray2, 0, snumArray2.length-1); //Call to quickSort2()
			stopTime = System.nanoTime();
			avgQuickSortTime += (stopTime-startTime);
			numQuickSorts++;
			

			for(int j=0; j<n; j++){ //Fill the snumArray2 with random values before merge sorting.
				snumArray2[j] = snum70kArray[rand.nextInt(snum70kArray.length)];
			}
			startTime = System.nanoTime();
			myMergeSort(snumArray2);	//Call to myMergeSort()
			stopTime = System.nanoTime();
			avgMergeSortTime += (stopTime-startTime);
			numMergeSorts++;

			
			for(int j=0; j<n; j++){ //Fill the snumArray2 with random values before heap sorting.
				snumArray2[j] = snum70kArray[rand.nextInt(snum70kArray.length)];
			}
			startTime = System.nanoTime();
			myHeapSort(snumArray2);	//Call to myHeapSort()
			stopTime = System.nanoTime();
			avgHeapSortTime += (stopTime-startTime);
			numHeapSorts++;
		}

//=========== Print Average Times =======
		System.out.println("average time taken to search  entries into my hash table:");
		System.out.println("\t" + hashTable.getAvgHashSearchTime() + " nanoseconds");
		System.out.println("\n- - - - - - - - - - - - - - - - - - - - - - - -");
		
		System.out.println("average time taken by quick sort on an array of size "+ n + " (calculated via m sorts):");
		System.out.println("\t" + (avgQuickSortTime/numQuickSorts) + " nanoseconds");
		
		System.out.println("\naverage time taken by merge sort on an array of size "+ n + " (calculated via m sorts):");
		System.out.println("\t" + (avgMergeSortTime/numMergeSorts) + " nanoseconds");
		
		System.out.println("\naverage time taken by heap sort on an array of size "+ n + " (calculated via m sorts):");
		System.out.println("\t" + (avgHeapSortTime/numHeapSorts) + " nanoseconds");

		System.out.println("\nDone."); //Done.
	}//End of main
//====================================== END OF MAIN ====================================================================

	
	
	
	
	
//====================================== QUICK SORT METHODS ====================================================================
	/** Generic quick sort method. See Reference 2B
	 * 
	 * @param low - low index in the array
	 * @param high - high index in the array
	 */
	public static void quickSort(int low, int high){
		if(low < high){ //If partition is not sorted
			int pivotIndex = partition(low, high);
			
			quickSort(low, pivotIndex-1);
			quickSort(pivotIndex+1, high);
		}
	}//End of sort
	
	/** Partition method for quickSort(). See Reference 2B
	 * 
	 * @param low - low index in the array
	 * @param high - high index in the array
	 * @return - returns the pivot index
	 */
	public static int partition(int low, int high){
		String pivot = hashTable.get(snumArray[high]).getModelNum(); 
	    int i=(low-1); // index of smaller element
	    
	    for(int j=low; j<high; j++){
	        if(hashTable.get(snumArray[j]).getModelNum().compareTo(pivot) <= 0){ // If current element is less than or equal to the pivot,
	            i++; //Iterate smaller element
	            
	            // swap snumArray[i] and snumArray[j]
	            String temp = snumArray[i];
	            snumArray[i] = snumArray[j];
	            snumArray[j] = temp;
	        }
	    }//End for
	    
	    // swap snumArray[i+1] and snumArray[high] (or pivot)
		String temp = snumArray[i+1];
	   	snumArray[i+1] = snumArray[high];
		snumArray[high] = temp;
	 
		return (i+1);
	}//End of partition
	
	
	//==================== Quick Sorts snumArray2 ====================
	/** Secondary Quick Sort method. The only difference from quickSort() is that it calls partition2()
	 * 		instead of partition(). See Reference 2B
	 * 
	 * @param snumArray2 - serial number array created in Part C.
	 * @param low - low index in the array
	 * @param high - high index in the array
	 */
	public static void quickSort2(String[] snumArray2, int low, int high){ //Same as the sort1, but calls partition2().
		if(low < high){
			int pi = partition2(snumArray2, low, high);
			
	       quickSort2(snumArray2, low, pi-1);
	       quickSort2(snumArray2, pi+1, high);
		}
	}//End of sort2
	
	//---------------- Partitions snumArray2 -------------------
	/** Partition method for quickSort2(). See Reference 2B
	 * 
	 * @param snumArray2 - serial number array created in Part C.
	 * @param low - low index in the array
	 * @param high - high index in the array
	 * @return - returns the pivot index
	 */
	public static int partition2(String[] snumArray2, int low, int high){
		String pivot = hashTable.get(snumArray2[high]).getModelNum(); 
	    int i=(low-1); //Index of smaller element
	    
	    for(int j=low; j<high; j++){
	        if(hashTable.get(snumArray2[j]).getModelNum().compareTo(pivot) <= 0){ // If current element is less than or equal to the pivot,
	            i++; //Iterate smaller element and
	            
	            //Swap snumArray[i] and snumArray[j]
	            String temp = snumArray2[i];
	            snumArray2[i] = snumArray2[j];
	            snumArray2[j] = temp;
	        }
	    }
	    //Swap snumArray[i+1] and pivot
	    String temp = snumArray2[i+1];
	    snumArray2[i+1] = snumArray2[high];
		snumArray2[high] = temp;
	 
		return (i+1); //Return the pivot index
	}//End of partition2
	//-----------------------------------


//====================================== MERGE SORT METHODS ====================================================================
	/** Generic merge Sort method. See Reference 2C.
	 * 
	 * @param serialNums - array of serial numbers
	 */
	public static void myMergeSort(String[] serialNums){
		if(serialNums.length >= 2){ //If there are 2 or more elements in the array
			String[] left = new String[serialNums.length/2];	//create 2 arrays, each with half the length of the serialNums array
			String[] right = new String[serialNums.length - (serialNums.length/2)];
	
			for(int i=0; i<left.length; i++){	//Fill the left array with data from the left side of serialNums
				left[i] = serialNums[i];
			}
			for(int i=0; i<right.length; i++){ //Fill the right array with data from the right side of serialNums
				right[i] = serialNums[i + (serialNums.length/2)];
			}
			myMergeSort(left); //Recursively call myMergeSort() on both arrays
			myMergeSort(right);
			//Once you reach here, that means left and right arrays are now single elements...
			merge(serialNums, left, right); //Now merge the two arrays into one.
		}
	}//End of myMergeSort
	
	/** Merge method for myMergeSort(). See Reference 2C.
	 * 
	 * @param serialNums - array of serial numbers 
	 * @param left - smallest index in the serialNums array
	 * @param right - largest index in the serialNums array
	 */
	public static void merge(String[] serialNums, String[] left, String[] right){
		int leftPointer = 0; 
		int rightPointer = 0;
		String leftCapacity = hashTable.get(left[leftPointer]).getCapacity();
		String rightCapacity = hashTable.get(right[rightPointer]).getCapacity();
	  
		for(int i=0; i<serialNums.length; i++){
			if(rightPointer >= right.length || (leftPointer < left.length && leftCapacity.compareTo(rightCapacity) < 0)){ //If b has been completely searched through, OR leftCapacity > rightCapacity
			 	serialNums[i] = left[leftPointer]; //Merge left[a] into serialNums
			 	leftPointer++;
			}
			else{
				serialNums[i] = right[rightPointer];
				rightPointer++;
			}
		}
	}//End of merge
	
	
	
	
	
	
//====================================== HEAP SORT METHODS ====================================================================
	/** Generic heap Sort method. See Reference 2D.
	 * 
	 * @param snumArray - array of serial numbers
	 */
	public static void myHeapSort(String snumArray[]){
		int arraySize = snumArray.length;
	  	
	  	for(int i=(arraySize/2)-1; i>= 0; i--){// Build heap
	  		heapify(snumArray, arraySize, i);
	  	}
	  	
	  	// One by one extract an element from heap
	  	for(int i=arraySize-1; i>=0; i--){
	  		// Move current root to end
	  		String temp = snumArray[0];
	      	snumArray[0] = snumArray[i];
	      	snumArray[i] = temp;
	
	      	// call max heapify on the reduced heap
	      	heapify(snumArray, i, 0);
	  	}
	}//End of myHeapSort
	
	/** Heapify method for myHeapSort(). See Reference 2D.
	 * 
	 * @param snumArray - array of serial numbers
	 * @param arraySize - size of the array
	 * @param rootIndex - root index in the array 
	 */
	public static void heapify(String snumArray[], int arraySize, int rootIndex){
		int largest = rootIndex;  // Initialize largest as root
		int lChild = 2*rootIndex + 1;  // left child = 2*i + 1
		int rChild = 2*rootIndex + 2;  // right child = 2*i + 2
	
		try{//Try-catch block in case it tries to parse a "null" string
	  		// If left child's powered on hours are greater than root's
	        if(lChild<arraySize && (Integer.parseInt(hashTable.get(snumArray[lChild]).getHours()) > Integer.parseInt(hashTable.get(snumArray[largest]).getHours())) ){
	            largest = lChild; //Set largest = left child
	        }
	  	}
	  	catch(Exception e){ //If exception was found
	  		//do nothing, just skip over and keep running the program.
	  	}
	
	  	try{//Try-catch block in case it tries to parse a "null" string
	  		// If right child's powered on hours are greater than largest's so far
	        if(rChild<arraySize && (Integer.parseInt(hashTable.get(snumArray[rChild]).getHours()) > Integer.parseInt(hashTable.get(snumArray[largest]).getHours())) ){
	            largest = rChild; //Set largest = right child
	        }
	  	}
	  	catch(Exception e){ //If exception was found,
	  		//do nothing, just skip over and keep running the program.
	  	}
	
	  	if(largest != rootIndex){// If largest is not the root
	      	String temp = snumArray[rootIndex];	// then swap largest with root
	      	snumArray[rootIndex] = snumArray[largest];
	      	snumArray[largest] = temp;
	      	
	      	heapify(snumArray, arraySize, largest);// and recursively heapify
	  	}
	}//End of heapify
		

}//End of main		
