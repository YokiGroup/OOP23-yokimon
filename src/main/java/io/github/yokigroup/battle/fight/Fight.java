package io.github.yokigroup.battle.fight;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.Attack;

/**
 * Interface for Fight.
 */
public interface Fight {

    /**
     * Different success rates trigger a different quote on the view.
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
        VICTORY,
        LOSS
    }

    /**
     * Meant for signaling the state of the fight (Observer pattern).
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
     * Progress the fight one turn.
     * @param myAttack the player's attack.
     */
    //void progress(Attack myAttack);

    /**
     * Method through which the Logic can communicate which attack the player wants to use and do so.
     * @param myAttack the attack that's meant to be used by my Yokimon
     * @return success rate over my Yokimon's attack
     */
    Success attack(Attack myAttack);

    /**
     * Triggers the current opponent Yokimon attack.
     * @return success rate over opponent's attack
     */
    Success getAttacked();

    /**
     * Triggers end of the fight.
     * @return to Logic whether the fight is over
     */
    boolean isOver();

    /**
     * @return whether my Yokimon won
     */
    boolean victory();

    /**
     * Method to calculate how many XP points my current Yokimon earns, in case of victory.
     * @param yokimon the Yokimon whose XP points must be updated
     * @return xp points to be added
     */
    int getXP(Yokimon yokimon);

    /**
     * Useful for the View and for the level-up mechanism.
     * @return my party's Yokimon currently involved in the fight
     */
    Yokimon getCurrentMyYokimon();

    /**
     * Useful for the View.
     * @return my opponent's Yokimon currently involved in the fight
     */
    Yokimon getCurrentOpponent();

    /**
     * The current state of the fight.
     * @return the state.
     */
    State getState();

}
