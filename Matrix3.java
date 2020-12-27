package ca.queensu.cs.cisc124.asgmt.a4;

/**
 * A basic matrix class for 3x3 real-valued matrices.
 * 
 * <p>
 * The rows and columns of the matrix are indexed using a 0-based index (i.e.,
 * the first row is row 0 and the first column is column 0).
 */
public class Matrix3 {

	// A two dimensional array represents a 3x3 matrix.
	private double[][] matrix = new double[3][3];

	/**
	 * Initialize a 3x3 matrix by setting all entries in the two dimensional array
	 * to 0.
	 */
	public Matrix3() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.matrix[i][j] = 0;
			}
		}
	}

	/**
	 * Construct an independent copy of an existing 3x3 matrix. The new matrix will
	 * have the same entries as the existing matrix.
	 * 
	 * @param other the existing matrix to copy.
	 */
	public Matrix3(Matrix3 other) {
		this.matrix = other.matrix;
	}

	/**
	 * Returns the element at the specified row and column of this matrix.
	 * 
	 * @param row the row index
	 * @param col the column index
	 * @return the element at the specified row and column
	 * @throws IndexOutOfBoundsException if the row or column index is out of bounds
	 */
	public double get(int row, int col) {
		if (row >= 3 || col >= 3) {
			throw new IndexOutOfBoundsException("Row index out of bounds");
		}
		return this.matrix[row][col];
	}

	/**
	 * Sets the value of an entry inside the matrix at a specific row/column
	 * location.
	 * 
	 * 
	 * @param row   the row index
	 * @param col   the column index
	 * @param value the value to be set to at a location
	 * @throws IndexOutOfBoundsException if the row or column index is out of bounds
	 */
	public void set(int row, int col, double value) {
		if (row >= 3 || col >= 3) {
			throw new IndexOutOfBoundsException("Row index out of bounds");
		}
		this.matrix[row][col] = value;
	}

	/**
	 * Premultiplies this matrix by the specified matrix changing the elements of
	 * this matrix.
	 * 
	 * @param other the matrix to premultiply this matrix by
	 * @return a reference to this matrix
	 */
	public Matrix3 premultiply(Matrix3 other) {
		Matrix3 temp = new Matrix3();
		// Follow the rule of matrix multiplication, the first row of the first
		// matrix is multiplied by the first column of second matrix to produce
		// the result at location [0][0] and so on...
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					temp.matrix[i][j] += other.matrix[i][k] * this.matrix[k][j];
				}
			}
		}
		this.matrix = temp.matrix;
		return this;
	}

	/**
	 * Postmultiplies this matrix by the specified matrix changing the elements of
	 * this matrix.
	 * 
	 * @param other the matrix to postmultiply this matrix by
	 * @return a reference to this matrix
	 */
	public Matrix3 postmultiply(Matrix3 other) {
		Matrix3 temp = new Matrix3();
		// Follow the rule of matrix multiplication, the first row of the first
		// matrix is multiplied by the first column of second matrix to produce
		// the result at location [0][0] and so on...
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				for (int k = 0; k < 3; k++) {
					temp.matrix[i][j] += this.matrix[i][k] * other.matrix[k][j];
				}
			}
		}
		this.matrix = temp.matrix;
		return this;
	}

	/**
	 * Compares this matrix to the specified object. The result is {@code true} if
	 * and only if all of the following are {@code true}:
	 * 
	 * <ul>
	 * <li>the argument is not {@code null}</li>
	 * <li>the argument is a {@code Matrix3} reference</li>
	 * <li>the current matrix(two-dim array) and the other matrix are
	 * {@code equals}</li>
	 * </ul>
	 * 
	 * @param obj the object to compare with
	 * @return true if the two matrices are equal and false otherwise.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Matrix3)) {
			return false;
		}
		Matrix3 other = (Matrix3) obj;
		if (this.matrix.equals(other.matrix)) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the hash code for this matrix.
	 * 
	 * @return a hash code for this matrix.
	 */
	@Override
	public int hashCode() {
		if (this.matrix == null) {
			return 0;
		}
		int result = 1;
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[0].length; j++) {
				int c = Double.hashCode(this.matrix[i][j]);
				result = 31 * result + c;
			}
		}
		return result;
	}

	/**
	 * Return a string representation of this matrix. In this case, it will return
	 * the two dimensional array in a 3x3 matrix form. For examples, the array with
	 * entries:
	 * 
	 * <p>
	 * [[1,2,3],[4,5,6],[7,8,9]]
	 * 
	 * <p>
	 * Would be represented by the matrix form as:
	 * 
	 * <p>
	 * 1 2 3 4 5 6 7 8 9
	 * 
	 * @return Return a string representation of this matrix.
	 */
	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < this.matrix.length; i++) {
			for (int j = 0; j < this.matrix[0].length; j++) {
				if (j != 2) {
					output += String.valueOf(this.matrix[i][j]) + '\t';
				} else {
					output += String.valueOf(this.matrix[i][j]) + '\n';
				}
			}
		}
		return output;
	}

}
