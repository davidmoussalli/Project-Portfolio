package hw1cs3310_moussalli_012518;

import java.io.*;
import java.util.*;

/**
 * CS 3310 - Data and File Structures
 * Assignment 1
 * @author David Moussalli
 * Date: 1/25/2018
 * 
 * 
 * This program is a simulation of the inventory system from the MMORPG, Guild Wars 2.
 * Items only consist of weapons.
 * The text file titled "A1_items.txt" has data for 750 different weapons in the game. There are 4 
 * 	columns which list a weapon's "Item name", "Min strength", "Max strength", and "Rarity".
 * The 750 weapons are read into a 2D String array called "weaponsArray"
 * 
 * The user enters the number of bags to populate and then the bags are completely filled with 
 *  random items from weaponsArray.
 * The "bags" are actually an array of Objects of the "Weapon" class called "weaponObjArray"
 * The Weapon class has 6 attributes: 
  		_weaponName:		Item name
		_minStrength:		Min strength
		_maxStrength:		Max Strength
		_rarity:			Rarity
		_currentStrength:	Current Strength (A random number between max and min strength)
 *
 * A random item is chosen from weaponsArray and then the program searches for any occurence of it 
 * 	in the weaponObjArray. Both the item name and the rarity must match. If found, the program reports
 * 	the bag number in which it was found, the position within the bag, and the item's current strength.
 * Also, it reports the time spent searching for the item.
 * 
 * The speed of the search algorithm is further tested. The previous process is repeated 10 times, and 
 * 	the average search time is reported. For this step, no output is printed, except for the average time.
 * 
 * The entire inventory is then sorted using a selection sort algorithm, then the program searches
 * 	for new random items (like before), using a binary search algorithm instead of a linear search.
 * 
 *
 *
 *
 * To run this program, simply press "run" and enter the number of bags (n).
 * 
 * This program works with an arbitrary number of bags.
 *
 *REFERENCES: 
	  Recalling how random numbers work in Java:
		1.	Title:  "Pick a random element from a string array? [duplicate]"
		 	Asked by: "Logger" on August 5, 2014
		 	Answered by: "Bohemian♦" on August 6, 2014
		 	Date used: Wednesday, January 24, 2018
		 	Availability:
		 	 	- https://stackoverflow.com/questions/25150199/pick-a-random-element-from-a-string-array
		 	 
		2.	Title:  "How do I generate random integers within a specific range in Java?"
		 	Asked by: "user42155" on December 12, 2008
		 	Answered by: "Greg Case" on December 12, 2008
		 	Date used: Wednesday, January 24, 2018
		 	Availability:
		 	 	- https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
	
	  Converting Strings to Integers and vice versa:
		3. 	Title: "Java – Convert String to int"
		 	Author: mkyong
		 	Published: June 1, 2015 | Updated : July 1, 2015
		 	Date used: Wednesday, January 24, 2018
		 	Availability:
		 	 	- https://www.mkyong.com/java/java-convert-string-to-int/
	 	 
	  Fixing an error when searching for the weapon:
		4.	Title:  "How do I compare strings in Java?"
		 	Asked by: "Nathan H" on February 4, 2009
		 	Answered by: "Aaron Maenpaa" on February 4, 2009
		 	Date used: Wednesday, January 24, 2018
		 	Availability:
	 	 		- https://stackoverflow.com/questions/513832/how-do-i-compare-strings-in-java
	 
	  Recalling how time works in Java:
		5. 	Title:  "How do I time a method's execution in Java?"
		 	Asked by: "Ogre Psalm33" on October 7, 2008
		 	Answered by: "Diastrophism" on October 7, 2008
		 	Date used: Wednesday, January 24, 2018
		 	Availability:
	 	 		- https://stackoverflow.com/questions/180158/how-do-i-time-a-methods-execution-in-java
	 
	  Binary search algorithm:
	  	6.	Title: "7.1.1.2. Binary Search"  (This reference is from Canvas and I'm sure who the author or publisher is.)
	  		Date used: Wednesday, January 25, 2018
	  		Availability:
	 	 		- https://canvas.instructure.com/courses/1266618/assignments/7759008
	 	
	 	7. 	Title: "Using Binary Search with sorted Array with duplicates"
			Asked by: "5StringRyan" on November 2, 2012
		 	Answered by: "Xiangyu Xu" on December 3, 2013
	 		Date used: Monday, January 29, 2018
	 		Availability:
	 			- https://stackoverflow.com/questions/13197552/using-binary-search-with-sorted-array-with-duplicates
	  
 */
