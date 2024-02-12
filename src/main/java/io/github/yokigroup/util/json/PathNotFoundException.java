package io.github.yokigroup.util.json;

public class PathNotFoundException extends RuntimeException {
    public PathNotFoundException() {
        super();
    }

    public PathNotFoundException(Throwable cause) {
        super(cause);
    }

    public PathNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PathNotFoundException(String message) {
        super(message);
    }
}
