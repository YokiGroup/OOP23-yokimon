package io.github.yokigroup.view.notification;

/**
 * Notification system for Level-up events.
 */
public interface LevelUpNotification extends Notification{
    /**
     * The new level of the Yokimon.
     * @return the new level.
     */
    int levelReached();

    /**
     * Name of the Yokimon who must level-up.
     * @return the name.
     */
    String yokimonName();
}
