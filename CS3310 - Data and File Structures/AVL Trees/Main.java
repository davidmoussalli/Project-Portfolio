package pack;
import java.io.*;
import java.math.BigInteger;
import java.util.*;


/**
 * Class: 		CS 3310 
 * Assignment 	5
 * Date:		4/17/2018
 * @author 		David Moussalli
 * 
 * 
 * I apologize for not having the program description done and for
 * 	having somewhat brief documentation, it is getting very close to the 
 * 	deadline and I must focus on finishing the assignment.
 * 
 * I forgot to implement only printing the trees if n<=250...Sorry about that. 
 * 	Because of this, I recommend only using the tiny file, or file that is 
 * 	smaller than data_main.csv. 
 * 
 * REFERENCES: 
 * 		Reference 1: AVL Tree basics:
	 		Title:	"26.2. The AVL Tree"
	 		Published by: Canvas by Instructure, Inc.
		 	Date used: April 14, 2018
  			Availability:
 	 			- https://canvas.instructure.com/courses/1266618/modules/items/15561045
 	 	
 	 	Reference 2:
 	 		2A: (Different Rotations)
			 	Title:	"Balanced binary search tree rotations"
			 	Channel: "WilliamFiset"
			 	Published: November 12, 2017
			 	Date used: April 14, 2018
			 	Availability:
			 	 	- https://www.youtube.com/watch?v=q4fnJZr8ztY&ab_channel=WilliamFiset
			
			2B: (Insertion)
				Title: "AVL tree insertion"
			    Channel: "WilliamFiset"
			 	Published: November 12, 2017
			 	Date used: April 14, 2018
			 	Availability:
			 	 	- https://www.youtube.com/watch?v=1QSYxIKXXP4&ab_channel=WilliamFiset
			 	 	
			2C: (Removal)
				Title: "AVL tree removals"
			    Channel: "WilliamFiset"
			 	Published: November 12, 2017
			 	Date used: April 14, 2018
			 	Availability:
			 	 	- https://www.youtube.com/watch?v=g4y2h70D6Nk&ab_channel=WilliamFiset
			 	 	
			2D: 
				Title: "AVL tree source code"
			    Channel: "WilliamFiset"
			 	Published: November 12, 2017
			 	Date used: April 14, 2018
			 	Availability:
			 	 	- https://www.youtube.com/watch?v=tqFZzXkbbGY&ab_channel=WilliamFiset
			 	 	 	
		Reference 3: Breadth first search
		 	Title:	"Java breadth first and depth first search in 5 min"
		 	Channel: "Gaur Associates"
		 	Published: February 22, 2017
		 	Date used: April 17, 2018
		 	Availability:
		 	 	- https://www.youtube.com/watch?v=Kfm00t4YKow&ab_channel=GaurAssociates 
			 	
"*/
public class Main{
	static PrintWriter outputStream;
	public static void main(String[] args) throws FileNotFoundException {
		outputStream = new PrintWriter("hw5cs3310_davidMoussalli_Output.txt");
		//Timers:
		long startTime=0, stopTime=0, 
			avgSNumTimer=0,				//Average time to insert an object into the hash table
			avgModelTimer=0,				//Average Quick Sort search time
			avgCapacityTimer=0,			//Average Merge Sort search time
			avgHoursTimer=0;				//Average Heap Sort search time
		
		//Counters:
		int counter=0;
		Random rand = new Random();	//Random variable for later use in part B
		
		HDTestData[] array;		//Create the array of HDTestData objects

println("=============== Part A ==============");
//================================ Part A ================================
		File csvFile = new File("data_tiny.csv");	//Create a file object
		Scanner csvScanner = new Scanner(csvFile);  //Create a csv file scanner
		Scanner csvCounter = new Scanner(csvFile);	//Create a csv file scanner to count the lines in the file.
		csvScanner.nextLine(); 	//This line is here so we don't use the header line.
		csvCounter.nextLine();
		
		while(csvCounter.hasNextLine()) { 	//Count the lines in the file
			csvCounter.nextLine();
			counter++;
		}
		csvCounter.close();		//Close the counter file
		array = new HDTestData[counter];	//Create array...
		
		AVLTree<String> tree1 = new AVLTree<String>(); 			//Create AVLTree object with type String
		AVLTree<String> tree2 = new AVLTree<String>(); 			//Create AVLTree object with type String
		AVLTree<BigInteger> tree3 = new AVLTree<BigInteger>(); 	//Create AVLTree object with type BigInteger
		AVLTree<Integer> tree4 = new AVLTree<Integer>(); 		//Create AVLTree object with type Integer
		
		for(int i=0; csvScanner.hasNextLine(); i++){ //Fill the array with data from the file
			String line = csvScanner.nextLine();	 //Read next line
			array[i] = new HDTestData(line);		 //Put it into the array
			
			String[] array1 = line.split(",");		 //Split the line into the different data items
			
			//Insert each item into their associated tree
			tree1.insert(array1[0]);
			tree2.insert(array1[1]);
			tree3.insert(new BigInteger(array1[2]));
			tree4.insert(Integer.parseInt(array1[3]));
		}
		csvScanner.close();	//Close the Scanner after loading the data.

		println("\n======= Depth-first: =======");
		println("\tSorted by serial_number:");
		tree1.printDepthFirst(tree1.root, outputStream);
		
		println("\n\n\tSorted by model:");
		tree2.printDepthFirst(tree2.root, outputStream);
		
		println("\n\n\tSorted by capacity_bytes:");
		tree3.printDepthFirst(tree3.root, outputStream);
		
		println("\n\n\tSorted by power_on_hours:");
		tree4.printDepthFirst(tree4.root, outputStream);
		println("");
		

		println("\n======= Breadth-first: =======");
		println("\tSorted by serial_number:");
		tree1.printBreadthFirst(tree1.root, outputStream);
		
		println("\n\n\tSorted by model:");
		tree2.printBreadthFirst(tree2.root, outputStream);
		
		println("\n\n\tSorted by capacity_bytes:");
		tree3.printBreadthFirst(tree3.root, outputStream);
		
		println("\n\n\tSorted by power_on_hours:");
		tree4.printBreadthFirst(tree4.root, outputStream);
		println("");
		
println("=============== Part B ==============");
//================================ Part B ================================
//1) - Reading file:
		HDTestData[] array2;		//Create the array of HDTestData objects
		
		File csvFile2 = new File("data_main.csv");	//Create a file object
		Scanner csvScanner2 = new Scanner(csvFile);  //Create a csv file scanner
		Scanner csvCounter2 = new Scanner(csvFile);	//Create a csv file scanner to count the lines in the file.
		csvScanner2.nextLine(); 	//This line is here so we don't use the header line.
		csvCounter2.nextLine();
		counter=0; 	//Initialize the counter to 0
		
		while(csvCounter2.hasNextLine()) { 	//Count the lines in the file
			csvCounter2.nextLine();
			counter++;
		}
		csvCounter2.close();		//Close the counter file
		array2 = new HDTestData[counter];	//Create array...
		
		for(int i=0; csvScanner2.hasNextLine(); i++){ //Fill the array with data from the file
			String line = csvScanner2.nextLine();	 //Read next line
			array2[i] = new HDTestData(line);		 //Put it into the array
		}
		csvScanner2.close(); //Close the Scanner after loading the data.
		
		AVLTree<String> tree5 = new AVLTree<String>(); 			//Create AVLTree object with type String
		AVLTree<String> tree6 = new AVLTree<String>(); 			//Create AVLTree object with type String
		AVLTree<BigInteger> tree7 = new AVLTree<BigInteger>(); 	//Create AVLTree object with type BigInteger
		AVLTree<Integer> tree8 = new AVLTree<Integer>(); 		//Create AVLTree object with type Integer

//3) - Inserting n objects:		
int n=1000;	//Set n here **********************
println("Inserting " + n + " items...");
		for(int i=0; i<n; i++) { //Add n random items from the array into the AVL trees.
			HDTestData item = array2[rand.nextInt(array2.length)]; //Get random item from the array
			
			//Insert each attribute of the item into their associated tree
			startTime = System.nanoTime(); 		 //Start timer
		tree5.insert(item.getSerialNum());
			stopTime = System.nanoTime(); 		 //Stop timer
			avgSNumTimer+= stopTime-startTime; //Add time to the total insert time
			
			startTime = System.nanoTime(); 		 //Start timer
		tree6.insert(item.getModelNum());
			stopTime = System.nanoTime(); 		 //Stop timer
			avgModelTimer+= stopTime-startTime;  //Add time to the total insert time
			
			startTime = System.nanoTime(); 		 //Start timer
		tree7.insert(new BigInteger(item.getCapacity()));
			stopTime = System.nanoTime(); 		 //Stop timer
			avgCapacityTimer+= stopTime-startTime;  //Add time to the total insert time
			
			startTime = System.nanoTime(); 		 //Start timer			
		tree8.insert(Integer.parseInt(item.getHours()));
			stopTime = System.nanoTime(); 		 //Stop timer
			avgHoursTimer+= stopTime-startTime;  //Add time to the total insert time
		}
		//Divide the total times by the total number of nodes inserted (n)
		avgSNumTimer /= n;
		avgModelTimer /= n;
		avgCapacityTimer /= n;
		avgHoursTimer /= n;
		
		System.out.println("\n\tAverage serial number insertion time:\t" + avgSNumTimer + " nanoseconds.");
		System.out.println("\tAverage model insertion time:\t\t" + avgModelTimer + " nanoseconds.");
		System.out.println("\tAverage capacity insertion time:\t" + avgCapacityTimer + " nanoseconds.");
		System.out.println("\tAverage hours insertion time:\t\t" + avgHoursTimer + " nanoseconds.");
		
		outputStream.println("\n\tAverage serial number insertion time:\t" + avgSNumTimer + " nanoseconds.");
		outputStream.println("\tAverage model insertion time:\t\t\t" + avgModelTimer + " nanoseconds.");
		outputStream.println("\tAverage capacity insertion time:\t\t" + avgCapacityTimer + " nanoseconds.");
		outputStream.println("\tAverage hours insertion time:\t\t\t" + avgHoursTimer + " nanoseconds.");
		
		
		
		//Initialize each timer to 0
		avgSNumTimer = 0;
		avgModelTimer = 0;
		avgCapacityTimer = 0;
		avgHoursTimer = 0;
		
		println("------------");
//4) - Finding m objects:
int m = 10;		//Set m here **********************
		print("Searching for " + m + " items...");
		for (int i=0; i<m; i++) {
			HDTestData item = array2[rand.nextInt(array2.length)]; //Get random item from the array

			//Search for each attribute of the item in their associated tree
			print("\n\n" + (i+1) + ":\t");
			startTime = System.nanoTime(); 		 //Start timer
		tree5.search(tree5.root, item.getSerialNum(), outputStream);
			stopTime = System.nanoTime(); 		 //Stop timer
			avgSNumTimer+= stopTime-startTime; //Add time to the total find the first occurrence of time
			
			print("\n\t\t");
			startTime = System.nanoTime(); 		 //Start timer
		tree6.search(tree6.root, item.getModelNum(), outputStream);
			stopTime = System.nanoTime(); 		 //Stop timer
			avgModelTimer+= stopTime-startTime;  //Add time to the total find the first occurrence of time

			print("\n\t\t");
			startTime = System.nanoTime(); 		 //Start timer
		tree7.search(tree7.root, new BigInteger(item.getCapacity()), outputStream);
			stopTime = System.nanoTime(); 		 //Stop timer
			avgCapacityTimer+= stopTime-startTime;  //Add time to the total find the first occurrence of time

			print("\n\t\t");
			startTime = System.nanoTime(); 		 //Start timer		
		tree8.search(tree8.root, Integer.parseInt(item.getHours()), outputStream);
			stopTime = System.nanoTime(); 		 //Stop timer
			avgHoursTimer+= stopTime-startTime;  //Add time to the total find the first occurrence of time		
		}
		//Divide the total times by the total number of searches (m)
		avgSNumTimer /= m;
		avgModelTimer /= m;
		avgCapacityTimer /= m;
		avgHoursTimer /= m;
		
//5) - Average search times:
		System.out.println("\n\n------------"
				+ "\n\tAverage serial number search time:\t" + avgSNumTimer + " nanoseconds.");
		System.out.println("\tAverage model search time:\t\t" + avgModelTimer + " nanoseconds.");
		System.out.println("\tAverage capacity search time:\t\t" + avgCapacityTimer + " nanoseconds.");
		System.out.println("\tAverage hours search time:\t\t" + avgHoursTimer + " nanoseconds.");

		outputStream.println("\n\n------------"
				+ "\n\tAverage serial number search time:\t\t" + avgSNumTimer + " nanoseconds.");
		outputStream.println("\tAverage model search time:\t\t\t\t" + avgModelTimer + " nanoseconds.");
		outputStream.println("\tAverage capacity search time:\t\t\t" + avgCapacityTimer + " nanoseconds.");
		outputStream.println("\tAverage hours search time:\t\t\t\t" + avgHoursTimer + " nanoseconds.");

//6) - Deleting entire trees & print average times:
		startTime = System.nanoTime(); 		 //Start timer
		tree5.deleteTree(tree5.root);
		tree5.root = null;
		stopTime = System.nanoTime(); 		 //Stop timer
		avgSNumTimer+= stopTime-startTime; //Add time to the total insert time

		startTime = System.nanoTime(); 		 //Start timer
		tree6.deleteTree(tree6.root);
		tree6.root = null;
		stopTime = System.nanoTime(); 		 //Stop timer
		avgModelTimer+= stopTime-startTime; //Add time to the total insert time

		startTime = System.nanoTime(); 		 //Start timer
		tree7.deleteTree(tree7.root);
		tree7.root = null;
		stopTime = System.nanoTime(); 		 //Stop timer
		avgCapacityTimer+= stopTime-startTime; //Add time to the total insert time

		startTime = System.nanoTime(); 		 //Start timer
		tree8.deleteTree(tree8.root);
		tree8.root = null;
		stopTime = System.nanoTime(); 		 //Stop timer
		avgHoursTimer+= stopTime-startTime; //Add time to the total insert time

		//Divide the total times by the total number of nodes deleted (n)
		avgSNumTimer /= n;
		avgModelTimer /= n;
		avgCapacityTimer /= n;
		avgHoursTimer /= n;
		
		System.out.println("\n------------"
				+ "\n\tAverage serial number deletion time:\t" + avgSNumTimer + " nanoseconds.");
		System.out.println("\tAverage model deletion time:\t\t" + avgModelTimer + " nanoseconds.");
		System.out.println("\tAverage capacity deletion time:\t\t" + avgCapacityTimer + " nanoseconds.");
		System.out.println("\tAverage hours deletion time:\t\t" + avgHoursTimer + " nanoseconds.");
		
		outputStream.println("\n------------"
				+ "\n\tAverage serial number deletion time:\t" + avgSNumTimer + " nanoseconds.");
		outputStream.println("\tAverage model deletion time:\t\t\t" + avgModelTimer + " nanoseconds.");
		outputStream.println("\tAverage capacity deletion time:\t\t\t" + avgCapacityTimer + " nanoseconds.");
		outputStream.println("\tAverage hours deletion time:\t\t\t" + avgHoursTimer + " nanoseconds.");
		
		print("\nDone.");
		outputStream.close(); //Close the PrintWriter
	}//End of main

	
	
	
	
//"====================== User-Defined Dual Printing Methods ======================
	/** Prints the content to both the console and the outputStream
	 * 
	 * @param content
	 */
	private static void print(String content) {
		System.out.print(content);
		outputStream.print(content);
	}
	
	/** Prints the content to both the console and the outputStream
	 * 
	 * @param content
	 */
	private static void println(String content) {
		System.out.println(content);
		outputStream.println(content);
	}

}
