package ca.queensu.cs.cisc124.asgmt.a4;

import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;

/**
 * A 2D rotation transformation where the rotation occurs about a specified
 * point by some angle specified in radians.
 * 
 * <p>
 * A remote rotation matrix has the invariant that the determinant of the matrix
 * is equal to 1. This class preserves that invariant.
 * 
 * <p>
 * Rotations about the same point that differ by an integer multiple of
 * {@code 2 * pi} radians are equal.
 *
 */
public class RemoteRotation extends AbstractAffinity {

	// the angle of rotation for this transformation
	private double radians;

	// the center of rotation for this transformation
	private Point2 center;

	/**
	 * Initializes a rotation of 0 radians about the point {@code (0, 0)}.
	 */
	public RemoteRotation() {
		super();
		this.radians = 0;
		this.center = new Point2(0, 0);

	}

	/**
	 * Initializes this remote rotation by copying another remote rotation and set
	 * the appropriate elements of the transformation matrix by calling
	 * {@code set()}.
	 * 
	 * @param other the remote rotation to copy
	 */
	public RemoteRotation(RemoteRotation other) {
		super();
		this.radians = other.radians;
		this.center = new Point2(other.center.x(), other.center.y());
		this.set(this.radians, this.center);
	}

	/**
	 * Initializes this remote rotation to the specified angle about the specified
	 * center of rotation and set the appropriate elements of the transformation
	 * matrix by calling {@code set()}.
	 * 
	 * @param radians the angle to rotate by
	 * @param center  the point to rotate about
	 */
	public RemoteRotation(double radians, Point2 center) {
		super();
		this.radians = radians;
		this.center = new Point2(center.x(), center.y());
		this.set(this.radians, this.center);
	}

	/**
	 * Returns the angle of rotation.
	 * 
	 * @return the angle of rotation
	 */
	public double getRadians() {
		// ALREADY DONE FOR YOU
		return this.radians;
	}

	/**
	 * Returns a new point equal to the center of rotation.
	 * 
	 * @return the center of rotation
	 */
	public Point2 getCenter() {
		return new Point2(this.center);
	}

	/**
	 * Changes the angle of rotation for this transformation.
	 * 
	 * <p>
	 * This method re-computes the elements of the matrix representing this
	 * transformation if the specified angle is different from the current angle of
	 * rotation by calling {@code set()}.
	 * 
	 * @param radians the angle of rotation
	 */
	public void setRadians(double radians) {
		this.radians = radians;
		this.set(this.radians, this.center);
	}

	/**
	 * Changes the center of rotation for this transformation if the center is
	 * changed and change the transformation matrix by calling {@code set()}.
	 * 
	 * <p>
	 * This method re-computes the elements of the matrix representing this
	 * transformation.
	 * 
	 * @param center the center of rotation
	 */
	public void setCenter(Point2 center) {
		this.center = new Point2(center.x(), center.y());
		this.set(this.radians, this.center);
	}

	/**
	 * Changes the angle and center of rotation for this transformation.
	 * 
	 * <p>
	 * This method re-computes the elements of the matrix representing this
	 * transformation by using matrix multiplication following the formula A =
	 * T2RT1.
	 * 
	 * @param radians the angle of rotation
	 * @param center  the center of rotation
	 */
	public void set(double radians, Point2 center) {
		this.radians = radians;
		this.center = new Point2(center.x(), center.y());
		// Initialize matrix T2 and set the correct entries.
		Matrix3 T2 = new Matrix3();
		T2.set(0, 2, this.center.x());
		T2.set(1, 2, this.center.y());
		T2.set(0, 0, 1);
		T2.set(1, 1, 1);
		T2.set(2, 2, 1);
		// Initialize the rotational matrix and set the correct entries.
		this.mat.set(0, 0, Math.cos(this.radians));
		this.mat.set(1, 0, Math.sin(this.radians));
		this.mat.set(0, 1, -Math.sin(this.radians));
		this.mat.set(1, 1, Math.cos(this.radians));
		// Initialize the matrix T1 and set the correct entries.
		Matrix3 T1 = new Matrix3();
		T1.set(0, 2, -this.center.x());
		T1.set(1, 2, -this.center.y());
		T1.set(0, 0, 1);
		T1.set(1, 1, 1);
		T1.set(2, 2, 1);
		// Follow the formula: A = T2RT1
		this.mat = new Matrix3(T2.postmultiply(this.mat).postmultiply(T1));
	}

	/**
	 * Returns the string starting with the string {@code "affinity: "} followed by
	 * the string representation of the superclass.
	 * 
	 * @return a string representation of this transformation
	 */
	@Override
	public String toString() {
		return "affinity: " + super.toString();
	}

}
