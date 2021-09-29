
/**
* COP 3530: Project 4 – Binary Search Trees
* <p>
* This classes asks for the csv file name that contains the info, and parses it then inserts it into a BST
*	This class has 7 choices such as print inorder, preorder, postorder, find a node, delete a node, print bottom
*	States and print top states regarding DR
* @author Nour Beleih
* @version 3/26/2021
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Project4 {

	public static void main(String[] args) throws IOException {

		Scanner input = new Scanner(System.in);
		
		
		String path = "States4.csv";
		
		String Line = "";
		int i = 0;
		int userInput;
		boolean checkCsvFileName = false;
		String[] result = null;
		BinarySearchTree BST = new BinarySearchTree();
		
	while(checkCsvFileName == false) 
	{
		System.out.print("Enter the name of the csv file: ");
		String csvFileName = input.next().toLowerCase();
		
		
		if(csvFileName.equals("states4.csv")) 
		{
			
		checkCsvFileName = true;
		BufferedReader br = new BufferedReader(new FileReader(path));

		br.readLine();
		
		while ((Line = br.readLine()) != null) 
		{

			result = Line.split(",");

			int numOfDeaths = Integer.parseInt(result[6]);
			int ppl = Integer.parseInt(result[4]);
			double finalDeathRate = ( (double)numOfDeaths / (double)ppl) * 100000;
			
			
			
			BST.insert(result[0], finalDeathRate);

			i++;

		}//end while loop
		
		System.out.println("\nThere were 50 records read to build a binary search tree.\n");

	do {
		
			
		System.out.println("\n1) Print tree inorder");
		System.out.println("2) Print tree preorder");
		System.out.println("3) Print tree postorder");
		System.out.println("4) Delete a state for a given name");
		System.out.println("5) Search and print a state and its path for a given name");
		System.out.println("6) Print bottom states regarding DR");
		System.out.println("7) Print top states regarding DR");
		System.out.println("8) Exit");

		System.out.print("Enter your choice: ");
		//userInput = input.nextInt();
		
		while(!input.hasNextInt()) 
		{
			
			System.out.println("Input is not a number!");
			System.out.println("Enter again: ");
			input.nextLine();
		}
		
		userInput = input.nextInt();

		
		if(userInput == 1) 
		{
			System.out.println("       Name\t    Death Rate");
			System.out.println("------------------------------------");
			Node theLocalRoot = BST.getRoot();
			BST.printInorder(theLocalRoot);
		}
		else if(userInput == 2)
		{

			System.out.println("       Name\t    Death Rate");
			System.out.println("------------------------------------");
			Node theLocalRoot = BST.getRoot();
			BST.printPreorder(theLocalRoot);
		}
		else if(userInput == 3)
		{

			System.out.println("       Name\t    Death Rate");
			System.out.println("------------------------------------");
			Node theLocalRoot = BST.getRoot();
			BST.printPostorder(theLocalRoot);
		}
		else if(userInput == 4)
		{
			System.out.print("\nEnter the state name you want to delete: ");
			input.nextLine();
			String stateToBeDeleted = input.nextLine();
			 stateToBeDeleted = stateToBeDeleted.substring(0, 1).toUpperCase() + stateToBeDeleted.substring(1);

			
			Node theLocalRoot = BST.getRoot();

			boolean found = BST.searchFor(theLocalRoot, stateToBeDeleted);
			if(found == true) 
			{
				BST.delete(stateToBeDeleted);
				System.out.printf("\n%s has been deleted.\n" , stateToBeDeleted);
			}
			else
			{
				System.out.println("Can't find input!\n");
			}
			
			
		}
		else if(userInput == 5)
		{
			System.out.print("Enter state name: ");
			input.nextLine();
			String nameEntered = input.nextLine();
			//input.next();
			//nameEntered += input.next();
			
		
			nameEntered = nameEntered.substring(0, 1).toUpperCase() + nameEntered.substring(1);

			double findState = BST.find(nameEntered);
			
			if(findState > 0) 
			{
				System.out.printf("%s is found with a death rate of: %6.2f\n" ,nameEntered , findState);
				System.out.printf("Path to %s is: " , nameEntered);
				int sizeOfPath = BST.path.size();
				
				for(int j = 0; j < sizeOfPath; j++) 
				{
					System.out.print(BST.path.get(j) + " -> ");
				}
				System.out.printf("%s" , nameEntered);
			}
			else
			{
				System.out.println("State is not found");
			}
			
			
			
		}
		else if(userInput == 6)
		{
			System.out.print("\nEnter the number of states: ");
			int numOfStates = input.nextInt();

			
			BST.printBottomStates(numOfStates);
		}
		else if(userInput == 7)
		{
			System.out.print("\nEnter the number of states: ");
			int c = input.nextInt();
			
			
			BST.printTopStates(c);
			
		}
		else if(userInput == 8) {
			System.out.println("Goodbye!");
			break;
		}
		else
		{
			System.out.println("Invalid option.");
		}
	} while(userInput != 8);
	
		}//end csvfileName if
		else
		{
			System.out.print("Csv File name was incorrect. Try again\n ");
		}
		
	}//end checkCsvFileName while loop
	
	}

}
