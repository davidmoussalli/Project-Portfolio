package hw2cs3310_moussalli_021218;
import java.io.*;
import java.util.*;


/** 
 * Class: 		CS 3310 
 * Assignment 	2
 * Date:		2/17/2018
 * @author 		David Moussalli
 * 
 * This program implements Stacks and Queues, using both Linked Lists and Arrays,
 * 		to convert decimal integers into binary and vice versa.
 * The user enters the number of times to run the program and it goes through a loop
 * 	which calls a method that creates different types of data structures, each of which
 * 	all do the same thing...Convert a decimal integer to binary and back again.
 * The program calculates the individual time and average time for each structure
 * 	to work and displays it to the user. If the number of iterations is less
 * 	than 500, the program will display detailed output for each structure; if number
 *  of iterations is greater than or equal 500, it will only output the average times 
 *  for each data structure.
 * 
 * 
 * REFERENCES: 
 * 		Recalling how Linked Lists, Stacks, and Queues work:
		 	1.	Title:	"Linked List in Java"
		 	Channel: "Derek Banas"
		 	Published: March 3, 2013
		 	Date used: Saturday, February 17, 2018
		 	Availability:
		 	 	- https://www.youtube.com/watch?v=195KUinjBpU&ab_channel=DerekBanas
		 	
			2.	Title:  "Stacks and Queues"
		 	Channel: "Derek Banas"
		 	Published: March 1, 2013
		 	Date used: Saturday, February 17, 2018
		 	Availability:
		 	 	- https://www.youtube.com/watch?v=JvGZh_BdF-8&ab_channel=DerekBanas
		 	
 */
public class Main {
	static Random rand = new Random();	//Used to generate random integers
	static int n;						//Number of iterations
	static long startTime,
				stopTime,
				totalTime,				//stopTime - startTime
				
				avgLLStackTime=0,		//Average time to use the Linked List Stack
				avgAStackTime=0,		//Average time to use the Array Stack
				avgLLQueueTime=0,		//Average time to use the Linked List Queue
				avgAQueueTime=0;		//Average time to use the Array Queue
	
