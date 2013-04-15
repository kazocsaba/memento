package hu.kazocsaba.memento;

/**
 * Indicates that the format of the memento is incorrect.
 *
 * @author Kazó Csaba
 */
public class MementoFormatException extends Exception {
	private static final long serialVersionUID = -2987493624L;

	public MementoFormatException() {
	}

	public MementoFormatException(String message) {
		super(message);
	}

	public MementoFormatException(Throwable cause) {
		super(cause);
	}

	public MementoFormatException(String message, Throwable cause) {
		super(message, cause);
	}
}
