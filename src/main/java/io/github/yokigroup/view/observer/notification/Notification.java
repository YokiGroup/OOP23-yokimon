package io.github.yokigroup.view.observer.notification;

public interface Notification {
    String getMessage(NotificationVisitor visitor);
}
