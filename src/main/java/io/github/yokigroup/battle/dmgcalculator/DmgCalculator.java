package io.github.yokigroup.battle.dmgcalculator;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.fight.Fight;

/**
 * Used as a {@link Fight} component.
 * Its purpose is to calculate the actual damage (in terms of HP) induced by an {@link Attack}
 * @see Yokimon
 */
public interface DmgCalculator {

    /**
     * Calculates the actual damage provided by the attack.
     * @param attackingYokimon the offending Yokimon
     * @param attackedYokimon the offended Yokimon
     * @param attack the attack used by the first one
     * @return total damage points (to subtract from attacked Yokimon's HP)
     */
    int getDMG(Yokimon attackingYokimon, Yokimon attackedYokimon, Attack attack);
}
