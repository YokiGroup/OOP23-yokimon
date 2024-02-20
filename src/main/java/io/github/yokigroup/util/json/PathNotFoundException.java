package io.github.yokigroup.util.json;

/**
 * Thrown when the path queried to the json parser is not found.
 */
public class PathNotFoundException extends RuntimeException {

    /**
     * Constructs a new runtime exception with the specified cause.
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public PathNotFoundException(final Throwable cause) {
        super(cause);
    }

}
