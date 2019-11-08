package pack;

/** This class is a generic Queue.  - See References in class main.
 * 
 * @author User
 *
 * @param <T>
 "*/
public class MyQueue<T> {
	AVLNode[] arrayQ;
	int first,
	 last;
	int size;
	
	/** Constructor method for class MyQueue.  - See References in class main.
	 * 
	 * @param numNodes
	 */
	public MyQueue(int numNodes) {
		first = -1;
		last = 0;
		size = 0;
		arrayQ = new AVLNode[numNodes + 10];
	}
	
	/** Returns true/false depending on the value of last. - See References in class main.
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (last<=0);
	}
	
	/** Returns the size of the queue. - See References in class main.
	 * 
	 * @return
	 */
	public int getSize() {
		return size;
	}
	
	/** Returns the value of the first node in the queue. - See References in class main.
	 * 
	 * @return
	 */
	public AVLNode<T> peek() {
		if(isEmpty()) {
			return null;
		}
		return arrayQ[first];
	}
	
	/** Adds the provided node to the queue. - See References in class main.
	 * 
	 * @param node
	 */
	public void enqueue(AVLNode<T> node) {
		if (last==0) {
			first++;
		}
		if(node != null) { //Only add the node to the queue if it is not null.
			arrayQ[last++] = node;
		}
		size++;
	}
	
	/** Removes and returns the head node from the queue. - See References in class main.
	 * 
	 * @return
	 */
	public AVLNode<T> dequeue() {
		if(isEmpty()) {
			return null;
		}
		AVLNode<T> deleted = arrayQ[first]; //Set the value to be deleted as the first item in the queue

		for(int i=0; i+1<last; i++) { //Go through the queue and move all items up in the queue
			arrayQ[i] = arrayQ[i+1];
		}
		last--; //Decrement first
		size--; //Decrement size
		if(last == 0) { //If last = 0, the queue is empty
			first--;
		}
		return deleted; //Return the deleted node.
	}
	
}//" End of class MyQueue - See References in class main.
