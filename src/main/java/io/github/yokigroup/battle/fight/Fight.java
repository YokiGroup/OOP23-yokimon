package io.github.yokigroup.battle.fight;

import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.battle.yokimon.LevelUpLogic;

import java.util.List;


/**
 * The game mechanics necessary for the fight.
 *
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
        FAIL
    }

    /**
     * Meant for signaling the state of the fight.
     */
    enum State {
        /**
         * It's the player's turn.
         */
        PLAYER_TURN,
        /**
         * It's the opponent's turn.
         */
        OPPONENT_TURN,
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
     * Sets an attack to use in the next.
     *
     * @param attack attack to use
     * @throws IllegalArgumentException if the attack is not possessed by the fighting yokimon
     */
    void selectAttack(Attack attack);

    /**
     * Gets the selected attack.
     *
     * @return the current selected attack.
     */
    Attack getSelectedAttack();

    /**
     * Method through which the Logic can communicate which {@link Attack} the player wants to use and do so.
     *
     * @return success rate over my {@link Yokimon}'s attack
     * @see this#selectAttack(Attack)
     */
    Success attack();

    /**
     * Triggers the current opponent Yokimon {@link Attack}.
     *
     * @return success rate over opponent's attack
     */
    Success getAttacked();

    /**
     * Triggers end of the fight.
     *
     * @return to Logic whether the fight is over
     */
    boolean isOver();

    /**
     * @return whether my {@link Yokimon} won
     */
    boolean victory();

    /**
     * Method to calculate how many XP points my current Yokimon earns, in case of victory.
     *
     * @param yokimon the Yokimon whose XP points must be updated
     * @return xp points to be added
     */
    int getXP(Yokimon yokimon);

    /**
     * Useful for the View and for the {@link LevelUpLogic} mechanism.
     *
     * @return my party's {@link Yokimon} currently involved in the fight
     */
    Yokimon getCurrentMyYokimon();

    /**
     * @return the player's party
     */
    List<Yokimon> getPlayerParty();

    /**
     * Useful for the View.
     *
     * @return my opponent's {@link Yokimon} currently involved in the fight
     */
    Yokimon getCurrentOpponent();

    /**
     * The current state of the fight.
     *
     * @return the state.
     */
    State getState();

    /**
     * This is meant to display the life bar in the View.
     *
     * @param yokimon the {@link Yokimon} whose HP percentage must be shown
     * @return the percentage of HP points left.
     */
    double getHPPercentage(Yokimon yokimon);

    /**
     * This method must be used at each turn.
     *
     * @return if the player must attack first.
     */
    boolean playerIsFirst();

}
