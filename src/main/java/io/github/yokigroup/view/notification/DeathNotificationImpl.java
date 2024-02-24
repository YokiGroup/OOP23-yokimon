package io.github.yokigroup.view.notification;

public final class DeathNotificationImpl implements DeathNotification {
    private final Cause causeOfDeath;

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
