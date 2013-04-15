package hu.kazocsaba.memento;

import java.util.Arrays;

/**
 * A wrapper around an array of doubles to properly implement equals and hashcode.
 * @author Kazó Csaba
 */
class DoubleArray {
	private double[] array;
	/**
	 * Creates a new instance backed by the specified array.
	 * @param array the double array to wrap
	 * @throws NullPointerException if <code>array</code> is <code>null</code>
	 */
	public DoubleArray(double[] array) {
		if (array==null) throw new NullPointerException();
		this.array=array;
	}
	@Override
	public boolean equals(Object obj) {
		return obj instanceof DoubleArray && Arrays.equals(((DoubleArray) obj).array,array);
	}
	@Override
	public int hashCode() {
		return Arrays.hashCode(array);
	}
	public double[] getArray() {return array;}

}
