package io.github.yokigroup.view.notification;

public interface DeathNotification extends Notification {
    enum Cause {
        UNPREPARED_FOR_BATTLE,
        DEFEATED_IN_BATTLE
    }
    Cause getCause();
}
