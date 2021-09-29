
public class Stack {

	
	int maxSize;
	State[] stackAry;
	int top;
	
	/**
	* Constructor that initiates object created with values
	*
	* @param takes the size of the stack
	* @return Stack is created
	*/
	public Stack(int size) {
		
		this.maxSize = size;
		stackAry = new State[size];
		top = -1;
	}

	public int getSize() {
		return maxSize;
	}

	public void setSize(int size) {
		this.maxSize = size;
	}

	public State[] getStackAry() {
		return stackAry;
	}

	public void setStackAry(State[] stackAry) {
		this.stackAry = stackAry;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}
	
	/**
	* This method pops/removes items from the stack
	*
	* @return State Object
	*/
	
	public State pop() {
		
		return stackAry[top--];
	}
	
	/**
	* This method pushes State object in to the stack
	*
	* @param State object
	*/
	public void push(State obj) {
		
		stackAry[++top] = obj;
	}
	
	/**
	* Checks if the stack is empty
	*
	* @return boolean value
	*/
	public boolean isEmpty() {
		return (top == -1);
	}
	
	/**
	* Checks whether stack is full
	*
	* @return boolean value
	*/
	public boolean isFull() {
		return (top == maxSize - 1);
	}
	/**
	*This method returns the state that is at the top of the stack
	*
	* @return a State object
	*/
	
	public State peek() {
		return stackAry[top];
	}
	
	/**
	* This method loops from the back of the stack and prints each element
	*/
	public void printStack() {
		
		
		for(int i = maxSize - 1; i >= 0; i--) {
			
			if(stackAry[i] != null) {
				System.out.println(stackAry[i]);

			}
			
		}
	}
}
