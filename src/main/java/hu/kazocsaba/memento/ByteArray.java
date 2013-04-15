package hu.kazocsaba.memento;

import java.util.AbstractList;
import java.util.Arrays;

/**
 * A wrapper around an array of bytes to properly implement equals and hashcode.
 * @author Kazó Csaba
 */
class ByteArray {
	private byte[] array;
	/**
	 * Creates a new instance backed by the specified array.
	 * @param array the integer array to wrap
	 * @throws NullPointerException if <code>array</code> is <code>null</code>
	 */
	public ByteArray(byte[] array) {
		if (array==null) throw new NullPointerException();
		this.array=array;
	}
	@Override
	public boolean equals(Object obj) {
		return obj instanceof ByteArray && Arrays.equals(((ByteArray) obj).array,array);
	}
	@Override
	public int hashCode() {
		return Arrays.hashCode(array);
	}
	public byte[] getArray() {return array;}

}
