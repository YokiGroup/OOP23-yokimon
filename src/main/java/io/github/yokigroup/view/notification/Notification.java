package io.github.yokigroup.view.notification;

public interface Notification {
    String getMessage(NotificationVisitor visitor);
}
