package io.github.yokigroup.view.notification;

/**
 * Notification system to notify a level-up event.
 */
public final class LevelUpNotificationImpl implements LevelUpNotification {
    private final int levelReached;
    private final String yokimonName;

    /**
     * Constructor for LevelUpNotificationImpl.
     * @param levelReached the Yokimon's new level.
     * @param yokimonName the Yokimon's name.
     */
    public LevelUpNotificationImpl(final int levelReached, final String yokimonName) {
        this.levelReached = levelReached;
        this.yokimonName = yokimonName;
    }

    @Override
    public int levelReached() {
        return levelReached;
    }

    @Override
    public String yokimonName() {
        return yokimonName;
    }

    @Override
    public String getMessage(final NotificationVisitor visitor) {
        return visitor.getLevelUpNotificationText(this);
    }
}
