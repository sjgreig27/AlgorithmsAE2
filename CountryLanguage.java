
public class CountryLanguage implements Relation<String, String> {
	
	private CountryLanguage.Node root;
	
	public CountryLanguage(){
		root = null;
	}

	@Override
	public boolean contains(String xValue, String yValue) {
		String target = xValue+yValue;
		int direction = 0;
		CountryLanguage.Node curr = root;
		for (;;) {
			if (curr == null)
				return false;
			direction = target.compareTo(curr.composite);
			if (direction == 0)
				return true;
			else if (direction < 0)
				curr = curr.left;
			else
				curr = curr.right;
		}
	}

	@Override
	public String getYValues(String xValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getXValues(String yValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addPair(String xValue, String yValue) {
		int direction = 0;
		String composite = xValue+yValue;
		CountryLanguage.Node parent = null, curr = root;
		for (;;) {
			if (curr == null) {
				CountryLanguage.Node ins = new CountryLanguage.Node (xValue, yValue);
				if (root == null)
					root = ins;
				else if (direction < 0)
					parent.left = ins;
				else
					parent.right = ins;
				return;
			}
			direction = composite.compareTo(curr.composite);
			if (direction == 0)
				return;
			parent = curr;
			if (direction < 0)
				curr = curr.left;
			else
				curr = curr.right;
		}
		
	}

	@Override
	public void removePair(String xValue, String yValue) {
		String composite = xValue+yValue;
		int direction = 0;
		CountryLanguage.Node parent = null, curr = root;
		for (;;) {
			if (curr == null)
				return;
			direction = composite.compareTo(curr.composite);
			if (direction == 0) {
				CountryLanguage.Node del = curr.deleteTopmost();
				if (curr == root)
					root = del;
				else if (curr == parent.left)
					parent.left = del;
				else
					parent.right = del;
				return;
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
	public void removePairsContainingX(String xValue) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removePairsContainingY(String yValue) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void emptyRelation() {
		// TODO Auto-generated method stub
		
	}
	
	/*INNER CLASS*/
	private static class Node{
		protected String composite;
		protected Node left, right;
		
		protected Node(String xValue, String yValue){
			composite = ""+xValue+yValue;
			left = null;
			right = null;
		}
		
		public Node deleteTopmost() {
			if (this.left == null)
				return this.right;
			else if (this.right == null)
				return this.left;
			else { // this node has two children
				this.composite = this.right.getLeftmost();
				this.right = this.right.deleteLeftmost();
				return this;
			}
		}

		private String getLeftmost() {
			Node curr = this;
			while (curr.left != null)
				curr = curr.left;
			return curr.composite;
		}

		public Node deleteLeftmost() {
			if (this.left == null)
				return this.right;
			else {
				Node parent = this, curr = this.left;
				while (curr.left != null) {
					parent = curr;
					curr = curr.left;
				}
				parent.left = curr.right;
				return this;
			}
		}
	}
	
	/*END OF INNER CLASS*/

	
}
