package org.howard.edu.lsp.assignment4;

public class IntegerSetException extends Exception {
    private static final long serialVersionUID = 1L;

	public IntegerSetException() {
        super();
    }

    public IntegerSetException(String message) {
        super(message);
    }

    public IntegerSetException(String message, Throwable cause) {
        super(message, cause);
    }

    public IntegerSetException(Throwable cause) {
        super(cause);
    }

    // Add additional constructors or methods as needed for specific exceptions

    // Example: Constructor for an exception with a predefined message
    public IntegerSetException(int value) {
        super("Invalid operation with value: " + value);
    }
}
