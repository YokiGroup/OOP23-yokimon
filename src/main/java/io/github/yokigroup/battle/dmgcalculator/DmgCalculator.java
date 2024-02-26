package io.github.yokigroup.battle.dmgcalculator;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.util.Pair;

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
     * @return a pair containing the total damage points (to subtract from attacked Yokimon's HP) and the
     * success value of the attack
     */
    Pair<Integer, Fight.Success> getDMG(Yokimon attackingYokimon, Yokimon attackedYokimon, Attack attack);
}
