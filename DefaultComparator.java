package Assignment3;

import java.util.Comparator;

/**
 * 
 * @author jordan
 *
 * @param <E> Iteme type which is basis for comparison
 */
public class DefaultComparator<E> implements Comparator<E> {

	/**
	 * Sets the default comparison settings for what ever Class instantiates it.
	 */
	@SuppressWarnings("unchecked")
	public int compare(E a, E b) throws ClassCastException {return ((Comparable<E>) a).compareTo(b);}
	
	
}
