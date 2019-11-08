package pack;
import java.io.*;
import java.util.*;

/**
 * Class: 		CS 3310 
 * Assignment 	4
 * Date:		3/27/2018
 * @author 		David Moussalli
 * 
 * This program implements various forms of tree data structures and various related algorithms.
  It contains balanced min and max heaps, a binary search tree, and algorithms related to them.
  
 * The purpose of this program is to compare the time complexities of array-based min
  	heaps, node-based height balanced max heaps, and binary search trees.
  
 * Part A: Two String arrays are filled with names from two files, whose names are given via
  	command line arguments.
  	
 * Part B: The first array of names is sent to the MinHeap class, which creates a min heap and
 	fills it with the strings from the file. After each name is inserted, the heap is recursively
 	heapified, to keep the smallest values at the top of the heap. Various information about
 	the heap is then printed to the user, including the pre-order traversal of the heap, 
 	minimum and maximum values, information related to search results, and average insert
 	and search times. When a name being searched for is found, the program will report 
 	its index in the heapArray, its depth, if it is a leaf node, the subtree size, and the time
 	it took to find the value.
 	
 * Part C: The first array of names is sent to the MaxHeap class, which creates a max heap and
 	fills it with the strings from the file. After each name is inserted, the heap is recursively
 	heapified, to keep the largest values at the top of the heap. The same information printed 
 	about the min heap in Part B is printed about the max heap.
 	
 * Part D: The first array of names is sent to the MaxHeap class, which creates a max heap and
 	fills it with the strings from the file. Since it is a binary search tree, there is no need to 
 	do any kind of sorting or "heapifying", because it sorts the values as it goes. The same 
 	information printed about the min and max heaps are then printed about the binary search tree.
 	
 * Part E: In this part, a random number n (between 100-1000) is generated. This value is then 
 	used to pick out n random strings from the first array and create all the trees using 
 	the selected strings. This process is repeated n times and the total and average times to insert 
 	are printed out.
  
 * ============================= REFERENCES: =========================================
  		Reference 1: Array-based heap implementation:
  			1A:
		 		Title:	"Binary heap. Array-based internal representation."
		 		Published by: algolist.com
			 	Date used: Saturday, March 24, 2018
	  			Availability:
	 	 			- http://www.algolist.net/Data_structures/Binary_heap/Array-based_int_repr
	 	 	1B:
	 	 		Title:	"Inserting an element into a heap"
		 		Published by: algolist.com
			 	Date used: Saturday, March 24, 2018
	  			Availability:
	 	 			- http://www.algolist.net/Data_structures/Binary_heap/Insertion
 	 	
 	 	Reference 2: Printing tree in preorder:
	 	 	Title: "Array based binary tree preorder" 
			Asked by: "Raju Kumar"
			Date asked: February 4, 2012
			Answered by: "Harminder"
			Date answered: February 4, 2012
			Date used: 3/24/2018
			Availability:
		 	 	- https://stackoverflow.com/questions/9139111/array-based-binary-tree-preorder
 	 	 
 	 	 Reference 3: Finding subtree depth:
	 	 	Title: "How to calculate the depth of a binary search tree" 
			Asked by: "Jon"
			Date asked: December 9, 2009
			Answered by: "Soumya Kanti Naskar"
			Date answered: August 8, 2016
			Date used: 3/24/2018
			Availability:
		 	 	- https://stackoverflow.com/questions/9139111/array-based-binary-tree-preorder
		 	
		 Reference 4: Binary Search Tree Info
			Title: "Binary Search Tree | Set 1 (Search and Insertion)"
		    Publisher: GeeksforGeeks
		    Author: Ankur Narain Verma
		    Date used: 3/26/2018
			Availability:
		 	 	- https://www.geeksforgeeks.org/binary-search-tree-set-1-search-and-insertion/	
	 * 
 * @author David Moussalli
 *
 */
public class Main {