public class Assignment1DMoussalli{
	/**
	 * The main method of the program. Opens the input file and fills a 2D String array with all the
	 * 	 	weapons. The program then asks the user how many bags they want, then fills an array of
	 * 		Weapon objects with random items taken from the String array. The program then continues
	 * 		completion 
	 * 
	 * @param args - Command line arguments
	 * @throws FileNotFoundException - Exception thrown when the filename cannot be found
	 */
	public static void main(String[] args) throws FileNotFoundException{
		
		int numWeapons=0;
		String[] temp;
		String filename = "A1_items.txt";
		Scanner keyboard = new Scanner(System.in);

		//Load the text file (only used to count numWeapons
		Scanner countLinesFile = new Scanner( new File(filename) );
		
		countLinesFile.nextLine();
		for(numWeapons=0; countLinesFile.hasNext(); numWeapons++){//This loop counts the numWeapons
			countLinesFile.nextLine();
		}
		countLinesFile.close();//Close the counter file
		
		//Load the text file (For use of data)
		Scanner itemsFile = new Scanner(new File(filename));
		String[][] weaponsArray = new String[numWeapons][5];

		
		itemsFile.nextLine(); // Skip the first line because it is just a description
		for(int i=0; i<numWeapons; i++){ //Load the weaponsArray with all the file entries 
			temp = itemsFile.nextLine().split(",");	//Takes one weapon entry....
			for(int j=0; j<4; j++){ 
				weaponsArray[i][j] = temp[j];	// ...and splits it into weapon information
			}
			weaponsArray[i][4] = "null";
 		}

//create n number bags, each with 20 item slots.
			System.out.print("How many bags? (Enter an integer): ");
			int nBags = keyboard.nextInt(); //Get input from user 
			System.out.println("\nn="+nBags);
			String[][][] bags = new String[nBags][20][5]; //Create 3d array, using nBags as the number of bags, each of which have 20 weapons, with 5 fields.
			Weapon[] weaponObjArray = new Weapon[nBags*20];
			
			for(int i=0; i<nBags; i++){ //Fill the bags with random items from the weaponsArray
				for(int j=0; j<20; j++){
					String tempWeapon[] = weaponsArray[new Random().nextInt(numWeapons)]; /** See reference 1 and 2 */
					bags[i][j] = tempWeapon;								//random.nextInt(max strength - min strength +1) + min strength
					bags[i][j][4] = Integer.toString((new Random().nextInt(Integer.parseInt(bags[i][j][2]) - Integer.parseInt(bags[i][j][1])) + 
							Integer.parseInt(bags[i][j][1]))); /** See reference 1, 2, and 3 */
					
					weaponObjArray[(i*20)+j] = new Weapon(tempWeapon);
					weaponObjArray[(i*20)+j].setCurStr(Integer.toString((new Random().nextInt(Integer.parseInt(weaponObjArray[(i*20)+j].getMaxStr()) - 
							Integer.parseInt(weaponObjArray[(i*20)+j].getMinStr())) + Integer.parseInt(weaponObjArray[(i*20)+j].getMinStr()))));
					weaponObjArray[(i*20)+j].setBagNum(i);//Set bag number so you know which bag you are in.
				}
			}//End of fill bag loop

			System.out.println("Bags before sorting:");
			printItems(nBags, bags, weaponObjArray);
			
			linearSearch(weaponsArray, bags, numWeapons, weaponObjArray); //Call search method to see if a random weapon exists in the bags.
			linearSearchNoOutput(weaponsArray, bags, numWeapons, weaponObjArray); //Sane as linearSearch(), but displays no output.
			bags = Sort(bags, weaponObjArray);//Method that sorts bags

			System.out.println("\nBags after sorting:");
			printItems(nBags, bags, weaponObjArray);

			binarySearch(weaponsArray, numWeapons, weaponObjArray);
			binarySearchNoOutput(weaponsArray, numWeapons, weaponObjArray);

		keyboard.close();//Close the keyboard scanner.
		itemsFile.close();//Close the items file.
		System.out.println("\nDone.");
	}//End of Main()
	
	
	/**
	 * Prints all items in your inventory in the correct format
	 * 
	 * @param nBags - number of bags
	 * @param bags - 3d String array of inventory items	
	 * @param weaponObjArray - Array of Weapon objects
	 */
	 private static void printItems(int nBags, String[][][] bags, Weapon[] weaponObjArray) {
		 if(nBags <= 8){
			for(int g=0; g<bags.length; g++){//Print bags loop
				System.out.printf("Bag %d:\n", g+1);
				for(int i=0; i<5; i++){//Print out all the items in the bag.
					System.out.println((i+1) + "\t" + weaponObjArray[(g*20)+i].getRarity() + " " + weaponObjArray[(g*20)+i].getName() + 
							", " + weaponObjArray[(g*20)+i].getCurStr());
				}
				System.out.println("\t..."); 
			}//End of print bags loop
		}
	 }

