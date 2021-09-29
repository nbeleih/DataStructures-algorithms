/*
 * This is the main method
 * 
 * It gets a csv file from user and displays the data in it. It stores the data in the csv file 
 * an array of objects
 * @param args an array of String Objects
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author nourm
 *
 */
public class Project1 {

	public static void main(String[] args) throws IOException {

		String path = "C:\\Users\\nourm\\Desktop\\State1.csv";
		String line = "";
		State[] statesObj = new State[51]; // check maybe its 51 in size
		int i = 0;
		int userInput = 0;
		boolean checkCsvName = false;
		String[] result = null;
		String[] stateNames = null;
		boolean checkSortStatus = false;

		Scanner input = new Scanner(System.in);

		// A while loop that will loop until a valid csv file name is correct
		while (checkCsvName == false) {

			System.out.println("Enter the name of the CSV file(include .csv in the name): ");
			String csvFile = input.next().toLowerCase();
			

			if (csvFile.equals("states1.csv")) {

				checkCsvName = true;
				System.out.println("There were 50 records read.\n");

				do {

					BufferedReader br = new BufferedReader(new FileReader(path));

					br.readLine();
					while ((line = br.readLine()) != null && i <= 50) {

						result = line.split(",");

						statesObj[i] = new State(result[0], result[1], result[2], result[4], result[5], result[6],
								result[7], result[8]);
						
						i++;

					}
					Project1.computeCFR(statesObj);
					Project1.computeCaseRate(statesObj);
					System.out.println("1. Print a States report");
					System.out.println("2. Sort by Name");
					System.out.println("3. Sort by Case Fatality Rate");
					System.out.println("4. Sort by Median Household Income");
					System.out.println("5. Find and print a given State");
					System.out.println("6. Print Spearman’s rho matrix");
					System.out.println("7. Quit");

					System.out.print("Enter your choice: ");
					userInput = input.nextInt();

					if (userInput == 1) {

						System.out.println(
								"\tName\t\t    Capitol\t\t\t Region\t\t Population\t      Case Rate\t\tCFR\t Median Income    Violent Crime Rate");
						System.out.println("  -----------------------------------------------------------------------"
								+ "------------------------------------------------------------------------------------");
						for (int walker = 0; walker < statesObj.length - 1; walker++) {

							System.out.println(statesObj[walker]);
						}

					}

					if (userInput == 2) { // sorts state names

						Project1.sortNames(statesObj); // sorts out the names alphabetically
						System.out.println("States sorted by names.");
						checkSortStatus = true;

						/*
						 * for(int j2 = 0; j2 < statesObj.length; j2++) {
						 * System.out.println(statesObj[j2]);
						 * 
						 * }
						 */
					}

					if (userInput == 3) {

						Project1.sortCFR(statesObj); // using the method to sort from lowest to greatest
						System.out.println("States sorted by CFR.");

					}

					if (userInput == 4) {

						Project1.sortMedianIncome(statesObj); 
						System.out.println("States sorted by Household Income.\n");

						

					}

					if (userInput == 5) {

						System.out.print("Enter a state name: ");
						String stateEntered = input.next();
						input.nextLine();
						boolean found = false;

						for (int i5 = 0; i5 < statesObj.length - 1; i5++) {

							if (statesObj[i5].getName().equals(stateEntered)) {

								found = true;
							}

						}
						if (found == true) {

							if (checkSortStatus == false) {

								System.out.println("Used Sequential search.");
								// do sequential search here
								for (int w = 0; w < statesObj.length - 1; w++) {

									if (statesObj[w].getName().equals(stateEntered)) {

										System.out.printf("Name: %s\n" , statesObj[w].getName());
										System.out.printf("MHI: %s\n",statesObj[w].getHouseholdIncome());
										System.out.printf("VCR: %s\n" , statesObj[w].getCrimeRate());
										System.out.printf("CFR: %5.5f\n" , statesObj[w].getCFR());
										System.out.printf("Case Rate: %5.5f\n" , statesObj[w].getCaseRate());

									}
								}
							} else {
								// do binary search
								int returnedIndex = Project1.findingStateWithBinary(statesObj, stateEntered);
								System.out.printf("Name: %s\n" , statesObj[returnedIndex].getName());
								System.out.printf("MHI: %s\n",statesObj[returnedIndex].getHouseholdIncome());
								System.out.printf("VCR: %s\n" , statesObj[returnedIndex].getCrimeRate());
								System.out.printf("CFR: %5.5f\n" , statesObj[returnedIndex].getCFR());
								System.out.printf("Case Rate: %5.5f\n" , statesObj[returnedIndex].getCaseRate()); 
								System.out.println("Used Binary search.");

							}
						} // end if statement

						else {
							System.out.println("Couldn't find given state");

						}

					}

				} while (userInput != 7); // end do while loop

				System.out.println("Goodbye!");

			} else {

				System.out.println("File doesn't exist. Try again");

			} // end if else statement

		} // end while loop

	}// end main

