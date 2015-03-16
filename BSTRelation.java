import java.util.ArrayList;

public class BSTRelation<X extends Comparable<X>,Y extends Comparable<Y>> 
implements Relation<X,Y>{

	private BSTRelation.Node<X, Y> root;

	public BSTRelation(){
		root = null;
	}

	//INNER CLASS FOR NODES//

	protected static class Node<X extends Comparable<X>,Y extends Comparable<Y>>{
		protected Pair<X,Y> element;
		protected Node<X,Y> left, right;

		protected Node(X xValue, Y yValue){
			this.element = new Pair<X,Y>(xValue, yValue);
			left = null;
			right = null;
		}
		
		public String toString(){
			String description = "{ "+element.xValue+", "+element.yValue+" }";
			return description;
		}

		//INNER CLASS OF PAIRS//

		protected static class Pair<X extends Comparable<X>,Y extends Comparable<Y>> 
		implements Comparable<Pair<X,Y>>{

			protected X xValue;
			protected Y yValue;

			protected Pair (X xValue, Y yValue){
				this.xValue = xValue;
				this.yValue = yValue;
			}

			public X getXValue() {
				return xValue;
			}

			public Y getYValue() {
				return yValue;
			}

			@Override
			public int compareTo(Pair<X,Y> that) {
				int cmp = this.getXValue().compareTo(that.getXValue());
				if (cmp == 0)
					cmp = this.getYValue().compareTo(that.getYValue());
				return cmp;
			}
		}
		// END OF INNER CLASS OF PAIRS//

		public Node<X,Y> deleteTopmost() {
			if (this.left == null)
				return this.right;
			else if (this.right == null)
				return this.left;
			else { // this node has two children
				this.element = this.right.getLeftmost();
				this.right = this.right.deleteLeftmost();
				return this;
			}
		}

		private Pair<X,Y> getLeftmost() {
			Node<X,Y> curr = this;
			while (curr.left != null)
				curr = curr.left;
			return curr.element;
		}

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

	@Override
	public boolean contains(X xValue, Y yValue) {
		int direction = 0;
		Node<X,Y> target = new Node<X,Y>(xValue, yValue);
		BSTRelation.Node<X,Y> curr = root;
		for (;;) {
			if (curr == null)
				return false;
			direction = target.element.compareTo(curr.element);
			if (direction == 0)
				return true;
			else if (direction < 0)
				curr = curr.left;
			else
				curr = curr.right;
		}
	}

	@Override
	public ArrayList<Y> correspondingYValues(X xValue) {
		int direction = 0;
		BSTRelation.Node<X,Y> curr = root;
		ArrayList<Y> foundValues = new ArrayList<Y>();
		for (;;) {
			if (curr == null){
				return foundValues;
			}
			direction = xValue.compareTo(curr.element.getXValue());
			if (direction==0){
				break;
			}
			else if(direction<0){
				curr = curr.left;
			}
			else if(direction>0){
				curr = curr.right;
			}
		}
		foundValues = traverseX(curr, xValue);
		return foundValues;
	}
	
	private ArrayList<Y> traverseX(Node<X,Y> node, X xValue){
		ArrayList<Y> foundValues = new ArrayList<Y>();
		if (node.left !=null){
			ArrayList<Y> leftMatches = traverseX (node.left, xValue);
			for (Y correspondingValue: leftMatches){
				foundValues.add(correspondingValue);
			}
		}
		if (node.element.getXValue().compareTo(xValue)==0){
			foundValues.add(node.element.getYValue());
		}
		if (node.right!=null){
			ArrayList<Y> rightMatches = traverseX (node.right, xValue);
			for (Y correspondingValue: rightMatches){
				foundValues.add(correspondingValue);
			}
		}
		return foundValues;
	}

	@Override
	public ArrayList<X> correspondingXValues(Y yValue) {
		ArrayList<X> matchedValues = traverseY(this.root, yValue);
		return matchedValues;
	}

	private ArrayList<X> traverseY(Node<X,Y> node, Y yValue){
		ArrayList<X> foundValues = new ArrayList<X>();
		if (node.left !=null){
			ArrayList<X> leftMatches = traverseY (node.left, yValue);
			for (X correspondingValue: leftMatches){
				foundValues.add(correspondingValue);
			}
		}
		if (node.element.getYValue().compareTo(yValue)==0){
			foundValues.add(node.element.getXValue());
		}
		if (node.right!=null){
			ArrayList<X> rightMatches = traverseY (node.right, yValue);
			for (X correspondingValue: rightMatches){
				foundValues.add(correspondingValue);
			}
		}
		return foundValues;
	}


	@Override
	public void empty() {
		root = null;

	}

	@Override
	public boolean insert(X xValue, Y yValue) {
		int direction = 0;
		BSTRelation.Node<X,Y> parent = null, curr = root;
		BSTRelation.Node<X,Y> ins = new BSTRelation.Node<X,Y> (xValue, yValue);
		for (;;) {
			if (curr == null) {
				if (root == null)
					root = ins;
				else if (direction < 0)
					parent.left = ins;
				else
					parent.right = ins;
				return true;
			}
			direction = ins.element.compareTo(curr.element);
			if (direction == 0)
				return false;
			parent = curr;
			if (direction < 0)
				curr = curr.left;
			else
				curr = curr.right;
		}
	}

	@Override
	public boolean remove(X xValue, Y yValue) {
		BSTRelation.Node<X,Y> target = new BSTRelation.Node<X,Y>(xValue, yValue);
		int direction = 0;
		BSTRelation.Node<X,Y> parent = null, curr = root;
		for (;;) {
			if (curr == null)
				return false;
			direction = target.element.compareTo(curr.element);
			if (direction == 0) {
				BSTRelation.Node<X,Y> del = curr.deleteTopmost();
				if (curr == root)
					root = del;
				else if (curr == parent.left)
					parent.left = del;
				else
					parent.right = del;
				return true;
			}
			parent = curr;
			if (direction < 0)
				curr = parent.left;
			else
				// direction > 0
				curr = parent.right;
		}
	}

	@Override
	public void removeContainingX(X xValue) {
		
	}	

	@Override
	public void removeContainingY(Y yValue) {
		
	}
	
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		if (root!=null){
			builder.append(description(root));
		}
		return builder.toString();
	}
	
	public String description(Node<X,Y> node){
		StringBuilder builder = new StringBuilder();
		if (node.left!=null){
			String leftDescription = description(node.left);
			builder.append(leftDescription);
		}
		builder.append(node.toString());
		if (node.right!=null){
			String rightDescription = description(node.right);
			builder.append(rightDescription);
		}
		return builder.toString();
	}
}