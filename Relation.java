import java.util.ArrayList;

/**
 * Relation stores binary relations. The pairs are in no fixed
 * order. Individual x and y values may be duplication, but each (x,y) 
 * pair must be unique.
 */
public interface Relation<X extends Comparable<X>,Y extends Comparable<Y>> {
	
	/**
	 * Method to determine whether a given X,Y pair is contained in the relation
	 * @param xValue	Comparable X to be found
	 * @param yValue	Comparable Y to be found
	 * @return boolean	boolean value indicating whether the X,Y pair is contained in the relation
	 */
	public boolean contains(X xValue, Y yValue);
	
	/**
	 * Method to return values of Y which are paired with the value X in the relation. 
	 * @param xValue	Comparable X to be found in the relation
	 * @return ArrayList<Y>	ArrayList containing all values of Y paired with X in the relation.
	 */
	public ArrayList<Y> correspondingYValues (X xValue);
	
	/**
	 * Method to return values of X which are paired with the value Y in the relation. 
	 * @param yValue	Comparable Y to be found in the relation
	 * @return ArrayList<X>	ArrayList containing all values of X paired with Y in the relation.
	 */
	public ArrayList<X> correspondingXValues (Y yValue);
	
	/**
	 * Method to clear the relation of all existing pairs of X,Y values
	 */
	public void empty();
	
	/**
	 * Method to add a pair of X,Y values to the relation.
	 * @param xValue	Comparable X to be added to the relation
	 * @param yValue	Comparable Y to be added to the relation
	 * @return boolean	boolean to denote the successful addition of the pair to the relation.
	 */
	public boolean insert (X xValue, Y yValue);
	
	/**
	 * Method to remove a given X,Y pair from the relation.
	 * @param xValue	Comparable X value to be removed.
	 * @param yValue	Comparable Y value to be removed.
	 * @return boolean	boolean to denote the successful removal of the pair from the relation.
	 */
	public boolean remove (X xValue, Y yValue);
	
	/**
	 * Method to remove all pairs containing a given value of X from the relation	
	 * @param xValue	Comparable X to be removed from the relation.
	 */
	public void removeContainingX (X xValue);
	
	/**
	 * Method to remove all pairs containing a given value of Y from the relation	
	 * @param yValue	Comparable Y to be removed from the relation.
	 */
	public void removeContainingY (Y yValue);
	
	/**
	 * Method to generate a String description of the contents of the relation
	 * @return String	a String describing the pairs contained in the relation
	 */
	public String toString();
	
}