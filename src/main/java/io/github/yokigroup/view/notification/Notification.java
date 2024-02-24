package io.github.yokigroup.view.notification;

/**
 * Notification system. Notifies the view of events that need to be comunicated to the player, along with their details.
 */
public interface Notification {

    /**
     * given a {@param visitor} extracts the message to be displayed to the player.
     * @param visitor {@link NotificationVisitor} to process and generate a string representing the notification.
     * @return The event string to be displayed in the view
     * @see NotificationVisitor
     */
    String getMessage(NotificationVisitor visitor);
}
