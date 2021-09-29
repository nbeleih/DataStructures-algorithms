
/**
* COP 3530: Project 2 – Stacks and PriorityQ Queues
* This class contains the main method that creates objects of PriortiyQ, and Stack classes ,and then
* calls it's methods of push() , remove() , and more. There are no outputs of this class. This class
* prints the PriorityQs groups and also prints the stack contents
* 
*  @author Nour Beleih
* @version 2/19/2021
*/

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Project2 {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		String path = "C:\\Users\\nourm\\Desktop\\States2.csv";
		String line = null;
		State[] stateObjs = new State[50];
		String[] result = null;
		int i = 0;
		boolean checkCsvName = false;
		
		Scanner input = new Scanner(System.in);
		
		
		while (checkCsvName == false) {

			System.out.println("Enter the name of the CSV file(include .csv in the name): ");
			String csvFile = input.next().toLowerCase();
			

			if (csvFile.equals("states2.csv")) {

				checkCsvName = true;
				System.out.println("There were 50 records read.\n");
		
		
		BufferedReader br = new BufferedReader(new FileReader(path));
		
		br.readLine();
		while ((line = br.readLine()) != null && i <= 50) {

			result = line.split(",");

			stateObjs[i] = new State(result[0], result[1], result[2], result[4], result[5], result[6],
					result[7], result[8]);
			
			i++;
			
		}
		
		//run through stateobjs[i].getDR in a loop, and have an if statement that checks whether DR < 50 ? 
		//then insert it into the VGOOD PriorityQ!!
		
		int n = stateObjs.length - 1;
		int VGOODnum = 0;
		int GOODnum = 0;
		int FAIRnum = 0;
		int POORnum = 0;
		
		for(int j = 0; j < n; j++) {
			
			
			if(stateObjs[j].getDeathRate() < 50) {
				
				//count with a variable so you could have a size for the priority Q in each group
				// then have another loop that creates the actual ones
				VGOODnum++;
				
			}
			else if( (stateObjs[j].getDeathRate() > 50) && (stateObjs[j].getDeathRate() < 100) ) {
				
				GOODnum++;
			}
			else if( (stateObjs[j].getDeathRate() > 100) && (stateObjs[j].getDeathRate() < 150) ) {
				
				FAIRnum++;
			}
			else {
				
				POORnum++;
			}
			
		}//end for loop
		
		//creating the priortiy Queues with the right size
		
		PriorityQ VGOOD = new PriorityQ(VGOODnum);
		PriorityQ GOOD = new PriorityQ(GOODnum);
		PriorityQ FAIR = new PriorityQ(FAIRnum);
		PriorityQ POOR = new PriorityQ(POORnum);
		
		for(int w = 0; w < n; w++) {
			
		//-----------------------------calculating CFR-----------------------------------------------
			
			int numOfDeaths = Integer.parseInt(stateObjs[w].getNumOfCovidDeaths());
			int numOfCases = Integer.parseInt(stateObjs[w].getNumOfCovidCases());
			
			double CFR = (double)numOfDeaths/(double)numOfCases;
			
			
			stateObjs[w].setCFR(CFR);
			
	//---------------------------------calculating case Rate-------------------------------------------

			
			int population = Integer.parseInt(stateObjs[w].getPopulation());
			double caseRate = ( (double)numOfCases / (double)population) * 100000;
			
			// set object to caseRate
			stateObjs[w].setCaseRate(caseRate);
			
			
			if(stateObjs[w].getDeathRate() < 50) {
				
				VGOOD.insert(stateObjs[w]);
				
			}
			else if( (stateObjs[w].getDeathRate() > 50) && (stateObjs[w].getDeathRate() < 100) ) {
				
				GOOD.insert(stateObjs[w]);
			}
			else if( (stateObjs[w].getDeathRate() > 100) && (stateObjs[w].getDeathRate() < 150) ) {
				
				FAIR.insert(stateObjs[w]);
			}
			else {
				if(stateObjs[w].getDeathRate() > 150)
				POOR.insert(stateObjs[w]); 
			}
			
		}//end 2nd for loop
		
		
		//printing the PriorityQs from POOR to VGOOD
		
		
		System.out.println("\nPOOR Priority Queue contents:\n ");
		System.out.println("\tName\t\t MHI\t\t VCR\t\t CFR\t\tCase Rate\t Death Rate");
		System.out.println("--------------------------------------------------------"
				+ "-------------------------------------------------");
		
		POOR.printQueue();
		
		System.out.println("\nFAIR Priority Queue contents: \n");
		System.out.println("\tName\t\t MHI\t\t VCR\t\t CFR\t\tCase Rate\t Death Rate");
		System.out.println("--------------------------------------------------------"
				+ "-------------------------------------------------");
		
		
		FAIR.printQueue();
		
		System.out.println("\nGOOD Priority Queue contents: \n");
		System.out.println("\tName\t\t MHI\t\t VCR\t\t CFR\t\tCase Rate\t Death Rate");
		System.out.println("--------------------------------------------------------"
				+ "-------------------------------------------------");
		
		GOOD.printQueue();
		
		System.out.println("\nVGOOD Priority Queue contents: \n");
		System.out.println("\tName\t\t MHI\t\t VCR\t\t CFR\t\tCase Rate\t Death Rate");
		System.out.println("--------------------------------------------------------"
				+ "-------------------------------------------------");
		
		VGOOD.printQueue();
	
	
		Stack theStack = new Stack(50);
	
		//push items at the priorityQ one by one into the stack
	
	for(int c = 0; c < POORnum; c++) {
		
		State poorDRState = POOR.remove();  //problem here i think!!!!
		POOR.removingElement();
		theStack.push(poorDRState);
	}
	for(int c2 = 0; c2 < FAIRnum; c2++) {
		
		State fairDRstate = FAIR.remove();
		FAIR.removingElement();
		theStack.push(fairDRstate);
	}
	for(int c3 = 0; c3 < GOODnum; c3++) {
		
		State goodDRstate = GOOD.remove();
		GOOD.removingElement();
		theStack.push(goodDRstate);
	}
	for(int c4 = 0; c4 < VGOODnum; c4++) {
		
		State vgoodDRstate = VGOOD.remove();
		VGOOD.removingElement();
		theStack.push(vgoodDRstate);
	}
	
	System.out.println("\nStack contents: \n");
	System.out.println("\tName\t\t MHI\t\t VCR\t\t CFR\t\tCase Rate\t Death Rate");
	System.out.println("--------------------------------------------------------"
			+ "-------------------------------------------------");
	
	theStack.printStack();

			}//if statement
			else {
				System.out.println("Wrong CSV name. Try again");
			}
		}//end while
}//end main method
	
}//end project2 class
