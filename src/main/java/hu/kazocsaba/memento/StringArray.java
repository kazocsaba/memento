package hu.kazocsaba.memento;

import java.util.Arrays;

/**
 * A wrapper around an array of strings to properly implement equals and hashcode.
 *
 * @author Kazó Csaba
 */
class StringArray {
	private String[] array;

	/**
	 * Creates a new instance backed by the specified array.
	 *
	 * @param array the string array to wrap
	 * @throws NullPointerException if <code>array</code> is <code>null</code>
	 */
	public StringArray(String[] array) {
		if (array == null) throw new NullPointerException();
		this.array = array;
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof StringArray && Arrays.equals(((StringArray)obj).array, array);
	}

	@Override
	public int hashCode() {
		return Arrays.hashCode(array);
	}

	public String[] getArray() {
		return array;
	}
}