	/**	main()
	 * 
	 * @param args - string array of command line arguments.
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		String filename = "hw2cs3310_davidMoussalli.txt";
		PrintWriter outputStream = new PrintWriter(filename);
		Scanner keyboard = new Scanner(System.in);
		
		try {//Try statement is used to test user input
			System.out.print("n = ");
			n = keyboard.nextInt();
			outputStream.println("n = " + n);
			for(int i=0; i<n; i++) { //Go through the loop n times
				int num = rand.nextInt(1999999)+1; //Create random in between 1-2,000,000
				if(n<500) { 
					doProcess(num, n, i, outputStream); //If n< 500, go through the program with detailed output...
				}
				else {
					doAvgProcess(num, outputStream); 	 //Else, go through the program without detailed output...
				}
			}
			outputStream.println("\n\nAverage time for LL-based stack: " + (avgLLStackTime / n) + " ns");	//Output the averages for each data structure.
			outputStream.println("Average time for Array-based stack: " + (avgAStackTime / n) + " ns");
			outputStream.println("Average time for LL-based queue: " + (avgLLQueueTime / n) + " ns");
			outputStream.println("Average time for Array-based queue: " +(avgAQueueTime / n) + " ns");
			
		}
		catch(Exception e) {//Created for InputMismatchExceptions, but will show any caught exceptions
			outputStream.println("Error: " +e + "\nExiting Program.");
		}
		outputStream.close();
		keyboard.close();//Close the keyboard scanner
	}
	
	
	
	/**This method creates an object for each of the four different data structure 
	 * 		classes (MyLLStack, MyAStack, MyLLQueue, and MyAQueue).
	 * Once created, this method pushes/ enqueues the "num" integer into the
	 * 		data structures, where it will then be changed into a binary integer
	 * 		and back to a decimal.
	 * The method outputs the decimal number, its binary equivalent, the time taken 
	 * 		to use the data structure, and it adds the time taken to the average time
	 * 		for that data structure.
	 * 
	 * @param num - the random number being pushed/enqueued into the Linked Lists/ Queues
	 * @param n - total number of iterations that will execute
	 * @param i - current iteration 
	 */
	private static void doProcess(int num, int n, int i, PrintWriter outputStream) {
		outputStream.println("\n__LL-based Stack__\n");
		MyLLStack LLStack = new MyLLStack();
		
		startTime = System.nanoTime();
		LLStack.push(num);
		outputStream.println("Decimal: " + LLStack.toDecimal());
		outputStream.println("Binary: " + LLStack.showStack());			
		stopTime = System.nanoTime();
		totalTime = (stopTime - startTime);
	avgLLStackTime += totalTime;
		outputStream.println("Time taken: " + totalTime + " ns");
		
//=====================================================================================
		outputStream.println("\n__Array-based Stack__\n");
		MyAStack AStack = new MyAStack();
		
		startTime = System.nanoTime();
		AStack.store(num);
		outputStream.println("Decimal: " + AStack.toDecimal());
		outputStream.println("Binary: " + AStack.showStack());
		stopTime = System.nanoTime();
		totalTime = (stopTime - startTime);
	avgAStackTime += totalTime;
		outputStream.println("Time taken: " + totalTime + " ns");
		
//=====================================================================================
		outputStream.println("\n__LL-based Queue__\n");
		MyLLQueue LLQueue = new MyLLQueue();
		
		startTime = System.nanoTime();
		LLQueue.enqueue(num);
		outputStream.println("Decimal: " + LLQueue.toDecimal());
		outputStream.println("Binary: " + LLQueue.showQueue());			
		stopTime = System.nanoTime();
		totalTime = (stopTime - startTime);
	avgLLQueueTime += totalTime;
		outputStream.println("Time taken: " + totalTime + " ns");
		
//=====================================================================================
		outputStream.println("\n__Array-based Queue__\n");
		MyAQueue AQueue = new MyAQueue();
		
		startTime = System.nanoTime();
		AQueue.enqueue(num);
		outputStream.println("Decimal: " + AQueue.toDecimal());
		outputStream.println("Binary: " + AQueue.showQueue());
		stopTime = System.nanoTime();
		totalTime = (stopTime - startTime);
	avgAQueueTime += totalTime;
		outputStream.println("Time taken: " + totalTime + " ns");

		
//=====================================================================================
		if(i<n-1) { //Don't print dashes on the last iteration.
			outputStream.println("\n------");
		}
	}//End of doProcess()
	
	
	
	/**This method does the exact same thing as doProcess(), except with no output.
	 * It will be called if the user wants to have more than 500 iterations. 
	 * 
	 * @param num - the random number being pushed/enqueued into the Linked Lists/ Queues
	 */
	private static void doAvgProcess(int num, PrintWriter outputStream) {
//LL-based Stack
		MyLLStack LLStack = new MyLLStack();
		startTime = System.nanoTime();
		LLStack.push(num);	
		stopTime = System.nanoTime();
		totalTime = (stopTime - startTime);
	avgLLStackTime += totalTime;
		
//=====================================================================================
//Array-based Stack
		MyAStack AStack = new MyAStack();
		startTime = System.nanoTime();
		AStack.store(num);
		stopTime = System.nanoTime();
		totalTime = (stopTime - startTime);
	avgAStackTime += totalTime;
		
//=====================================================================================
//LL-based Queue
		MyLLQueue LLQueue = new MyLLQueue();
		startTime = System.nanoTime();
		LLQueue.enqueue(num);		
		stopTime = System.nanoTime();
		totalTime = (stopTime - startTime);
	avgLLQueueTime += totalTime;
		
//=====================================================================================
//Array-based Queue
		MyAQueue AQueue = new MyAQueue();
		startTime = System.nanoTime();
		AQueue.enqueue(num);
		stopTime = System.nanoTime();
		totalTime = (stopTime - startTime);
	avgAQueueTime += totalTime;
	}//End of doAvgProcess()

}
