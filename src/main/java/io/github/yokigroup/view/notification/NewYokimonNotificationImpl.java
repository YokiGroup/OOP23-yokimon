package io.github.yokigroup.view.notification;

/**
 * Notification system for when a new Yokimon is found.
 */
public final class NewYokimonNotificationImpl implements NewYokimonNotification {
    private final String yokimonName;

    /**
     * Constructor for NewYokimonNotificationImpl.
     * @param yokimonName the new Yokimon's name.
     */
    public NewYokimonNotificationImpl(final String yokimonName) {
        this.yokimonName = yokimonName;
    }

    @Override
    public String yokimonName() {
        return yokimonName;
    }

    @Override
    public String getMessage(final NotificationVisitor visitor) {
        return visitor.getNewYokimonNotificationText(this);
    }
}
