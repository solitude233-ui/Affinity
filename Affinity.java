package ca.queensu.cs.cisc124.asgmt.a4;


/**
 * A class representing a combination of one or more
 * affine transformations. Other affine transformations
 * can be premultiplied or postmultiplied with this
 * transformation.
 * 
 * <p>
 * THIS CLASS IS ALREADY COMPLETE.
 */
public class Affinity extends AbstractAffinity {

	/**
	 * Initializes this affinity to the identity transformation.
	 */
	public Affinity() {
		super();
	}
	
	/**
	 * Initializes this affinity by copying another transformation.
	 * 
	 * @param t an affinity to copy
	 */
	public Affinity(AbstractAffinity t) {
		super(t);
	}
	
	/**
	 * Premultiply this transformation with the specified
	 * transformation changing this transformation. 
	 * 
	 * @param t a transformation to premultiply this transformation by
	 */
	public void premultiply(AbstractAffinity t) {
		this.mat.premultiply(t.mat);
	}
	
	/**
	 * Postmultiply this transformation with the specified
	 * transformation changing this transformation. 
	 * 
	 * @param t a transformation to postmultiply this transformation by
	 */
	public void postmultiply(AbstractAffinity t) {
		this.mat.postmultiply(t.mat);
	}
	
	
	/**
	 * Returns the string starting with the string {@code "affinity: "}
	 * followed by the string representation of the superclass.
	 * 
	 * @return a string representation of this transformation
	 */
	@Override
	public String toString() {
		return "affinity: " + super.toString();
	}
	
	
}