	/** Main method
	 * 
	 * @param args - command line arguments
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
//Part A:
		long startTime, stopTime, avgSearchTime=0, numSearches=0;
		
		String filename = "hw4cs3310_davidMoussalli_Output.txt"; //Output file
		PrintWriter outputStream = new PrintWriter(filename);
		
		File firstFile = new File(args[0]);		//Create 2 File and Scanner objects to read the two 
		File secondFile = new File(args[1]);	//	files (provided via comman line arguments) into arrays.
		Scanner fileScanner1 = new Scanner(firstFile);
		Scanner fileScanner2 = new Scanner(secondFile);		
		
		String[] firstArray = new String[countFileLines(firstFile)]; 
		String[] secondArray = new String[countFileLines(secondFile)]; 
		
		firstArray = fillArray(firstArray, fileScanner1); //Fill firstArray with data from fileScanner1
		secondArray = fillArray(secondArray, fileScanner2); //Fill secondArray with data from fileScanner2
		
		//Close the file scanners after they are finished being used.
		fileScanner1.close(); 
		fileScanner2.close();

		
//Part B:
		System.out.println("Min heap:");
		outputStream.println("Min heap:");
		MinHeap minHeap = new MinHeap(firstArray, outputStream);	//Create minHeap 
		
		System.out.println("\tAverage insert time: " + minHeap.getAvgInsertTime() + " nanoseconds.");
		outputStream.println("\tAverage insert time: " + minHeap.getAvgInsertTime() + " nanoseconds.");
		
		System.out.print("\tPreorder traversal:\n\t\t");
		outputStream.print("\tPreorder traversal:\n\t\t");
		minHeap.printPreOrder(0);	//Print the preorder traversal of the heap
		
		System.out.println("\n\tMin: " + minHeap.getMinimum() + " Max: " + minHeap.getMaximum()); //Print the min and max nodes
		outputStream.println("\n\tMin: " + minHeap.getMinimum() + " Max: " + minHeap.getMaximum());
		
		for(int i=0; i<secondArray.length; i++) { //Search for all names in secondArray
			System.out.println("\tSearching for \""+ secondArray[i] +"\" ..."); 
			outputStream.println("\tSearching for \""+ secondArray[i] +"\" ...");
			startTime = System.nanoTime();
			if(minHeap.searchForName(secondArray[i], 0) == false) {
				stopTime = System.nanoTime();
				System.out.println("\t\tItem not found.");
				outputStream.println("\t\tItem not found.");
			}
			else {
				stopTime = System.nanoTime();
				System.out.println("\t\tTime to find: " + (stopTime-startTime) + " nanoseconds.");
				outputStream.println("\t\tTime to find: " + (stopTime-startTime) + " nanoseconds.");
			}
			avgSearchTime += (stopTime-startTime); //Add all search times together
			numSearches++;	//Count number of searches
		}//End for
		avgSearchTime /= numSearches; //Calculate average search time
		//Print average times:
		System.out.println("\tMax value search time: "+ minHeap.getMaxSearchTime() +" nanoseconds.");
		System.out.println("\tMin search time: "+ minHeap.getMinSearchTime() +" nanoseconds."); 
		System.out.println("\tAverage search time: "+ avgSearchTime +" nanoseconds."); 
		outputStream.println("\tMax value search time: "+ minHeap.getMaxSearchTime() +" nanoseconds.");
		outputStream.println("\tMin search time: "+ minHeap.getMinSearchTime() +" nanoseconds.");
		outputStream.println("\tAverage search time: "+ avgSearchTime +" nanoseconds.");
	
		
//Part C:
		System.out.println("\nMax heap:");
		outputStream.println("\nMax heap:");
		MaxHeap maxHeap = new MaxHeap(firstArray, outputStream);	//Create maxHeap 
		
		System.out.println("\tAverage insert time: " + maxHeap.getAvgInsertTime() + " nanoseconds.");
		outputStream.println("\tAverage insert time: " + maxHeap.getAvgInsertTime() + " nanoseconds.");
		
		System.out.print("\tPostorder traversal:\n\t\t");
		outputStream.print("\tPostorder traversal:\n\t\t");
		maxHeap.printPostorder(maxHeap.root); 	//Print the postorder traversal of the heap
		
		System.out.println("\n\tMin: " + maxHeap.getMinimum(maxHeap.root) + " Max: " + maxHeap.getMaximum()); //Print the min and max nodes
		outputStream.println("\n\tMin: " + maxHeap.getMinimum(maxHeap.root) + " Max: " + maxHeap.getMaximum());

		for(int i=0; i<secondArray.length; i++) { //Search for all names in secondArray
			System.out.println("\tSearching for \""+ secondArray[i] +"\" ..."); 
			outputStream.println("\tSearching for \""+ secondArray[i] +"\" ...");
			startTime = System.nanoTime();
			if(maxHeap.searchForName(secondArray[i], maxHeap.root) == false) {
				stopTime = System.nanoTime();
				System.out.println("\t\tItem not found.");
				outputStream.println("\t\tItem not found.");
			}
			else {
				stopTime = System.nanoTime();
				System.out.println("\t\tTime to find: " + (stopTime-startTime) + " nanoseconds.");
				outputStream.println("\t\tTime to find: " + (stopTime-startTime) + " nanoseconds.");
			}
			avgSearchTime += (stopTime-startTime); //Add all search times together
			numSearches++;	//Count number of searches
		}//End for
		avgSearchTime /= numSearches; //Calculate average search time
		//Print average times:
		System.out.println("\tMax value search time: "+ maxHeap.getMaxSearchTime() +" nanoseconds."); 
		System.out.println("\tMin search time: "+ maxHeap.getMinSearchTime() +" nanoseconds."); 
		System.out.println("\tAverage search time: "+ avgSearchTime +" nanoseconds."); 
		outputStream.println("\tMax value search time: "+ maxHeap.getMaxSearchTime() +" nanoseconds.");
		outputStream.println("\tMin search time: "+ maxHeap.getMinSearchTime() +" nanoseconds.");
		outputStream.println("\tAverage search time: "+ avgSearchTime +" nanoseconds.");
	
		
//Part C:
		System.out.println("\nBinary search tree:");
		outputStream.println("\nBinary search tree:");
		BinarySearchTree binarySearchTree = new BinarySearchTree(firstArray, outputStream);	//Create binarySearchTree 

		System.out.println("\tAverage insert time: " + binarySearchTree.getAvgInsertTime() + " nanoseconds.");
		outputStream.println("\tAverage insert time: " + binarySearchTree.getAvgInsertTime() + " nanoseconds.");
		
		System.out.print("\tInorder traversal:\n\t\t");
		outputStream.print("\tInorder traversal:\n\t\t");
		binarySearchTree.printInorder(binarySearchTree.root);  	//Print the inorder traversal of the heap
		
		System.out.println("\n\tMin: " + binarySearchTree.getMinimum() + " Max: " + binarySearchTree.getMaximum()); //Print the min and max nodes
		outputStream.println("\n\tMin: " + binarySearchTree.getMinimum() + " Max: " + binarySearchTree.getMaximum());
		
		for(int i=0; i<secondArray.length; i++) { //Search for all names in secondArray
			System.out.println("\tSearching for \""+ secondArray[i] +"\" ..."); 
			outputStream.println("\tSearching for \""+ secondArray[i] +"\" ...");
			startTime = System.nanoTime();
			if(binarySearchTree.searchForName(secondArray[i], binarySearchTree.root) == null) {
				stopTime = System.nanoTime();
				System.out.println("\t\tItem not found.");
				outputStream.println("\t\tItem not found.");
			}
			else {
				stopTime = System.nanoTime();
				System.out.println("\t\tTime to find: " + (stopTime-startTime) + " nanoseconds.");
				outputStream.println("\t\tTime to find: " + (stopTime-startTime) + " nanoseconds.");
			}
			avgSearchTime += (stopTime-startTime); //Add all search times together
			numSearches++;	//Count number of searches
		}//End for
		avgSearchTime /= numSearches; //Calculate average search time
		//Print average times:
		System.out.println("\tMax value search time: "+ binarySearchTree.getMaxSearchTime() +" nanoseconds."); 
		System.out.println("\tMin search time: "+ binarySearchTree.getMinSearchTime() +" nanoseconds."); 
		System.out.println("\tAverage search time: "+ avgSearchTime +" nanoseconds."); 
		outputStream.println("\tMax value search time: "+ binarySearchTree.getMaxSearchTime() +" nanoseconds.");
		outputStream.println("\tMin search time: "+ binarySearchTree.getMinSearchTime() +" nanoseconds.");
		outputStream.println("\tAverage search time: "+ avgSearchTime +" nanoseconds.");
		
//Part D:
		Random rand = new Random();
		int n=rand.nextInt(900)+101;
		String[] partDArray = new String[n];
		long overallAvgBSTInsert = 0,
			 overallAvgMinHeapInsert = 0,
			 overallAvgMaxHeapInsert = 0;
		
		System.out.println("\nSize of n: " + partDArray.length);
		outputStream.println("\nSize of n: " + partDArray.length);
		for(int i=0; i<partDArray.length; i++) {
			partDArray[i] = firstArray[rand.nextInt(firstArray.length)];
		}
		startTime=System.nanoTime();
		
		for(int i=0; i<n; i++) {
			MinHeap minHeap2 = new MinHeap(partDArray, outputStream);	//Create MinHeap 
			MaxHeap maxHeap2 = new MaxHeap(partDArray, outputStream);	//Create MaxHeap 
			BinarySearchTree binarySearchTree2 = new BinarySearchTree(partDArray, outputStream);
			overallAvgBSTInsert += binarySearchTree2.getAvgInsertTime();
			overallAvgMinHeapInsert += minHeap2.getAvgInsertTime();
			overallAvgMaxHeapInsert += maxHeap2.getAvgInsertTime();
		}
		stopTime=System.nanoTime();
		System.out.println("\nTime to create and sort " + n + " min heaps, max heaps, and BSTs, each with " + n + " names:\n\t"
				+ (stopTime-startTime) + " nanoseconds (" +(float)(stopTime-startTime)/1000000000 +" seconds)");
		
		System.out.println("Min heap average insert time with " + n + " names in each:\n\t"
				+ overallAvgBSTInsert/n + " nanoseconds.");
		System.out.println("Max heap average insert time with " + n + " names in each:\n\t"
				+ overallAvgMinHeapInsert/n + " nanoseconds.");
		System.out.println("BST average insert time with " + n + " names in each:\n\t"
				+ overallAvgMaxHeapInsert/n + " nanoseconds.");
		
		outputStream.println("\nTime to create and sort " + n + " min heaps, max heaps, and BSTs, each with " + n + " names:\n\t"
				+ (stopTime-startTime) + " nanoseconds (" +(float)(stopTime-startTime)/1000000000 +" seconds)");
		outputStream.println("Min heap average insert time with " + n + " names in each:\n\t"
				+ overallAvgBSTInsert/n + " nanoseconds.");
		outputStream.println("Max heap average insert time with " + n + " names in each:\n\t"
				+ overallAvgMinHeapInsert/n + " nanoseconds.");
		outputStream.println("BST average insert time with " + n + " names in each:\n\t"
				+ overallAvgMaxHeapInsert/n + " nanoseconds.");
		
		outputStream.close();
	}//End of main method
	
	
	/** Counts the number of lines in the provided file
	 * 
	 * @param file - File object.
	 * @return returns the number of lines in the file.
	 * @throws FileNotFoundException
	 */
	public static int countFileLines(File file) throws FileNotFoundException {
		Scanner fileCounter = new Scanner(file);
		int numLines=0;
		
		while(fileCounter.hasNextLine()) {
			numLines++;
			fileCounter.nextLine();
		}
		
		fileCounter.close(); //Close the line counter file
		return numLines;
	}//End of countFileLines
	
	/** This method fills the given array with all data read in from the scanner.
	 * 
	 * @param array - array to read values into
	 * @param scan - scanner to read values in from
	 * @return returns the array filled with the scanner data
	 */
	public static String[] fillArray(String[] array, Scanner scan) {
		for(int i=0; scan.hasNextLine(); i++){
			array[i] = scan.nextLine();
		}
		return array;
	}//End of fillArray
	
	/** Prints all data from the provided array.
	 * 
	 * @param array - array to print data from
	 */
	public static void printArray(String[] array) {
		for(int i=0; i<array.length; i++) {
			System.out.println(i+ "\t" + array[i]);
		}
	}

}//End of Class Main


