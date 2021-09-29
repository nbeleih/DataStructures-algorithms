
public class PriorityQ {

	int nElements;
	int maxSize;
	State[] priorityQstate;
	int j;
	/**
	 * 
	 */
	int lowest = 0;

	public PriorityQ(int size) {

		maxSize = size;
		priorityQstate = new State[maxSize];
		nElements = 0;

	}

	/**
	* This method is used to insert items into the Priority Queue
	*
	* @param State Object
	*/
	
	public void insert(State obj) {

		priorityQstate[nElements++] = obj;



	}//end insert() method

	/**
	* This method finds the smallest object's DR and returns the whole object
	*
	* @return State type object
	*/
	public State remove() {


		if(isEmpty()) {

			System.out.println("The Priority Queue is Empty");
		}
		else
		{



			for(j = 0; j < nElements - 1; j++) {    //sequential search

				lowest = j;

				for(int i = nElements - 1; i >= 0; i--) {

					if(priorityQstate[i].getDeathRate() < priorityQstate[lowest].getDeathRate()) {

						lowest = i;
					}//end if statement

				}//end 2nd for loop

				
			}//end 1st for loop
			
		
			return priorityQstate[lowest];			


		}//end else

		return null;
	}//end remove() method

	/**
	* This method loops from beginning to end of PriorityQ and prints it's content
	* 
	*/
	public void printQueue() {

		for(int i = 0; i < maxSize; i++) { 

			System.out.println(priorityQstate[i]);
		}
	}
	/**
	* Checks if the PriorityQ is empty
	*
	* @return boolean value
	*/
	public boolean isEmpty()
	{

		return (nElements == 0);
	}

	public boolean isFull()
	{

		return (nElements == maxSize);
	}
	
	/**
	* Removes elements from PriorityQ
	*
	* @param no parameters 
	* @return returns void but removes elements from the Priority Queue.
	*/
	
	public void removingElement() {

		for(int index = lowest; index < nElements - 1; index++) {  //loop for deleting the array

			priorityQstate[index] = priorityQstate[index + 1];
		} // end for loop 

		nElements--;
	}
}