	/**
	 * Uses a linear search algorithm to check if a random item exists in the user's inventory, and if so, prints its location in your inventory,
	 *   then reports the time (in nanoseconds) it took to search the array.
	 * 
	 * @param weaponsArray - Array of all weapons in the "A1_items.txt" file
	 * @param bags - 3d String array of inventory items 
	 * @param numWeapons - Number of weapons in the weaponsArray
	 * @param weaponObjArray - Array of Weapon objects
	 */
	private static void linearSearch(String[][] weaponsArray, String[][][] bags, int numWeapons, Weapon[] weaponObjArray){
		String[] randItem = new String[5];
		randItem = weaponsArray[new Random().nextInt(numWeapons)]; /** See reference 1 and 2 */ //Get random item form weaponsArray 
		System.out.println("\nSearching for " + randItem[3] + " " + randItem[0]);

		boolean found = false;
		long startTime = System.nanoTime(); /** See reference 5 */ //Set start time
		for(int g=0; g<bags.length; g++){//Search  bags for the randItem
			for(int i=0; i<bags[g].length; i++){//Print out all the items.
				if( (weaponObjArray[(g*20)+i].getName().equals(randItem[0])) && (weaponObjArray[(g*20)+i].getRarity().equals(randItem[3])) ){
					System.out.printf("\tFound in bag %d, slot %d. Strength: %s.\n", g+1, i+1, weaponObjArray[(g*20)+i].getCurStr());
					found = true;
				}
			}
		}//End of search
		long endTime = System.nanoTime(); /** See reference 5 */ //Set end time
		long totalTime = endTime - startTime;
		
		if(found == false){
			System.out.println("Not found.");
		}
		else{
			found = false;
		}
		System.out.println("Single Search time:  " + totalTime + " nanoseconds."); /** See reference 5 */
	}//End of Search()

	/**
	 * Uses a linear search algorithm to check if 10 random items exists in the user's inventory, and if so, prints its location in your inventory,
	 *   then reports the average time (in nanoseconds) it took to search the array 10 times.
	 * 
	 * 
	 * @param weaponsArray - Array of all weapons in the "A1_items.txt" file
	 * @param bags - 3d String array of inventory items 
	 * @param numWeapons - Number of weapons in the weaponsArray
	 * @param weaponObjArray - Array of Weapon objects
	 */
	private static void linearSearchNoOutput(String[][] weaponsArray, String[][][] bags, int numWeapons, Weapon[] weaponObjArray){
		int numTimes;
		long averageTime = 0;
		
		for(numTimes=0; numTimes<10; numTimes++){
			String[] randItem = new String[5];
			randItem = weaponsArray[new Random().nextInt(numWeapons)]; /** See reference 1 and 2 */ //Get random item form weaponsArray
			long startTime = System.nanoTime(); /** See reference 5 */ //Set start time
			
			for(int g=0; g<bags.length; g++){//Search  bags for the randItem
				for(int i=0; i<bags[g].length; i++){//Print out all the items.
					if( (weaponObjArray[(g*20)+i].getName().equals(randItem[0])) && (weaponObjArray[(g*20)+i].getRarity().equals(randItem[3])) ){
						//Do nothing
					}
				}
			}//End of search
			
			long endTime = System.nanoTime(); /** See reference 5 */ //Set end time
			long totalTime = endTime - startTime;
			averageTime += totalTime; //Add totalTime to the averageTime
		}
		averageTime = averageTime/10; //Divide averageTime by the number of times we searched (10)
		System.out.println("Average Search time: " + averageTime + " nanoseconds.");
	}//End of linearSearchNoOutput()

