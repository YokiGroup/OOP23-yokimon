package io.github.yokigroup.view.observer.notification;

import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.view.Painter;
import javafx.application.Platform;

public class NotificationVisitorImpl implements NotificationVisitor, EObserver<Notification> {
    private final Painter painter;
    // FIXME locales


    public NotificationVisitorImpl(Painter painter) {
        this.painter = painter;
    }

    @Override
    public String getLevelUpNotificationText(final LevelUpNotification not) {
        final String levelUpMsg = "%s leveled up to level %d!";
        return String.format(levelUpMsg, not.yokimonName(), not.levelReached());
    }

    @Override
    public String getNewYokimonNotificationText(NewYokimonNotification notification) {
        final String newYokimonMsg = "Obtained %s";
        return String.format(newYokimonMsg, notification.yokimonName());
    }

    @Override
    public void update(PublisherImpl<Notification> publisher, Notification lastArg, Notification arg) {
        painter.paintEventText(arg.getMessage(this));
        Platform.runLater(painter::repaint);
    }
}
