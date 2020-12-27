package ca.queensu.cs.cisc124.asgmt.a4;

import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;

/**
 * Superclass for 2D affine transformations or affinities. An affinity maps
 * lines to lines and preserves parallelism but may change the distance between
 * points and the angle between lines. Examples of affine transformations
 * include rotation, translation, scaling, and shear.
 * 
 * <p>
 * Any combination of affine transformations is also an affine transformation.
 * 
 * <p>
 * Every affinity can be represented by a 3x3 matrix where the last row of the
 * matrix is {@code [0, 0, 1]}. This class provides a {@code Matrix3} object for
 * subclasses to manipulate. It also provides methods for transforming and
 * mapping points using matrix multiplication. Subclasses should override these
 * methods to provide more efficient implementations of these methods.
 */
public abstract class AbstractAffinity {
	/**
	 * The matrix representation of this transformation.
	 */
	protected Matrix3 mat;

	/**
	 * Initializes this affinity to the identity transformation which has the first
	 * row of (1, 0, 0), second row of (0, 1, 0) and last row of the matrix being
	 * (0, 0, 1).
	 */
	public AbstractAffinity() {
		this.mat = new Matrix3();
		this.mat.set(0, 0, 1.0);
		this.mat.set(1, 1, 1.0);
		this.mat.set(2, 2, 1.0);
	}

	/**
	 * Initializes this affinity by copying another transformation. This
	 * implementation makes an independent copy of the matrix belonging to the other
	 * transformation.
	 * 
	 * @param t an affinity to copy
	 */
	public AbstractAffinity(AbstractAffinity t) {
		this.mat = new Matrix3(t.mat);
	}

	/**
	 * Transforms the specified point using this transformation changing the
	 * coordinates of the point using matrix multiplication. Given the parameter is
	 * a 2-point vector, this method first converts the vector a 3x3 matrix using
	 * the help of homogeneous point and then performs the multiplication. The (0,0)
	 * and (1,0) entry in the resulting matrix provides the new coordinate for the
	 * point.
	 * 
	 * @param p a point to transform
	 * @return the transformed point p
	 */
	public Point2 transform(Point2 p) {
		Matrix3 pt = new Matrix3();
		Matrix3 result = new Matrix3();
		pt.set(0, 0, p.x());
		pt.set(1, 0, p.y());
		pt.set(2, 0, 1);
		result = pt.premultiply(this.mat);
		p.set(result.get(0, 0), result.get(1, 0));
		return p;
	}

	/**
	 * Maps the specified point using this transformation to produce a new point by
	 * calling the {@code transform()}.
	 * 
	 * @param p a point to map
	 * @return a new point equal to this transformation applied to p
	 */
	public Point2 map(Point2 p) {
		// ALREADY DONE FOR YOU
		Point2 q = new Point2(p);
		return this.transform(q);
	}

	/**
	 * Returns a new copy of the matrix representation of this transformation.
	 * 
	 * @return a new copy of the matrix representation of this transformation
	 */
	public Matrix3 copyMatrix() {
		return new Matrix3(this.mat);
	}

	/**
	 * Returns a hash code for this transformation. The returned hash code is the
	 * hash code of the transformation matrix.
	 * 
	 * @return a hash code for this transformation
	 */
	@Override
	public int hashCode() {
		// ALREADY DONE FOR YOU
		return this.mat.hashCode();
	}

	/**
	 * Compares the specified object with this affinity for equality. Returns true
	 * if and only if the specified object is also an AbstractAffinity having a
	 * matrix representation equal to this transformation.
	 * 
	 * @param obj the object to be compared for equality with this transformation
	 * @return true if the specified object is equal to this transformation
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof AbstractAffinity)) {
			return false;
		}
		AbstractAffinity other = (AbstractAffinity) obj;
		if (this.mat.equals(other.mat)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the string representation of the matrix representation of this
	 * transformation.
	 * 
	 * <p>
	 * The nine elements of the matrix will appear in an easily readable form in the
	 * returned string.
	 * 
	 * @return a string representation of this transformation
	 */
	@Override
	public String toString() {
		return this.mat.toString();
	}

}
