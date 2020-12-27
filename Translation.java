package ca.queensu.cs.cisc124.asgmt.a4;

import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;
import ca.queensu.cs.cisc124.notes.basics.geometry.Vector2;

/**
 * A 2D translation transformation. A translation moves points by a vector
 * amount.
 * 
 * <p>
 * A translation matrix has the invariant that the upper-left 2x2 block is equal
 * to the 2x2 identity matrix. This class preserves that invariant.
 *
 */
public class Translation extends AbstractAffinity {

	/**
	 * Initializes a translation by the vector {@code (0, 0)}.
	 */
	public Translation() {
		super();
	}

	/**
	 * Initializes a translation by the vector {@code (x, y)}. The value of
	 * {@code (x, y)} is stored at the (0,2) and (1,2) location inside the
	 * tranlation matrix.
	 * 
	 * @param x the amount to move a point in the x direction
	 * @param y the amount to move a point in the y direction
	 */
	public Translation(double x, double y) {
		this.mat.set(0, 2, x);
		this.mat.set(1, 2, y);
	}

	/**
	 * Translates the specified point using this transformation changing the
	 * coordinates of the point by using vector addition.
	 * 
	 * <p>
	 * This method provides a more efficient implementation than the superclass
	 * method by avoiding the full 3x3 matrix multiplication.
	 * 
	 * @param p a point to translate
	 * @return the translated point p
	 */
	@Override
	public Point2 transform(Point2 p) {
		Vector2 v = new Vector2(this.mat.get(0, 2), this.mat.get(1, 2));
		p.add(v);
		return p;
	}

	/**
	 * Returns the vector corresponding to this translation.
	 * 
	 * @return the vector corresponding to this translation
	 */
	public Vector2 getVector() {
		Vector2 v = new Vector2(this.mat.get(0, 2), this.mat.get(1, 2));
		return v;
	}

	/**
	 * Changes the translation vector for this transformation returning the previous
	 * vector.
	 * 
	 * <p>
	 * This method re-computes the elements of the matrix representing this
	 * transformation.
	 * 
	 * @param x the amount to move a point in the x direction
	 * @param y the amount to move a point in the y direction
	 */
	public void setVector(double x, double y) {
		this.mat.set(0, 2, x);
		this.mat.set(1, 2, y);
	}

	/**
	 * Returns the string starting with the string {@code "translation: "} followed
	 * by the string representation of the superclass.
	 * 
	 * @return a string representation of this transformation
	 */
	@Override
	public String toString() {
		return "translation: " + super.toString();
	}
}
