/**
* This class creates a binary search tree, and has methods that inserts and deletes nodes
* This class also creates a node before inserting in the BST 
*
* @author Nour Beleih
* @version 3/26/2021
*/


import java.util.ArrayList;
import java.util.Vector;

public class BinarySearchTree {
	
	public Node root;
	public ArrayList<String> path = new ArrayList<String>();
	public Node arr[] = new Node[20];

	
	public BinarySearchTree() 
	{
		
		root = null;
	}
	
	
	/*
	 * This method creates a node and inserts it in the BST
	 * 
	 * @param name parameter is based on the name of the state
	 * @param DR is the death rate that is calculated before it is inserted in the BST
	 *	 @return void
	 */
	public void insert(String name , double DR) 
	{
		
		Node newNode = new Node(name , DR);
		
		if(root == null) 
		{
			
			root = newNode;
		}
		
		else 
		{
			Node cur = root;
			Node parent;
			
			while(true)
			{
				parent = cur;
				
				
				if(newNode.stateName.compareTo(cur.stateName) < 0) 
				{ //is the new state name bigger? if yes enter here
					
					cur = cur.leftChild;
					
					if(cur == null)
					{
						parent.leftChild = newNode;
						return;
					}
					
				}//end checkWhichisBigger if statement
				
				else	//if its not bigger? go left
				{
					cur = cur.rightChild;
					
					if(cur == null) 
					{
						parent.rightChild = newNode;
						return;
						
					}
					
				}
				
			}//end while loop
			
		}//end else
		
	}//end insert method
	
	
	/*
	 * This method finds the node with the parameter name given
	 * 
	 * @param name of the state for the node to be found
	 *	 @return double DR value of the node
	 */
	public double find(String name) 
	{
		
		Node cur = root;
		
		if(cur == null) 	//check if root is empty
		{
			
			return -1;
		}
		else 
		{
			
			while(!cur.stateName.equals(name) && cur != null) 
			{
				int checkWhichisBigger = name.compareTo(cur.stateName);		//if name given is bigger or not

				if(checkWhichisBigger > 0)		// name is bigger? go right
				{
					path.add(cur.stateName);
					cur = cur.rightChild;
				}
				
				else
				{
					path.add(cur.stateName);
					cur = cur.leftChild;
				}
				if(cur == null)
				{
					return 0;
				}
				
			}//end while loop
			
		}//end 1st else statement 
		
		return cur.DR;
	}//end find method
	
	/*
	 * This method cuts off the node's name given
	 * 
	 * @param name of the state name of the node object
	 *	 @return void
	 */
	public void delete(String name)
	{
		
		//Case 1: node to be deleted doesn't have any children
		
		Node cur = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		while(!cur.stateName.equals(name))
		{
			parent = cur;
			int checkWhichisBigger = name.compareTo(cur.stateName);	//returns 1 if name is bigger
			
			if(checkWhichisBigger < 0)
			{
				isLeftChild = true;
				cur = cur.leftChild;
			}
			else
			{
				isLeftChild = false;
				cur = cur.rightChild;
			}
			if(cur == null) {
				return;
			}
			
		}//end while statement
		
		if(cur.rightChild == null && cur.leftChild == null) 
		{
			
			if(cur == root)
			{
				root = null;
			}
			else if(isLeftChild) 
			{
				parent.leftChild = null;
			}
			else 
			{
				parent.rightChild = null;
			}
			
		}//end if statement
		
		//continue here
		
		//Case 2: if node that is to be deleted has  child
		
		else if(cur.rightChild == null) 
		{
			
			if(cur == root) 
			{
				
				root = cur.leftChild;
			}
			else if(isLeftChild) 
			{
				parent.leftChild = cur.leftChild;
			}
			else
			{
				parent.rightChild = cur.leftChild;
			}
			
		}//end else if
		
		else if(cur.leftChild == null)
		{
			if(cur == root)
			{
				root = cur.rightChild;
			}
			else if(isLeftChild)
			{
				parent.leftChild = cur.rightChild;
			}
			else
			{
				parent.rightChild = cur.rightChild;
			}
		}//end else if statement
		
		//Case 3: if node has 2 children
		
		else
		{
			
			Node successor = gettingSuccessor(cur);
			
			if(cur == root) 
			{
				root = successor;
			}
			
			else if(isLeftChild)
			{
				parent.leftChild = successor;
			}
			else
			{
				parent.rightChild = successor;
			}
			
			successor.leftChild = cur.leftChild;	//check this
			
			
		}//end else 
		
		
		
	}//end delete method
	
	/*
	 * This method traverses the BST checkOrder
	 * 
	 * @param theLocalRoot is the root based for the tree to be traversed
	 *	 @return void
	 */
	public void printInorder(Node theLocalRoot)
	{
		
		if(theLocalRoot == null) {
			return;
		}
		
		printInorder(theLocalRoot.leftChild);
		
		System.out.println(theLocalRoot);
		printInorder(theLocalRoot.rightChild);
		
	}//end printInorder method
	
