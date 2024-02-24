package io.github.yokigroup.view.notification;

/**
 * Notifies the cause of death of the player.
 */
public interface DeathNotification extends Notification {
    /**
     * Cause of death.
     */
    enum Cause {
        /**
         * The player commenced a fight without carrying any yokimons in their party.
         */
        UNPREPARED_FOR_BATTLE,
        /**
         * The player finished their stock of yokimons during a battle.
         */
        DEFEATED_IN_BATTLE
    }

    /**
     * @return cause of death of the player
     */
    Cause getCause();
}
