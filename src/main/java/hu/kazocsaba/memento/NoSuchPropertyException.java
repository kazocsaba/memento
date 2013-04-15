package hu.kazocsaba.memento;

/**
 * Thrown when a non-existing memento property is accessed.
 *
 * @author Kazó Csaba
 */
public class NoSuchPropertyException extends MementoFormatException {
	private static final long serialVersionUID = 28614353283745L;

	public NoSuchPropertyException() {
	}

	public NoSuchPropertyException(String message) {
		super(message);
	}

	public NoSuchPropertyException(Throwable cause) {
		super(cause);
	}

	public NoSuchPropertyException(String message, Throwable cause) {
		super(message, cause);
	}
}
