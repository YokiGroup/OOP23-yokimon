package io.github.yokigroup.view.notification;

/**
 * Notification system for when a new Yokimon is found.
 */
public interface NewYokimonNotification extends Notification {

    /**
     * The name of the new Yokimon found.
     * @return the name of the Yokimon.
     */
    String yokimonName();
}
