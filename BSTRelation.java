/*
 * Class BSTRelation implements the interface Relation and stores binary relations
 * consisting of type 'X' and type 'Y' which both extend Comparable. The implementation
 * is a binary search tree which stores nodes. Each node stores one element which is a 
 * an instance of the class 'Pair'. The class 'Pair' stores the values of X and Y and 
 * implements the compareTo method. 
 * 
 */

import java.util.ArrayList;

public class BSTRelation<X extends Comparable<X>,Y extends Comparable<Y>> 
	implements Relation<X,Y>{

	private BSTRelation.Node<X, Y> root;

	/**
	 * Constructor for binary search tree relation
	 */
	public BSTRelation(){
		root = null;
	}

	/**
	 * INNER CLASS FOR THE NODES OF THE BINARY SEARCH TREE
	 */
	private static class Node<X extends Comparable<X>,Y extends Comparable<Y>>{
		
		protected Pair<X,Y> element;
		protected Node<X,Y> left, right;

		/**
		 * Constructor for Node
		 * @param xValue	Comparable X value
		 * @param yValue	Comparable Y value
		 */
		protected Node(X xValue, Y yValue){
			this.element = new Pair<X,Y>(xValue, yValue);
			left = null;
			right = null;
		}

		/**
		 * INNER CLASS TO STORE PAIRS OF VALUES
		 */
		private static class Pair<X extends Comparable<X>,Y extends Comparable<Y>> 
		implements Comparable<Pair<X,Y>>{

			protected X xValue;
			protected Y yValue;
			
			/**
			 * Constructor for Pair
			 * @param xValue	Comparable X value within the pair
			 * @param yValue	Comparable Y value within the pair
			 */
			protected Pair (X xValue, Y yValue){
				this.xValue = xValue;
				this.yValue = yValue;
			}

			/**
			 * Method to compare the values of two Pairs
			 */
			public int compareTo(Pair<X,Y> that) {
				// Compare the X values
				int cmp = this.xValue.compareTo(that.xValue);
				// If the Y values if the X values are identical
				if (cmp == 0)
					cmp = this.yValue.compareTo(that.yValue);
				return cmp;
			}
			
			/**
			 * Method to generate a String describing the values contained in the pair
			 */
			public String toString(){
				String description = "{"+xValue+": "+yValue+"}";
				return description;
			}
		}
		// END OF INNER CLASS OF PAIRS//
		
		/**
		 * Method to determine the node to replace this node upon deletion
		 * @return Node	node which will replace this node upon deletion from the BST
		 * Note - code taken from the example implementation provided in lecture notes
		 */
		public Node<X,Y> deleteTopmost() {
			if (this.left == null)
				// if this node has no left subtree
				// the right subtree takes its place in the BST
				return this.right;
			else if (this.right == null)
				// if this node has no right subtree
				// the left subtree takes its place in the BST
				return this.left;
			else { // this node has two children
				// replace the contents of this node with the contents of
				// the leftmost node in the right subtree
				this.element = this.right.getLeftmost();
				// then remove the duplicate node from the right subtree
				this.right = this.right.deleteLeftmost();
				return this;
			}
		}
		
		/**
		 * Method to determine the leftmost node in a subtree
		 * @return curr.element	Pair from the leftmost node in the subtree
		 * Note - code taken from the example implementation provided in lecture notes
		 */
		private Pair<X,Y> getLeftmost() {
			Node<X,Y> curr = this;
			while (curr.left != null)
				// iterate until the leftmost node in the subtree is found
				curr = curr.left;
			return curr.element;
		}
		
		/**
		 * Method to delete the leftmost node from a subtree and determine the node
		 * to be linked for the right subtree
		 * @return Node	Node representing the element which links to the right subtree
		 * Note - code taken from the example implementation provided in lecture notes
		 */
		public Node<X,Y> deleteLeftmost() {
			if (this.left == null)
				return this.right;
			else {
				Node<X,Y> parent = this, curr = this.left;
				while (curr.left != null) {
					parent = curr;
					curr = curr.left;
				}
				parent.left = curr.right;
				return this;
			}
		}
	}

	/**
	 * Method to determine whether a given X,Y pair is contained in the relation
	 * @return boolean	boolean value indicating whether the X,Y pair is contained in the relation
	 * 
	 * Algorithm: BST search
	 * Time complexity: best = O(log n); worst = O(n)
	 * Note: Code adapted from implementation provided in lecture notes
	 */
	public boolean contains(X xValue, Y yValue) {
		// integer value to store return value of compareTo method
		int direction = 0;
		// Generate a new node for comparison to existing nodes
		Node<X,Y> target = new Node<X,Y>(xValue, yValue);
		// Set current node to the root node
		Node<X,Y> curr = root;
		for (;;) {
			// if curr is null, there are no more nodes to search through
			if (curr == null)
				return false;
			direction = target.element.compareTo(curr.element);
			// A match is identified, terminate the search
			if (direction == 0)
				return true;
			else if (direction < 0)
				// target value is less than current node's value
				curr = curr.left;
			else // target value is greater than current node's value
				curr = curr.right;
		}
	}

	/**
	 * Method to return values of Y which are paired with the value X in the relation.
	 * @param xValue	Comparable X to be found in the relation
	 * @return ArrayList<Y>	ArrayList containing all values of Y paired with X in the relation.
	 * 
	 * Algorithm: Combination of BST search and in-order traversal of all elements in the BST
	 * Time complexity: best = O(log n); worst = O(n)
	 * Note: function 2 of specifications requires this and the proceeding method below (findRemainingYValues)
	 */
	public ArrayList<Y> correspondingYValues(X xValue) {
		int direction = 0;
		BSTRelation.Node<X,Y> curr = root;
		// ArrayList stores values of Y associated with xValue in relation
		ArrayList<Y> foundValues = new ArrayList<Y>();
		// Identify the first node containing a matching X value
		for (;;) {
			// BST search algorithm
			if (curr == null){
				// if curr is null, there are no more nodes to search through
				return foundValues;
			}
			direction = xValue.compareTo(curr.element.xValue);
			if (direction==0){
				// When a match is identified terminate search
				break;
			}
			else if(direction<0){
				curr = curr.left;
			}
			else if(direction>0){
				curr = curr.right;
			}
		}
		// If X is found then check all subtrees of found node for additional
		// matching pairs.
		foundValues = findRemainingYValues(curr, xValue);	
		return foundValues;
	}
	
	/**
	 * Method to traverse the subtrees of the first element with a matching X value
	 * @param node Node the current node in the search
	 * @param xValue Comparable X to be found
	 * @return foundValues ArrayList of Comparable Y value associated with given X
	 */
	private ArrayList<Y> findRemainingYValues(Node<X,Y> node, X xValue){
		ArrayList<Y> foundValues = new ArrayList<Y>();
		// In-order traversal
		if (node.left !=null){
			// Search the left subtree for matches
			ArrayList<Y> leftMatches = findRemainingYValues(node.left, xValue);
			for (Y correspondingValue: leftMatches){
				foundValues.add(correspondingValue);
			}
		}
		// If the X value of the node matches add the associated Y value to the ArrayList
		if (node.element.xValue.compareTo(xValue)==0){
			foundValues.add(node.element.yValue);
		}
		// Search the right subtree for matches
		if (node.right!=null){
			ArrayList<Y> rightMatches = findRemainingYValues(node.right, xValue);
			for (Y correspondingValue: rightMatches){
				foundValues.add(correspondingValue);
			}
		}
		return foundValues;
	}

	/**
	 * Method to return values of X which are paired with the value Y in the relation. 
	 * @param yValue	Comparable Y to be found in the relation
	 * @return ArrayList<X>	ArrayList containing all values of X paired with Y in the relation.
	 * 
	 * Algorithm: Search performed using in-order traversal of BST
	 * Time complexity: O(n)
	 * Note: function 3 of specifications requires this and the proceeding method below (findAssociatedXValues)
	 */
	public ArrayList<X> correspondingXValues(Y yValue) {
		ArrayList<X> matchedValues = findAssociatedXValues(this.root, yValue);
		return matchedValues;
	}
	
	/**
	 * Method to find all Pairs containing given value Y using in-order traversal
	 * @param node	Node currently the focus of the search
	 * @param yValue	Comparable Y to be found in the BST
	 * @return foundValues	ArrayList containing the X values associated with given Y
	 */
	private ArrayList<X> findAssociatedXValues(Node<X,Y> node, Y yValue){
		ArrayList<X> foundValues = new ArrayList<X>();
		// traverse the left subtree
		if (node.left !=null){
			ArrayList<X> leftMatches = findAssociatedXValues(node.left, yValue);
			// add the matches from the left subtree to the ArrayList
			for (X correspondingValue: leftMatches){
				foundValues.add(correspondingValue);
			}
		}
		// If the Y value of the node matches then add X value to the ArrayList
		if (node.element.yValue.compareTo(yValue)==0){
			foundValues.add(node.element.xValue);
		}
		// traverse the right subtree
		if (node.right!=null){
			ArrayList<X> rightMatches = findAssociatedXValues(node.right, yValue);
			// add the matches from the right subtree to the ArrayList
			for (X correspondingValue: rightMatches){
				foundValues.add(correspondingValue);
			}
		}
		return foundValues;
	}

	/**
	 * Method to clear the relation of all existing pairs of X,Y values.
	 * 
	 * Algorithm: Set the root element of the BST to null
	 * Time complexity: O(1)
	 */
	public void empty() {
		root = null;

	}

	/**
	 * Method to add a pair of X,Y values to the relation.
	 * @param xValue	Comparable X to be added to the relation
	 * @param yValue	Comparable Y to be added to the relation
	 * @return boolean	boolean to denote the successful addition of the pair to the relation.
	 * 
	 * Algorithm: BST insertion algorithm
	 * Time complexity: best = O(log n); worst = O(n)
	 * Note - Code adapted from the implementation provided in lectures
	 */
	public boolean insert(X xValue, Y yValue) {
		int direction = 0;
		// Set parent to null, and set curr to the BST's root
		BSTRelation.Node<X,Y> parent = null, curr = root;
		BSTRelation.Node<X,Y> ins = new BSTRelation.Node<X,Y> (xValue, yValue);
		for (;;) {
			if (curr == null) {
				if (root == null)
					// the new node is the root node
					root = ins;
				else if (direction < 0)
					// Add the node to the parent's left subtree
					parent.left = ins;
				else
					// Add the node to the parent's right subtree
					parent.right = ins;
				return true;
			}
			// compare the value of the current node to the value of node to be inserted
			direction = ins.element.compareTo(curr.element);
			if (direction == 0)
				// if the node matches and existing node, it cannot be added
				return false;
			// increment the current node to either the left or right subtree
			parent = curr;
			if (direction < 0)
				curr = curr.left;
			else
				curr = curr.right;
		}
	}

	/**
	 * Method to remove a given X,Y pair from the relation.
	 * @param xValue	Comparable X value to be removed.
	 * @param yValue	Comparable Y value to be removed.
	 * @return boolean	boolean to denote the successful removal of the pair from the relation.
	 * 
	 * Algorithm: BST deletion algorithm
	 * Time complexity: best = O(log n); worst = O(n)
	 * Note - Code adapted from the implementation provided in lectures
	 */
	public boolean remove(X xValue, Y yValue) {
		BSTRelation.Node<X,Y> target = new BSTRelation.Node<X,Y>(xValue, yValue);
		int direction = 0;
		// Set parent to null, and set curr to the BST's root node
		BSTRelation.Node<X,Y> parent = null, curr = root;
		for (;;) {
			// if curr is null, there are no more nodes to search through
			if (curr == null)
				return false;
			direction = target.element.compareTo(curr.element);
			// the node matches the given X,Y to be found
			if (direction == 0) {
				this.removeNode(curr, parent);
				return true;
			}
			// the node does not match, so progress to the next node
			parent = curr;
			if (direction < 0)
				curr = parent.left;
			else
				// direction > 0
				curr = parent.right;
		}
	}

	/**
	 * Method to remove all pairs containing a given value of X from the relation	
	 * @param xValue	Comparable X to be removed from the relation.
	 * 
	 * Algorithm: Use BST search algorithm to find first node containing value of X to be
	 * found then use in-order traversal of the subtrees to find remaining nodes with matching value of X.
	 * Time complexity: best = O(log n); worst = O(n)
	 * Note - Note: function 7 of specifications requires this and the proceeding method below (searchAndDeleteX)
	 */
	public void removeContainingX(X xValue) {
		int direction = 0;
		Node<X,Y> curr = root;
		Node<X,Y> parent = null;
		// Identify the first node matching xValue
		for (;;) {
			if (curr == null){
				// if curr is null, there are no more nodes to search through
				return;
			}
			direction = xValue.compareTo(curr.element.xValue);
			if (direction==0){
				// if a node is found that matches the xValue
				// terminate the BST search
				break;
			}
			else if(direction<0){
				parent = curr;
				curr = curr.left;
			}
			else if(direction>0){
				parent = curr;
				curr = curr.right;
			}
		}
		// If X is found then check subtrees for additional instances of matching xValues
		searchAndDeleteX(curr, parent, xValue);
	}
	
	/**
	 * Method to search the subtrees of the first node found containing the matching xValue
	 * @param curr	Node referring to the current Node in the search
	 * @param parent	Node referring to the parent Node of the current Node in the search
	 * @param xValue	Comparable X to be found and removed from the relation
	 */
	private void searchAndDeleteX (Node<X,Y> curr, Node<X,Y> parent, X xValue){
		if (curr.left!=null){
			searchAndDeleteX(curr.left, curr, xValue);
		}
		if (curr.element.xValue.compareTo(xValue)==0){
			removeNode(curr, parent);
		}
		if (curr.right!=null){
			searchAndDeleteX(curr.right, curr, xValue);
		}
	}

	/**
	 * Method to remove all pairs containing a given value of Y from the relation	
	 * @param xValue	Comparable Y to be removed from the relation.
	 * 
	 * Algorithm: Use in-order traversal of the BST to identify and delete the Pairs containing given value Y
	 * Time complexity: O(n)
	 * Note - Note: function 8 of specifications requires this and the proceeding method below (searchAndDeleteY)
	 */
	public void removeContainingY(Y yValue) {
		// if the root element is empty, the BST is empty
		if (root== null){
			return;
		}
		Node<X,Y> curr = root;
		Node<X,Y> parent = null;
		searchAndDeleteY(curr, parent, yValue);
	}
	
	/**
	 * Method to traverse the BST deleting all nodes which contain given value Y
	 * @param curr	Node referring to the current Node in the search
	 * @param parent	Node referring to the parent Node of the current Node in the search
	 * @param yValue	Comparable Y to be found and removed from the relation
	 */
	private void searchAndDeleteY (Node<X,Y> curr, Node<X,Y> parent, Y yValue){
		// traverse the left subtree and remove matching nodes
		if (curr.left!=null){
			searchAndDeleteY(curr.left, curr, yValue);
		}
		// remove the node if the Y value of the node matches the given value
		if (curr.element.yValue.compareTo(yValue)==0){
			removeNode(curr, parent);
		}
		// traverse the right subtree and remove the matching nodes
		if (curr.right!=null){
			searchAndDeleteY(curr.right, curr, yValue);
		}
	}
	
	/**
	 * Method to remove a given Node from the relation
	 * @param toBeRemoved	Node to be removed
	 * @param parent	Node the parent node of the node to be removed
	 * Note: the code used in this method is adapted from the implementation presented in the lecture notes
	 */
	private void removeNode (Node<X,Y> toBeRemoved, Node<X,Y> parent){
		BSTRelation.Node<X,Y> replaceNode = toBeRemoved.deleteTopmost();
		if (toBeRemoved == root)
			// link the remaining subtree to the root
			root = replaceNode;
		else if (toBeRemoved == parent.left)
			// link the remaining subtree to the left link of the parent node
			parent.left = replaceNode;
		else
			// link the remaining subtree to the right link of the parent node
			parent.right = replaceNode;
	}

	/**
	 * Method to describe the Pairs within the relation as a String
	 * @return builder.toString()	String describing the Pairs contained in the relation
	 * 
	 * Algorithm: Use in-order traversal to iterate through all nodes in the BST calling the Pair.to.String() method
	 * Time complexity: O(n)
	 * Note: function 9 of specifications requires this and the proceeding method below (description)
	 */
	public String toString(){
		StringBuilder builder = new StringBuilder();
		if (root!=null){
			// append the results of the description method to the StringBuilder
			builder.append(description(root));
		}
		return builder.toString();
	}

	/**
	 * Method to traverse the nodes in the relation in-order, generating a string describing the contents of the relation
	 * @param node Node reflecting the current location of the traversal
	 * @return builder.toString() String description of the contents of the current node and associated subtrees
	 */
	private String description(Node<X,Y> node){
		StringBuilder builder = new StringBuilder();
		// traverse the left subtree
		if (node.left!=null){
			String leftDescription = description(node.left);
			builder.append(leftDescription);
		}
		// generate a String describing the Pair contained in the Node
		builder.append(node.element.toString());
		// traverse the right subtree
		if (node.right!=null){
			String rightDescription = description(node.right);
			builder.append(rightDescription);
		}
		return builder.toString();
	}
}