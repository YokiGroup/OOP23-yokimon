package io.github.yokigroup.util.json;

/**
 * Thrown when a json file is not structured correctly.
 */
public class InvalidJsonException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified cause.
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public InvalidJsonException(final Throwable cause) {
        super(cause);
    }

    /** Constructs a new runtime exception with the specified detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     *
     * @param   message   the detail message. The detail message is saved for
     *          later retrieval by the {@link #getMessage()} method.
     */
    public InvalidJsonException(String message) {
        super(message);
    }

}
