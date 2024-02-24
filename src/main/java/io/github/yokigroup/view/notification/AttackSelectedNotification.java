package io.github.yokigroup.view.notification;

import io.github.yokigroup.battle.attack.Attack;

/**
 * Notification used for notifying what attack has been selected by the player's yokimon.
 */
public interface AttackSelectedNotification extends Notification {
    /**
     * @return the selected attack
     */
    Attack getSelectedAttack();
}
