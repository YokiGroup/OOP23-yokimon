package io.github.yokigroup.view.observer.notification;

public interface NotificationVisitor {
    String getLevelUpNotificationText(LevelUpNotification notification);
    String getNewYokimonNotificationText(NewYokimonNotification notification);
}
