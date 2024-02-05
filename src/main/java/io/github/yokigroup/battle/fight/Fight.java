package io.github.yokigroup.battle.fight;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.Attack;

public interface Fight {

    /**
     * different success rates trigger a different quote on the view
     */
    enum success {
        GOOD, SUPER, WEAK, FAIL
    }

    /**
     *
     * @param myAttack the attack that's meant to be used by my Yokimon
     * @return success rate over my Yokimon's attack
     */
    success attack(Attack myAttack);

    /**
     * @return success rate over opponent's attack
     */
    success getAttacked();

    /**
     * triggers end of the fight
     * @return to Logic whether the fight is over
     */
    boolean isOver();

    /**
     * @return whether my Yokimon won
     */
    boolean victory();

    /**
     *
     * @param yokimon the Yokimon whose xp points must be updated
     * @return xp points to be added
     */
    int getXP(Yokimon yokimon);

    /**
     * useful for the View and for the level-up mechanism
     * @return my party's Yokimon currently involved in the fight
     */
    Yokimon getCurrentMyYokimon();

    /**
     * useful for the View
     * @return my opponent's Yokimon currently involved in the fight
     */
    Yokimon getCurrentOpponent();

}
