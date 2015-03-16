import java.util.ArrayList;

public interface Relation<X extends Comparable<X>,Y extends Comparable<Y>> {
	
	/**
	 * Method to determine whether the Relation stores
	 * a given pair of values.
	 * @param xValue	object of type X in the pair to be matched
	 * @param yValue	object of type Y in the pair to be matched
	 * @return boolean	denotes whether the pair exists within the Relation
	 */
	public boolean contains(X xValue, Y yValue);
	
	/**
	 * Method to return the values of Y in the relation which are 
	 * paired with the value of object X. 
	 * @param xValue	object of type X to be identified in the Relation
	 * @return Y[]	an array of type Y, containing the values which are paired
	 * to the xValue.
	 */
	public ArrayList<Y> correspondingYValues (X xValue);
	
	/**
	 * Method to return the values of X in the relation which are 
	 * paired with the value of object Y. 
	 * @param yValue	object of type Y to be identified in the Relation
	 * @return X[]	an array of type X, containing the values which are paired
	 * to the xValue.
	 */
	public ArrayList<X> correspondingXValues (Y yValue);
	
	/**
	 * Method to empty the relation of all pairs of values
	 */
	public void empty();
	
	/**
	 * Method to add a pair of values to the Relation.
	 * @param xValue	object of type X to be added to the Relation
	 * @param yValue	object of type Y to be added to the Relation
	 * @return boolean	denotes whether the object was successfully added
	 * the relation.
	 */
	public boolean insert (X xValue, Y yValue);
	
	/**
	 * Method to remove a given pair from the Relation.
	 * @param xValue	value of X within the pair to be removed.
	 * @param yValue	value of Y within the pair to be removed.
	 * @return boolean	denotes whether the pair was successfully removed from the Relation.
	 */
	public boolean remove (X xValue, Y yValue);
	
	/**
	 * Method to remove all pairs containing a given value of
	 * X from the Relation	
	 * @param xValue	Object containing value of X to be removed.
	 */
	public void removeContainingX (X xValue);
	
	/**
	 * Method to remove all pairs containing a given value of
	 * Y from the Relation	
	 * @param yValue	Object containing value of Y to be removed.
	 */
	public void removeContainingY (Y yValue);
	
	/**
	 * Method to generate a string of the contents of the Relation
	 * @return String	a string representing the contents of the Relation
	 */
	public String toString();
	
}