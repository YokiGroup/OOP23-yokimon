package io.github.yokigroup.view.notification;

import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.view.render.Painter;

public class NotificationVisitorImpl implements NotificationVisitor, EObserver<Notification> {
    private final Painter painter;
    // FIXME locales


    public NotificationVisitorImpl(final Painter painter) {
        this.painter = painter;
    }

    @Override
    public String getLevelUpNotificationText(final LevelUpNotification not) {
        final String levelUpMsg = "%s leveled up to level %d!";
        return String.format(levelUpMsg, not.yokimonName(), not.levelReached());
    }

    @Override
    public String getNewYokimonNotificationText(final NewYokimonNotification notification) {
        final String newYokimonMsg = "Obtained %s";
        return String.format(newYokimonMsg, notification.yokimonName());
    }

    @Override
    public void update(final Notification lastArg, final Notification arg) {
        painter.setEventText(arg.getMessage(this));
    }
}
