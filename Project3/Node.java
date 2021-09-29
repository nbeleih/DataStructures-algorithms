
/**
* This class creates a Node that is later inserted into a BST using another class
*
* @author Nour Beleih
* @version 3/26/2021
*/

public  class Node {

	
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public double getDR() {
		return DR;
	}

	public void setDR(double dR) {
		DR = dR;
	}

	public String stateName;
	public double DR;
	public Node leftChild;
	public Node rightChild;
	int hd;
	
	public Node(String stateName , double DR) {
		
		setStateName(stateName);
		setDR(DR);
		leftChild = null;
		rightChild = null;
		
	}// end constructor
	
	//HAVE A toString() here!!!! so you could use the printInorder() method to print both name and DR
	
	public String toString() 
	{
		
		return String.format("%13s\t\t%5.2f" , getStateName() , getDR());
	}
	
	}//end Node class
	