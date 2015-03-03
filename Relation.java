
public interface Relation<X, Y> {
	
	public boolean isEmpty();
	
	public boolean contains(X xValue, Y yValue);
	
	public int size();
	
	public Y getYValues (X xValue);
	
	public X getXValues (Y yValue);
	
	public void emptyRelation();
	
	public void addPair (X xValue, Y yValue);
	
	public void removePair (X xValue, Y yValue);
	
	public void removePairsContainingX (X xValue);
	
	public void removePairsContainingY (Y yValue);
	
	public String toString();
	
}
