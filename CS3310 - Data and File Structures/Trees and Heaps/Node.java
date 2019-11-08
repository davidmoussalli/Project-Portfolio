package pack;

public class Node {
	Node lChild,	//Left child node
 		 rChild,	//Right child node
 		 parent;	//Parent node
	private int depth,		//Depth of node
				height,		//Height of the tree
				size;		//Size of tree
	private String name;	//Name stored in Node
	
	
	/** Constructor for class Node.
	 *  Initializes variables.
	 * 
	 * @param name - name stored in Node.
	 * @param parent - parent node. Root's parent is null.
	 */
	public Node(String name, Node parent) {
		this.name = name;
		this.parent = parent;
		lChild = null;
		rChild = null;
		depth = 0;
		size = 0;
		height = 0;
	}
	
	public boolean isLeaf() {
		return ((lChild == null) && (rChild == null));
	}

	public void setHeight(int height) {
		this.height = height;
	}
	public void setName(String name) {
		this.name = name;
	}
	
//============================================= GET Methods ==============================
	public int getDepth() {
		return depth;
	}
	public int getSize() {
		return size;
	}
	public int getHeight() { //Returns the height of the subtree
		return height;
	}
	public String getName() {
		return name;
	}
}//End of class Node
