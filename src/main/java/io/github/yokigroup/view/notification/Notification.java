package io.github.yokigroup.view.notification;

/**
 * Notification system.
 */
public interface Notification {

    String getMessage(NotificationVisitor visitor);
}
