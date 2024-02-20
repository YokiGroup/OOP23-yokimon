package io.github.yokigroup.view.notification;

public class LevelUpNotificationImpl implements LevelUpNotification {
    private final int levelReached;
    private final String yokimonName;

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
    public String getMessage(NotificationVisitor visitor) {
        return visitor.getLevelUpNotificationText(this);
    }
}