	/*
	 * This method takes array of objects and sorts it alphabetically
	 * @param State[] StateNames
	 * 
	 */
	public static State[] sortNames(State[] stateNames) {

		int n = stateNames.length;
		State temp;

		for (int i = 0; i < n - 1; i++) {

			for (int j = n - 1; j > i; j--) {

				if (!stateNames[j].getName().isBlank()) {
					int num = stateNames[j - 1].getName().compareTo(stateNames[j].getName());

					if (num > 0) {

						temp = stateNames[j - 1];
						stateNames[j - 1] = stateNames[j];
						stateNames[j] = temp;

					}
				}
			}
		}

		return null;
	}

	/*
	 * This method takes the objects read from the csv file and sorts it by CFR
	 * @param State[] CFR
	 * 
	 */
	public static State[] sortCFR(State[] CFR) {

		int lowest;
		int n = CFR.length;
		int i;

		for (i = 0; i < n - 1; i++) {

			lowest = i;

			for (int j = i + 1; j < n; j++) {

				int jNumber = Integer.parseInt(CFR[j].getNumOfCovidDeaths()); 
				int jNumber2 = Integer.parseInt(CFR[j].getNumOfCovidCases());
				double finaleJnumber = (double)jNumber/(double)jNumber2;
			
				int iNumber = Integer.parseInt(CFR[lowest].getNumOfCovidDeaths());
				int iNumber2 = Integer.parseInt(CFR[lowest].getNumOfCovidCases());
				
				double finaleInumber = (double)iNumber/(double)iNumber2;
				
				
				CFR[j].setCFR(finaleJnumber);
				CFR[lowest].setCFR(finaleInumber);
				//set above = to numofCOVIDDeaths
				

				if (finaleJnumber < finaleInumber) {

					lowest = j;
				}

			}

			if (lowest != i) {

				State temp = CFR[lowest];
				CFR[lowest] = CFR[i];
				CFR[i] = temp;

			}

		}

		return null;
	}

	/*
	 * This method also takes StateObj. that is read from the csv file and sorts it based on house hold income
	 * @param State[] medianIncome
 	 * 
	 */
	public static State[] sortMedianIncome(State[] medianIncome) {

		int key, j;
		State temp;
		int n = medianIncome.length;

		for (int i = 1; i < n - 1; i++) {

			temp = medianIncome[i];
			j = i - 1;

			while (j >= 0 && medianIncome[j].getHouseholdIncome().compareTo(temp.getHouseholdIncome()) > 0) { // problem
																												// here

				medianIncome[j + 1] = medianIncome[j];
				j--;

				medianIncome[j + 1] = temp;
			}
		}

		return null;
	}

	/*
	 * 
	 * This method takes in two parameters and finds the given state by the user using binary search
	 * @param State[] stateObj
	 * @param target
	 */
	public static int findingStateWithBinary(State[] stateObj, String target) {

		int n = stateObj.length;
		int upper = n - 1;
		int lower = 0;
		
		
		while (upper >= lower) {
	
			int mid = (lower + upper) / 2;

			int num = target.compareToIgnoreCase(stateObj[mid].getName()); //edited HERE RECENTLY

			if (num > 0) {

				upper = mid - 1;
				return upper;
				//System.out.print(stateObj[upper]);

			} else if (num < 0) {

				lower = mid + 1;
				return lower;
				//System.out.print(stateObj[lower]);
			} else {
				
				return mid;
			}

		}
		return -1;
	}
	/*
	 * 
	 * This method calculates the CFR of each state object
	 * @param State[] CFR
	 */
	public static void computeCFR(State[] CFR) {
		
		int n = CFR.length;
		int i;

		for (i = 0; i < n - 1; i++) {

		
				int iNumber = Integer.parseInt(CFR[i].getNumOfCovidDeaths());
				int iNumber2 = Integer.parseInt(CFR[i].getNumOfCovidCases());
				
				double finaleInumber = (double)iNumber/(double)iNumber2;
				
				
				CFR[i].setCFR(finaleInumber);
				
		}
	}
	/*
	 * 
	 * This method calculates the case Rate per state object
	 * @param State[] statesObj
 	 */
	public static void computeCaseRate(State[] statesObj) {
		
		
		int n = statesObj.length;
		double caseRate;
		
		for(int i = 0; i < n - 1; i++) {
			
			int Covid19Cases = Integer.parseInt(statesObj[i].getNumOfCovidCases());
			int population = Integer.parseInt(statesObj[i].getPopulation());
			
			caseRate = (double)Covid19Cases / (double)population;
			// set object to caseRate
			statesObj[i].setCaseRate(caseRate);
			
		}
	}
}
