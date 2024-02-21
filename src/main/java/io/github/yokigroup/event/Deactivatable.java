package io.github.yokigroup.event;

/**
 * Used for objects that can be disabled, the meaning of which depends on object to object but generally means that a \
 * disabled object cannot update its state.
 */
public interface Deactivatable {
    /**
     * Activates the object, does nothing if it's already active.
     */
    void activate();

    /**
     * Deactivates the object, does nothing if it's already inactive
     */
    void deactivate();

    /**
     * @return {@code true} if the object is active
     */
    boolean isActive();
}