	/*
	 * This method traverses the BST preorder
	 * 
	 * @param DR theLocalRoot is the root for the BST to be printed
	 *	 @return void
	 */
	public void printPreorder(Node theLocalRoot) {
		
		
		if(theLocalRoot == null) {
			return;
		}
		
		System.out.println(theLocalRoot);
		printPreorder(theLocalRoot.leftChild);
		printPreorder(theLocalRoot.rightChild);
	}
	
	
	/*
	 * This method traverses the BST postorder
	 * 
	 * @param theLocalRoot is the root of the BST
	 *	 @return void
	 */
	public void printPostorder(Node theLocalRoot) {
		
		if(theLocalRoot == null) {
			return;
		}
		
		printPreorder(theLocalRoot.leftChild);
		printPreorder(theLocalRoot.rightChild);
		System.out.println(theLocalRoot);

		
	}
	
	/*
	 * This method finds the successor of the node passed
	 * 
	 * @param deleteNode is the node that is to be deleted in the BST
	 *	 @return Node, the successor of the parameter node
	 */
	public Node gettingSuccessor(Node deleteNode) 
	{
				
		Node successor = deleteNode;
		Node successorParent = deleteNode;

		Node cur = deleteNode.rightChild;
		
		while(cur != null) 
		
		{ 
	
			successorParent = successor;
			successor = cur;
			cur = cur.leftChild;
		}
		
		if(successor != deleteNode.rightChild)
		{
		
			successorParent.leftChild = successor.rightChild;
			successor.rightChild = deleteNode.rightChild;
		}
		
		return successor;
		
	}
	
	
	public Node getRoot()
	{
		return this.root;
	}
		
	/*
	 * This method searches for a given node
	 * 
	 * @param root is the root node for the given BST
	 *  @param name is the node's statename in the BST
	 *	 @return true if found false if not found
	 */
	public boolean searchFor(Node root, String name)
	{
		Node cur = root;
	    while(cur != null)
	    {
	        if(cur.stateName.equals(name))
	        {
	        	return true;

	        }
	        if(cur.stateName.compareTo(name) > 0) 
	        {
	        	cur = cur.leftChild;

	        }
	        else {
	        	cur = cur.rightChild;
	        }
	    }

	    return false;
	}
	
	/*
	 * This method prints the topstates of the given number c
	 * 
	 *  @param c, the number of nodes to be printed
	 */
	public void printTopStates(int c) {

		Node Node2 = new Node(null, 100);
		Node[] arr2 = new Node[c];

		int length = arr2.length;

		for (int i = 0; i < length; i++) {
			arr2[i] = Node2;
			checkOrder2(root, Node2, arr2, i);
		}

		

		for (int j = 0; j < c; j++) {
			System.out.println(arr2[j].stateName);
		}

	}

	
	private void checkOrder2(Node res, Node max, Node aNode[], int i) {
		if (res != null) {
			checkOrder2(res.leftChild, max, aNode, i);

			if (max.DR < res.DR) {
				if (!(checkArray2(aNode, res, i))) {
					max = res;

					if (max.DR > aNode[i].DR) {
						aNode[i] = max;
					}

				}
			}
			checkOrder2(res.rightChild, max, aNode, i);
		}
	}

	
	private boolean checkArray2(Node aNode[], Node res, int length) {
		for (int i = 0; i < length; i++) {
			if (aNode[i] == res)
				return true;
		}
		return false;
	}

	/*
	 * This method finds the max DR of the BST
	 * 
	 * @param root, root of the BST
	 *  @return the highest DR
	 */
	public double findMax(Node root)
	{ 
	    
	        if (root == null)
	            return Double.MIN_VALUE;
	  
	        double res = root.DR;
	        double leftSide = findMax(root.leftChild);
	        double rightSide = findMax(root.rightChild);
	  
	        if (leftSide > res)
	            res = leftSide;
	        if (rightSide > res)
	            res = rightSide;
	        
	        
	        return res;
	    }
	
	
	/*
	 * This method prints the bottomStates regarding DR
	 * 
	 *  @param c, the number of nodes to be printed
	 */
	
	public void printBottomStates(int c) {

		Node Node2 = new Node(null, 100);
		Node[] arr = new Node[c];

		int length = arr.length;

		for (int i = 0; i < length; i++) 
		{
			arr[i] = Node2;
			checkOrder(root, Node2, arr, i);
		}

		

		for (int j = 0; j < c; j++) {
			System.out.println(arr[j].stateName);
		}

	}

	
	private void checkOrder(Node res, Node min, Node aNode[], int i) {
		if (res != null) {
			checkOrder(res.leftChild, min, aNode, i);

			if (min.DR > res.DR) {
				if (!(checkArray(aNode, res, i))) {
					min = res;

					if (min.DR < aNode[i].DR) {
						aNode[i] = min;
					}

				}
			}
			checkOrder(res.rightChild, min, aNode, i);
		}
	}

	
	private boolean checkArray(Node aNode[], Node res, int length) {
		for (int i = 0; i < length; i++) {
			if (aNode[i] == res)
				return true;
		}
		return false;
	}


}// end of BinarySearchTree class
