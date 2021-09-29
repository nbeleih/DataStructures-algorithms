/*
 * Detailed description of the class
 * 
 * @author: Nour Beleih
 * @version: 4/17/21
 */


public class HashTable {

	private int hashTableSize;
    private LinkedList[] hashArr;
    
    public HashTable()
    {
    	hashArr = new LinkedList[hashTableSize = 101];
    }
	
	private class Node
	{
		String name;
		long population;
		long deaths;
		Node nextNode;
		
		public Node(String name,long population,long deaths)
		{
			this.name = name;
			this.population = population;
			this.deaths = deaths;
			}
		public void printNode()
		{
			System.out.printf("%-30s%-20.2f\n",name,(double)deaths/population*100000);
			}
		
		}//end Node class
	
	
	
	class LinkedList
	{
		
		private Node first;
		private Node last;
		
		public LinkedList(Node theNode) 
		{
			this.first = theNode;
			this.last = theNode;
		}
		
		/*
		 * This method inserts into a linked list and it is used as a helper method for insert() method
		 * 
		 * @param Node to be inserted in the linked list
		 *	 @return void
		 */
		
		public void insertIntoLinkedList(Node theNode) 
		{
			int hashVal = HashingFunc(theNode);
			
			Node foundOrnot = hashArr[hashVal].find(theNode.name);
			
			if(foundOrnot != null)
			{
				System.out.println(theNode.name +" already exists. Can't insert it.");
				return;
			}
		
			String key = theNode.name;
			
			Node cur = first;
			
			if (cur == null)
			      last = theNode;
			    
			theNode.nextNode = first;
			first = theNode;
			 
		}// end push method
		
		/*
		 * This method finds the node with given state name.THIS METHOD IS USED AS A HELPER METHOD FOR THE ORIGINAL FIND METHOD!
		 * 
		 * @param takes in state name as a parameter
		 *	 @return Node that contains the state name
		 */
		
		public Node find(String stateName) // find link
		{
		
			Node cur = first; // start at first
			boolean found = false;
		
		while(cur != null && found == false)
		
		{ // or key too small,
		
			if(cur.name.equals(stateName)) { 	// is this the link?

				found = true;
				return cur; // found it, return link
			}
				
			cur = cur.nextNode; // go to next item
		}
		return null; // didn’t find it
		
		} // end find()
		
		
		/*
		 * This method removes the node with given state name
		 * 
		 * @param a state name
		 *	 @return true or false if it removed a node
		 */
		 public boolean remove(String stateName) {

			 Node cur = first;
			 
			 
			 while(!cur.name.equals(stateName)) // until match is found,
			 {
			
				 cur = cur.nextNode; // move to next link
			 
				 if(cur == null)
			 return false; 
				 
			 }
			 if(cur==first) 
			 first = cur.nextNode; 
			 else 
			 
			 
			 if(cur==last) 
			 
	  
			return false;
			 
			return false;
	        }
		
		
		}//end LinkedList class
	
	/*
	 * This method hashes an index value for the node given based on it's name, population, and deaths
	 * 
	 * @param takes in a Node
	 *	 @return the hashed index value for the node
	 */
	
	public int HashingFunc(Node theNode) {
        int hashVal = 0;

        for (char eachChar : theNode.name.toCharArray()) {
            hashVal += eachChar;
        }

        hashVal += theNode.population + theNode.deaths;
        return hashVal % 101;  
        
    }//end hashing
	
	
	/*
	 * This method inserts a node in the correct hashed index value
	 * 
	 * @param state name
	 * @param population of given state
	 * @param deaths of given state
	 */
	
	public void insert(String state, long population ,long deaths) {
       
		Node theNode = new Node(state , population , deaths);
		
		
		int hashVal = HashingFunc(theNode);
		
        if (hashArr[hashVal] == null) {
            hashTableSize--;

            hashArr[hashVal] = new LinkedList(theNode);		//if there is no linkedList already there, create one
        } 
        else 
        {
            hashArr[hashVal].insertIntoLinkedList(theNode);
        }
    }
	
	/*
	 * This method prints out the 101 items in the hash table, whether it's empty or not
	 * 
	 */
	 public void display() 
	 {
	        for (int j = 0; j < hashArr.length; j++) 
	        {
	           
	        	if (hashArr[j] == null) 
	        	{
	                System.out.println((j + 1) + ". Empty");
	                continue;
	            }

	            for (Node node = hashArr[j].first; node != null; node = node.nextNode)
	            {
	                System.out.print((j + 1) + ". ");

	                node.printNode();
	            }
	        }
	    }
	 
	 /*
		 * This method finds the node with given state name, population, and deaths
		 * 
		 * @param takes in state name as a parameter
		 * @param population of state
		 * @param deaths of state given
		 *	 @return the index value if found, or -1 if not found
		 */
	 
	 public int find(String state , long population , long deaths)
	 {
		 Node theNode = new Node(state , population , deaths);
		 int hashVal = HashingFunc(theNode);
		 int indexValofState;
		 
		 if(hashArr[hashVal] == null)
		 {
			 return -1;
		 }
		 else
		 {
			Node searchForNode = hashArr[hashVal].find(state);
			indexValofState = searchForNode != null ? hashVal : -1;	//did searchForNode get a val>
																	//if yes return it's hash index
			
		 }
		 
		 return ++indexValofState;
	 }

	 /*
		 * This method deletes the node with given state name, population ,and deaths
		 * 
		 * @param takes in state name as a parameter
		 * @param population
		 * @param deaths of state
		 */
	 
	 public void delete(String state, long population , long deaths) 
	 {
		 Node theNodeCreated = new Node(state , population , deaths);
		 int hashVal = HashingFunc(theNodeCreated);
		 
		 if(hashArr[hashVal] == null)
		 {
			 System.out.println("State not found");
			 return;
		 }
		 else
		 {
			 hashArr[hashVal].remove(state);
			 System.out.println("Successfully removed " + state);
		 }
		 
	 }
	 
	 /*
		 * This method prints the number of indexes that are empty , and indexes with collisions
		 * 
		 */
	 
	 public void printEmptyAndCollisions() {
	        int countCollisions = 0;

	        for (LinkedList list : hashArr) {
	            if (list == null) {
	                continue;
	            }


	            if (list.first != list.last) {
	                countCollisions++;
	            }
	        }

	        System.out.println("Hash table has " + hashTableSize + " empty spaces and " + countCollisions + " collisions.");
	    }

}//end HashTable class

	

