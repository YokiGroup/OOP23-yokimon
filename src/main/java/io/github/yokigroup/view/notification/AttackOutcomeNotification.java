package io.github.yokigroup.view.notification;

import io.github.yokigroup.battle.fight.Fight;

/**
 * Notification describing an attack outcome.
 */
public interface AttackOutcomeNotification extends Notification {
    /**
     * Yokimon that performed the attack.
     */
    enum Attacker {
        /**
         * The player's yokimon performed the attack.
         */
        PLAYER,
        /**
         * The enemy's yokimon performed the attack.
         */
        ENEMY
    }

    /**
     * @return The performed attack's outcome.
     */
    Fight.Success getAttackOutcome();
    /**
     * @return The {@link Attacker} enum that details what side performed the attack.
     */
    Attacker getAttacker();

}