	/**
	 * Uses a selection sort algorithm to sort the inventory array based on the weapon name.
	 * 
	 * @param bags - 3d String array of inventory items 
	 * @param weaponObjArray - Array of Weapon objects
	 * @return This method returns the sorted bags array.
	 */
	private static String[][][] Sort(String[][][] bags, Weapon[] weaponObjArray){
		
		String[][] tempArray= new String[bags.length*bags[0].length][5];
		int tempI=0; 
		for(int i=0; i<bags.length; i++){//Fill tempArray with the contents from bags or weaponObjArray
			for(int j=0; j<bags[0].length; j++){
				tempArray[tempI][0] = weaponObjArray[(i*20)+j].getName();
				tempArray[tempI][1] = weaponObjArray[(i*20)+j].getMinStr();
				tempArray[tempI][2] = weaponObjArray[(i*20)+j].getMaxStr();
				tempArray[tempI][3] = weaponObjArray[(i*20)+j].getRarity();
				tempArray[tempI++][4] = weaponObjArray[(i*20)+j].getCurStr();
			}
		}

		for(int i = 0; i<tempArray.length; i++){//Sorting loop
            for(int j = 0; j<tempArray.length; j++){
	            if(tempArray[i][0].compareTo(tempArray[j][0]) < 0){ /** See reference 4 */
	                String[] temp = tempArray[i];
	                tempArray[i] = tempArray[j];
	                tempArray[j] = temp;
	            }
            }
        }//End of sorting loop
		
		tempI=0;
		for(int i=0; i<bags.length; i++){//Fill weaponObjArray with the sorted data
			for(int j=0; j<bags[0].length; j++){
				weaponObjArray[(i*20)+j].setName(tempArray[tempI][0]);
				weaponObjArray[(i*20)+j].setMinStr(tempArray[tempI][1]);
				weaponObjArray[(i*20)+j].setMaxStr(tempArray[tempI][2]);
				weaponObjArray[(i*20)+j].setRarity(tempArray[tempI][3]);
				weaponObjArray[(i*20)+j].setCurStr(tempArray[tempI][4]);
				weaponObjArray[(i*20)+j].setBagNum(i);
				bags[i][j] = tempArray[tempI++];
			}
		}
		return bags; //Return the sorted array
	}

	/** See reference 6 and 7
	 * 
	 * Uses a binary search algorithm to check if a random item exists in the inventory array, and if so, prints its location in your inventory,
	 * 	then reports the time (in nanoseconds) it took to search your inventory.
	 * ...It might not output them in the correct order, but it finds all instances of the item being searched for.
	 * 
	 * @param weaponsArray - Array of all weapons in the "A1_items.txt" file
	 * @param bags - 3d String array of inventory items 
	 * @param numWeapons - Number of weapons in the weaponsArray
	 * @param weaponObjArray - Array of Weapon objects
	 */
	private static void binarySearch(String[][] weaponsArray, int numWeapons, Weapon[] weaponObjArray){ 
		String[] randItem = new String[5];
		
		boolean found = false;
		randItem = weaponsArray[new Random().nextInt(numWeapons)]; /** See reference 1 and 2 */ //Get random item form weaponsArray 
		System.out.println("\nBINARY Searching for: " + randItem[3] + " " + randItem[0]);
		long startTime = System.nanoTime(); /** See reference 5 */ //Set start time
		int low =0; 
		int high = weaponObjArray.length-1;
		
		while(low <= high) {
			int mid = (low + high) / 2;
			if(randItem[0].compareTo(weaponObjArray[mid].getName()) < 0) {
				high = mid - 1;
			}
			else if( randItem[0].equals(weaponObjArray[mid].getName()) && randItem[3].equals(weaponObjArray[mid].getRarity()) ) {
				binarySearchAdjacentElements(weaponObjArray, mid, randItem); //Once you find one correct element, check adjacent elements.
				found = true;
				break;
			}
			else {
				low = mid + 1;
			}
		}
		long endTime = System.nanoTime(); /** See reference 5 */ //Set end time
		long totalTime = endTime - startTime;
		
		if(found == false){
			System.out.println("Not found.");
		}
		else{
			found = false;
		}
		System.out.println("Single Search time:  " + totalTime + " nanoseconds."); /** See reference 5 */
	}//End of binarySearch()

