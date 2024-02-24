package io.github.yokigroup.view.notification;

/**
 * Implementation of {@link DeathNotification}.
 */
public final class DeathNotificationImpl implements DeathNotification {
    private final Cause causeOfDeath;

    /**
     * @param causeOfDeath cause of death to notify to the view
     */
    public DeathNotificationImpl(final Cause causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    @Override
    public Cause getCause() {
        return causeOfDeath;
    }

    @Override
    public String getMessage(final NotificationVisitor visitor) {
        return visitor.getDeathNotificationText(this);
    }
}
