package pack;
import java.io.*;
import java.util.*;

/**
 * Class: 		CS 4310 
 * Assignment 	4
 * Date:		11/7/2018
 * @author 		David Moussalli

 * ===========================================================================
 * I GIVE PERMISSION TO THE INSTRUCTOR TO SHARE MY SOLUTION(S) WITH THE CLASS.
 * ===========================================================================
 * 
 * This program implements an adjacency list data structure to find the minimum necessary 
 * 	tables required to seat guests at a wedding. Guests will only be seated at tables 
 * 	with people that they know. As long as a guest knows one person at the table, they
 * 	will be seated there.
 * 
 * The purpose of this program is to use our knowledge of data structures to create a program 
 *  that efficiently solves a problem that could happen in the real world. We are to use common 
 *  sense and general intuition alongside our computer science skills to create the most efficent 
 *  algorithm to get the job done.
 *  
 * My program uses an adjacency list to find unique friendships, then subtract the unique friendships
 *  from the total number of guests. This algorithm will not work if there is a maximum number of 
 *  people that can sit at a table, but for this description, there is no limit of how many
 *  guests can be seated at one table. 
 *
 * Provided input file: input.txt
  
 * ============================= REFERENCES: =========================================
	 	Reference 1: Adjacency List Info
			Title: "Graph and its representations"
		    Publisher: GeeksforGeeks
		    Author: Sumit Ghosh
		    Date used: 11/9/2018
			Availability:
		 	 	- https://www.geeksforgeeks.org/graph-and-its-representations/
 
 * @author David Moussalli
 */
public class Main {

	/** Main method
	 * 
	 * @param args - command line arguments
	 * @throws FileNotFoundException - If the input file is not found, throw an exception.
	 */
	public static void main(String[] args) {
		Scanner kb = new Scanner(System.in);
		System.out.println("Please enter the filename:");
		String filename = kb.nextLine();
		System.out.println("filename: "+ filename);
		
		seatGuests(filename);

	} //End of main method

//=================================== User-Defined Functions ======================================================================

	/** seatGuests method - See Reference 1.
	 * 
	 * @param fname
	 */
	public static void seatGuests(String fname) {

		String filename = fname;
		try { //Start of try
			File file = new File(filename);
			Scanner scanner = new Scanner(file);
		
			int nTestCases = scanner.nextInt(); //Number of test cases
			for(int i=0; i<nTestCases; i++) { //Start of T loop																		O(T)
				int nFriends = scanner.nextInt(); //Number of friends
				List<Integer> adjList[] = new LinkedList[nFriends]; //Creation of the adjacency list.
				for(int j=0; j<nFriends; j++) { //Initialize all linked lists in the adjacency list.									O(n)
					adjList[j] = new LinkedList<>();
				}
				
				int mLines = scanner.nextInt(); //Number of lines in this test case
				for(int j=0; j<mLines; j++) { //mLines Loop 																			O(m)
					int friend = scanner.nextInt();
					int knowsFriend = scanner.nextInt();
					if(friend != knowsFriend) { //Check if frinend and knowsFriend are the same person
						checkDuplicateFriendship(adjList, friend, knowsFriend); //Check for duplicate friendship							O(2*logn)
					}
				} //End mLines Loop
				
				int nTables = nFriends; //Number of tables necessary is initialized to the number of friends.
				for(int j=0; j<nFriends; j++) { //Loop - Calculate number of tables needed												O(n)					
					if(!adjList[j].isEmpty()) { //If person j knows somebody,
						adjList[j].remove(0); //remove that friendship
						if(nTables>1) nTables--; //and subtract one table
					}
				} //End Calculate tables Loop
				System.out.println(nTables); //Print the minimum number of necessary tables to seat guests
			} //End of T loop
			scanner.close(); //Close the file scanner
		}//End of try
		catch(FileNotFoundException e){
			System.out.println("File not found.");
		}
	}
	
	/** This method checks if a friendship has already been added to the adjacency list.
	 * 	If the relationship is not a duplicate, it is added to the adjacency list.
	 * 
	 * @param adjList - Adjacency list of friendships
	 * @param friend  - Friend
	 * @param knowsFriend - Person that "friend" knows
	 */
	public static void checkDuplicateFriendship(List<Integer> adjList[], int friend, int knowsFriend) {
		if(!adjList[friend-1].contains(knowsFriend) && !adjList[knowsFriend-1].contains(friend)) { //Check for duplicate friendship
			adjList[friend-1].add(0,knowsFriend); //Add relationship to the adjacency list.
		} //Else do not add it to the adjacency list...
	} //End of checkDuplicateFriendship method
} //End of class Main