	/** See reference 6 and 7
	 * After binarySearch() finds the first matching item, this method finds all adjacent matching items.
	 * ...It might not output them in the correct order, but it finds all instances of the item being searched for.
	 * 
	 * @param weaponObjArray - Array of all weapons in the "A1_items.txt" file
	 * @param middleIndex - The first found element in the weaponObjArray with the correct weapon
	 * @param randItem - The random weapon we are searching for.
	 */
	private static void binarySearchAdjacentElements(Weapon[] weaponObjArray, int middleIndex, String[] randItem) { 
		System.out.printf("\tFound in bag %d, slot %d. Strength: %s.\n", weaponObjArray[middleIndex].getBagNum()+1, middleIndex%20+1,
				weaponObjArray[middleIndex].getCurStr() );
		
		//Test if the name is equal, then after, test if the rarity is also equal...because they are not sorted by name AND rarity..So rarity is random
		int tempMiddle = middleIndex;
		while(weaponObjArray[tempMiddle+1].getName().equals(randItem[0]) && tempMiddle <= weaponObjArray.length+1) {
			if(weaponObjArray[tempMiddle+1].getRarity().equals(randItem[3])) {
				System.out.printf("\tFound in bag %d, slot %d. Strength: %s.\n", weaponObjArray[tempMiddle+1].getBagNum()+1,
						((tempMiddle+1)%20)+1, weaponObjArray[tempMiddle+1].getCurStr() );
			}
			tempMiddle++;
		}
		
		tempMiddle = middleIndex;
		while(weaponObjArray[tempMiddle-1].getName().equals(randItem[0]) && tempMiddle >=0) {
			tempMiddle--;
			if(weaponObjArray[tempMiddle].getRarity().equals(randItem[3])) {
				System.out.printf("\tFound in bag %d, slot %d. Strength: %s.\n", weaponObjArray[tempMiddle].getBagNum()+1,
						((tempMiddle)%20)+1, weaponObjArray[tempMiddle].getCurStr() );
			}
		}
	}//End of binarySearchAdjacentElements()
	
	/** See reference 6 and 7
	 * 
	 * Uses a binary search algorithm to check if a random item exists in the inventory array, 
	 * 	then reports the average time (in nanoseconds) it took to search your inventory - happens 10 times
	 * 
	 * @param weaponsArray - Array of all weapons in the "A1_items.txt" file
	 * @param bags - 3d String array of inventory items 
	 * @param numWeapons - Number of weapons in the weaponsArray
	 */
	private static void binarySearchNoOutput(String[][] weaponsArray, int numWeapons, Weapon[] weaponObjArray){ 
		String[] randItem = new String[5];
		int numTimes;
		long averageTime = 0;

		for(numTimes=0; numTimes<10; numTimes++){
			randItem = weaponsArray[new Random().nextInt(numWeapons)]; /** See reference 1 and 2 */ //Get random item form weaponsArray 
			//System.out.println("\nBINARY Searching for: " + randItem[3] + " " + randItem[0]);
			long startTime = System.nanoTime(); /** See reference 5 */ //Set start time
			int low =0; 
			int high = weaponObjArray.length-1;
			
			while(low <= high) {
				int mid = (low + high) / 2;
				if(randItem[0].compareTo(weaponObjArray[mid].getName()) < 0) {
					high = mid - 1;
				}
				else if( randItem[0].equals(weaponObjArray[mid].getName()) && randItem[3].equals(weaponObjArray[mid].getRarity()) ) {
					binarySearchAdjacentElementsNoOutput(weaponObjArray, mid, randItem); //Once you find one correct element, check adjacent elements.
					break;
				}
				else {
					low = mid + 1;
				}
			}
			long endTime = System.nanoTime(); /** See reference 5 */ //Set end time
			long totalTime = endTime - startTime;
			averageTime += totalTime;
		}//End of for loop
		averageTime = averageTime/10;
		System.out.println("Average Search time: " + averageTime + " nanoseconds.");//End of for loop
	}//End of binarySearchNoOutput()
	
	/**	See reference 6 and 7
	 * 
	 * After binarySearchNoOutput() finds the first matching item, this method finds all adjacent matching items.
	 * 
	 * @param weaponObjArray
	 * @param middleIndex
	 * @param randItem
	 */
	private static void binarySearchAdjacentElementsNoOutput(Weapon[] weaponObjArray, int middleIndex, String[] randItem) {
		//Test if the name is equal, then after, test if the rarity is also equal...because they are not sorted by name AND rarity..So rarity is random
			int tempMiddle = middleIndex;
			while(weaponObjArray[tempMiddle+1].getName().equals(randItem[0]) && tempMiddle <= weaponObjArray.length+1) {
				if(weaponObjArray[tempMiddle+1].getRarity().equals(randItem[3])) {
					//Do nothing
				}
				tempMiddle++;
			}
			tempMiddle = middleIndex;
			
			while(weaponObjArray[tempMiddle-1].getName().equals(randItem[0]) && tempMiddle >=0) {
				if(weaponObjArray[tempMiddle-1].getRarity().equals(randItem[3])) {
					//Do nothing
				}
				tempMiddle--;
			}
	}//End of binarySearchAdjacentElementsNoOutput()
	
}//End of Assignment1


