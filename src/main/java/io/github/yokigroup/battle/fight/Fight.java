package io.github.yokigroup.battle.fight;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.battle.LevelUpLogic;

/**
 * The game mechanics necessary for the fight.
 * @see FightSubmodule
 * @see EObserver
 */
public interface Fight {

    /**
     * Different success rates trigger a different quote on the View right after a move.
     */
     enum Success {
        /**
         * The attack was successful.
         */
        GOOD,
        /**
         * The attack was very effective.
         */
        SUPER,
        /**
         * The attack was rather unsuccessful.
         */
        WEAK,
        /**
         * The attack was a failure.
         */
        FAIL,
        /**
         * The attack resulted in the player's victory.
         */
        VICTORY,
        /**
         * The attack resulted in the player's loss.
         */
        LOSS
    }

    /**
     * Meant for signaling the state of the fight
     */
    enum State {
        /**
         * The fight has been instantiated. We can proceed.
         */
         READY_TO_PROGRESS,
        /**
         * The fight ended with the player's victory.
         */
        WIN,
        /**
         * The fight ended with the player's loss.
         */
        LOSE
    }

    /**
     * Method through which the Logic can communicate which {@link Attack} the player wants to use and do so.
     * @param myAttack the attack that's meant to be used by my {@link Yokimon}
     * @return success rate over my {@link Yokimon}'s attack
     */
    Success attack(Attack myAttack);

    /**
     * Triggers the current opponent Yokimon {@link Attack}.
     * @return success rate over opponent's attack
     */
    Success getAttacked();

    /**
     * Triggers end of the fight.
     * @return to Logic whether the fight is over
     */
    boolean isOver();

    /**
     * @return whether my {@link Yokimon} won
     */
    boolean victory();

    /**
     * Method to calculate how many XP points my current Yokimon earns, in case of victory.
     * @param yokimon the Yokimon whose XP points must be updated
     * @return xp points to be added
     */
    int getXP(Yokimon yokimon);

    /**
     * Useful for the View and for the {@link LevelUpLogic} mechanism.
     * @return my party's {@link Yokimon} currently involved in the fight
     */
    Yokimon getCurrentMyYokimon();

    /**
     * Useful for the View.
     * @return my opponent's {@link Yokimon} currently involved in the fight
     */
    Yokimon getCurrentOpponent();

    /**
     * The current state of the fight.
     * @return the state.
     */
    State getState();

    /**
     * This is meant to display the life bar in the View.
     * @param yokimon the {@link Yokimon} whose HP percentage must be shown
     * @return the percentage of HP points left.
     */
    double getHPPercentage(Yokimon yokimon);

    /**
     * This method must be used at each turn.
     * @return if the player must attack first.
     */
    boolean playerIsFirst();

}
