package hu.kazocsaba.memento;
/**
 * Thrown when a memento property was accessed as a different type than
 * as it was set.
 * @author Kazó Csaba
 *
 */
public class TypeMismatchException extends MementoFormatException {
    private static final long serialVersionUID = 28614353283745L;

	public TypeMismatchException() {}

	public TypeMismatchException(String message) {
		super(message);
	}

	public TypeMismatchException(Throwable cause) {
		super(cause);
	}

	public TypeMismatchException(String message, Throwable cause) {
		super(message, cause);
	}

}
