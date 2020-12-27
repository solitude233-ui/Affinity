package ca.queensu.cs.cisc124.asgmt.a4;

import ca.queensu.cs.cisc124.notes.basics.geometry.Point2;
import java.lang.Math;

/**
 * A 2D rotation transformation. A rotation rotates points about the origin
 * {@code (0, 0)} by some angle specified in radians.
 * 
 * <p>
 * A rotation matrix has the invariant that the determinant of the matrix is
 * equal to 1. This class preserves that invariant.
 * 
 * <p>
 * Rotations that differ by an integer multiple of {@code 2 * pi} radians are
 * equal.
 *
 */
public class Rotation extends AbstractAffinity {

	// the angle of rotation for this rotation
	private double radians;

	/**
	 * Initializes a rotation of 0 radians.
	 */
	public Rotation() {
		super();
		this.radians = 0;
	}

	/**
	 * Initializes a rotation of the specified number of radians and set the new
	 * rotation matrix by calling {@code setRadians()}.
	 * 
	 * @param radians the angle of rotation
	 */
	public Rotation(double radians) {
		super();
		this.radians = radians;
		this.setRadians(this.radians);
	}

	/**
	 * Initializes this rotation by copying the specified rotation and set the new
	 * rotation matrix by calling {@code setRadians()}.
	 * 
	 * @param other the rotation to copy
	 */
	public Rotation(Rotation other) {
		super();
		this.radians = other.radians;
		this.setRadians(this.radians);
	}

	/**
	 * Rotates the specified point using this transformation changing the
	 * coordinates of the point by first converting the point into a 3x3 matrix and
	 * then using matrix multiplication by calling {@code premultiply()} to obtain
	 * the the new coordinates.
	 * 
	 * <p>
	 * This method provides a slightly more efficient implementation than the
	 * superclass method by avoiding the full 3x3 matrix multiplication.
	 * 
	 * @param p a point to rotate
	 * @return the rotated point p
	 */
	@Override
	public Point2 transform(Point2 p) {
		Matrix3 M = new Matrix3();
		M.set(0, 0, p.x());
		M.set(1, 0, p.y());
		M.set(2, 0, 1);
		M.premultiply(this.mat);
		p.set(M.get(0, 0), M.get(1, 0));
		return p;
	}

	/**
	 * Returns the angle of rotation for this transformation.
	 * 
	 * @return the angle of rotation for this transformation
	 */
	public double getRadians() {
		// ALREADY DONE FOR YOU
		return this.radians;
	}

	/**
	 * Changes the angle of rotation for this transformation and set the correct
	 * entries within the rotational matrix.
	 * 
	 * <p>
	 * This method re-computes the elements of the matrix representing this
	 * transformation if the specified angle is different from the current angle of
	 * rotation.
	 * 
	 * @param radians the angle of rotation
	 */
	public void setRadians(double radians) {
		this.radians = radians;
		this.mat.set(0, 0, Math.cos(radians));
		this.mat.set(1, 0, Math.sin(radians));
		this.mat.set(0, 1, -Math.sin(radians));
		this.mat.set(1, 1, Math.cos(radians));

	}

	/**
	 * Returns the string starting with the string {@code "rotation: "} followed by
	 * the string representation of the superclass.
	 * 
	 * @return a string representation of this transformation
	 */
	@Override
	public String toString() {
		return "rotation: " + super.toString();
	}
}
