
public class BSTRelation<X extends Comparable<X>,Y extends Comparable<Y>> 
implements Relation<X,Y>{

	private BSTRelation.Node<X, Y> root;

	public BSTRelation(){
		root = null;
	}

	//INNER CLASS FOR NODES//

	private static class Node<X extends Comparable<X>,Y extends Comparable<Y>>{
		protected Pair<X,Y> element;
		protected Node<X,Y> left, right;

		protected Node(X xValue, Y yValue){
			this.element = new Pair<X,Y>(xValue, yValue);
			left = null;
			right = null;
		}

		//INNER CLASS OF PAIRS//

		private static class Pair<X extends Comparable<X>,Y extends Comparable<Y>> 
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
	public Y[] correspondingYValues(X xValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public X[] correspondingXValues(Y yValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void empty() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean insert(X xValue, Y yValue) {
		int direction = 0;
		BSTRelation.Node<X,Y> parent = null, curr = root;
		for (;;) {
			BSTRelation.Node<X,Y> ins = new BSTRelation.Node<X,Y> (xValue, yValue);
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
		// TODO Auto-generated method stub

	}

	@Override
	public void removeContainingY(Y yValue) {
		// TODO Auto-generated method stub

	}
}