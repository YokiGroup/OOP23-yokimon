package io.github.yokigroup.view.notification;

public class NewYokimonNotificationImpl implements NewYokimonNotification {
    private final String yokimonName;

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
