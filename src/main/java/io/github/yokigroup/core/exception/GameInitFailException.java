package io.github.yokigroup.core.exception;

import java.io.Serial;

/**
 * Exception thrown if the game initialization logic did not go as planned.
 */
public class GameInitFailException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 56465165;

    /** Constructs a new game init fail exception with the specified cause and a
     * detail message of {@code (cause==null ? null : cause.toString())}
     * (which typically contains the class and detail message of
     * {@code cause}).  This constructor is useful for runtime exceptions
     * that are little more than wrappers for other throwables.
     *
     * @param  cause the cause (which is saved for later retrieval by the
     *         {@link #getCause()} method).  (A {@code null} value is
     *         permitted, and indicates that the cause is nonexistent or
     *         unknown.)
     */
    public GameInitFailException(final Throwable cause) {
        super(cause);
    }
}
