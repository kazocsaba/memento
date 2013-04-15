package hu.kazocsaba.memento;

import java.util.Arrays;

/**
 * A wrapper around an array of integers to properly implement equals and hashcode.
 * @author Kazó Csaba
 */
class IntArray {
	private int[] array;
	/**
	 * Creates a new instance backed by the specified array.
	 * @param array the integer array to wrap
	 * @throws NullPointerException if <code>array</code> is <code>null</code>
	 */
	public IntArray(int[] array) {
		if (array==null) throw new NullPointerException();
		this.array=array;
	}
	@Override
	public boolean equals(Object obj) {
		return obj instanceof IntArray && Arrays.equals(((IntArray) obj).array,array);
	}
	@Override
	public int hashCode() {
		return Arrays.hashCode(array);
	}
	public int[] getArray() {return array;}

}
