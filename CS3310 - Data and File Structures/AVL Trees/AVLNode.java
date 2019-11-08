package pack;

/** This is a generic Node class for the AVLTree. - See References in class main.
 * 
 * @author David Moussalli
 *
 * @param <T>
 "*/
public class AVLNode<T> {
		int height,				//Height of the node
			balanceFactor;		//Balance Factor of the node...rChild.height - lChild.height
		T key;					//Generic key value 
	AVLNode<T> lChild, rChild;	//Left and right child nodes

	/** Constructor for the AVLNode class.
	 * 
	 * @param value - Key value
	 */
	public AVLNode(T value){
		this.key = value;
	}	
}//" End of class AVLNode - See References in class main.
