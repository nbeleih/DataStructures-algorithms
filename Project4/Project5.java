
/**
* COP 3530: Project 5 – Hash Tables
* <p>
* This classes asks for the CSV file name that contains the info, and parses it then inserts it into a hash table
* This class has 6 choices such as print hash table, insert, delete, search for a state,
* and print number of empty cells and collisions in hash table
* 
* @author Nour Beleih
* @version 4/17/2021
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Project5 {

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);
		
		
		String path = "States5.csv";
		
		String Line = "";
		int i = 0;
		int userInput;
		boolean checkCsvFileName = false;
		String[] result = null;
		HashTable hashTable = new HashTable();
		State[] stateObjs = new State[51];
		int counter = 0;
		  
		
	while(checkCsvFileName == false) 
	{
		System.out.print("Enter the name of the csv file: ");
		String csvFileName = input.next().toLowerCase();
		
		
		if(csvFileName.equals("states5.csv")) 
		{
			
		checkCsvFileName = true;
		BufferedReader br = new BufferedReader(new FileReader(path));

		br.readLine();
		
		while ((Line = br.readLine()) != null) 
		{

			result = Line.split(",");

			
            hashTable.insert(result[0], Integer.parseInt(result[4]) , Integer.parseInt(result[6]) );
            stateObjs[counter] = new State(result[0] , Integer.parseInt(result[4]) , Integer.parseInt(result[6]));
    		counter++;

			i++;

		}//end while loop
		
		System.out.println("\nThere were 50 records read to build a binary search tree.\n");

	do {
		
			
		System.out.println("\n1) Print hash table");
		System.out.println("2) Delete a state for a given name");
		System.out.println("3) Insert a state of a given name");
		System.out.println("4) Search and print a state and its DR for a given name");
		System.out.println("5) Print numbers of empty cells and collisions");
		System.out.println("6) Exit");

		System.out.print("Enter your choice: ");
		
		while(!input.hasNextInt()) 
		{
			
			System.out.println("Input is not a number!");
			System.out.println("Enter again: ");
			input.nextLine();
		}
		
		userInput = input.nextInt();

		
		if(userInput == 1) 
		{
			hashTable.display();
			
		}
		else if(userInput == 2)
		{

			System.out.print("\nEnter the state name you want to delete: ");
			input.nextLine();
			String stateToBeDeleted = input.nextLine();
			// stateToBeDeleted = stateToBeDeleted.substring(0, 1).toUpperCase() + stateToBeDeleted.substring(1);
			 long population = 0;
			 long deaths = 0;
			 
			 stateToBeDeleted = capitalizeString(stateToBeDeleted);
			 
			 for(int j = 0; j < stateObjs.length; j++)
			 {
				
				 try 
				 {
					 if(stateObjs[j].stateName.equals(stateToBeDeleted)) 
					 {
						 population = stateObjs[j].population;
						 deaths = stateObjs[j].deaths;
						 break;
					 }
					 
				 } catch(Exception  e) {
					 
					 System.out.println("\nState given is not a REAL state name...");
				 }
				
			 }//end for loop
			 
			 hashTable.delete(stateToBeDeleted, population, deaths);
			
		}
		else if(userInput == 3)
		{

			System.out.print("\nEnter the state name you want to insert: ");
			input.nextLine();
			String stateToBeInserted = input.nextLine();
			// stateToBeInserted = stateToBeInserted.substring(0, 1).toUpperCase() + stateToBeInserted.substring(1);
			 long population = 0;
			 long deaths = 0;
			 
			 stateToBeInserted = capitalizeString(stateToBeInserted);
			 
			 for(int j = 0; j < stateObjs.length; j++)
			 {
				
				 try 
				 {
					 if(stateObjs[j].stateName.equals(stateToBeInserted)) 
					 {
						 population = stateObjs[j].population;
						 deaths = stateObjs[j].deaths;
						 break;
					 }
					 
				 } catch(Exception  e) {
					 
					 System.out.println("\nState given is not a REAL state name...");
				 }
				
			 }
			
				 hashTable.insert(stateToBeInserted, population, deaths);	
			 
		}
		else if(userInput == 4)
		{
			
			System.out.print("\nEnter the state name you want to find: ");
			input.nextLine();
			String stateToBeSearched = input.nextLine();
			// stateToBeSearched = stateToBeSearched.substring(0, 1).toUpperCase() + stateToBeSearched.substring(1);
			 long population = 0;
			 long deaths = 0;
			stateToBeSearched = capitalizeString(stateToBeSearched);
			 
			for(int j = 0; j < stateObjs.length; j++)
			 {
				
				 try 
				 {
					 if(stateObjs[j].stateName.equals(stateToBeSearched)) 
					 {
						 population = stateObjs[j].population;
						 deaths = stateObjs[j].deaths;
						 break;
					 }
					 
				 } catch(Exception  e) {
					 
					 System.out.println("\nState given is not a REAL state name...");
				 }
				
			 }//end for loop
			 
			int resultOfSearching = hashTable.find(stateToBeSearched, population, deaths);
			boolean foundOrNot = resultOfSearching > 0 ? true : false;
			
			if(foundOrNot == true) {
				
				System.out.printf("\nFound %s at index: %d with DR of : %6.2f\n", stateToBeSearched , resultOfSearching , (double)deaths/population*100000);
				
			}
			else
			{
				System.out.println("Couldn't find given state");
			}
			
			
			
		}
		else if(userInput == 5)
		{
			
			hashTable.printEmptyAndCollisions();
			
		}
		
		else if(userInput == 6) {
			System.out.println("Goodbye!");
			break;
		}
		else
		{
			System.out.println("Invalid option.");
		}
	} while(userInput != 6);
	
		}//end csvfileName if
		else
		{
			System.out.print("Csv File name was incorrect. Try again\n ");
		}
		
	}//end checkCsvFileName while loop
	
	}
	
	public static String capitalizeString (String theString) 
	{
	   
		
		StringBuffer sb = new StringBuffer(); 
	    boolean capOrNot = true;
	    String s = " ,.-;";

	    for (int i = 0; i < theString.length(); i++) 
	    {
	        if (capOrNot)              
	            sb.append(Character.toUpperCase(theString.charAt(i)));
	        else
	            sb.append(Character.toLowerCase(theString.charAt(i)));

	        if (s.indexOf(theString.charAt(i)) >=0) 
	            capOrNot = true;
	        else
	            capOrNot = false;
	    }          
	    return sb.toString().trim();
	}//end capitalize  

}
