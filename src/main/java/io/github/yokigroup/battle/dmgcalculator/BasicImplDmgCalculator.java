package io.github.yokigroup.battle.dmgcalculator;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.util.Pair;

/**
 * Basic version for {@link DmgCalculator}, that only takes into consideration
 * the two {@link Yokimon} stats and the {@link Attack} power.
 */
public class BasicImplDmgCalculator implements DmgCalculator {
    private static final int DIVISOR = 4;

    /**
     * This version exclusively uses the power of the attack and the Yokimons' stats.
     * @param attackingYokimon the offending Yokimon
     * @param attackedYokimon the offended Yokimon
     * @param attack the attack used by the first one
     * @return the actual damage (to subtract from the HP of the attacked Yokimon)
     */
    protected double getDMGdouble(final Yokimon attackingYokimon, final Yokimon attackedYokimon, final Attack attack) {
        return (double) (attackingYokimon.getStat(Yokimon.Stats.ATK) * attack.attackPower())
                / (attackedYokimon.getStat(Yokimon.Stats.DEF) * DIVISOR);
    }

    /**
     * This version exclusively uses the power of the attack and the Yokimons' stats.
     *
     * @param attackingYokimon the offending Yokimon
     * @param attackedYokimon  the offended Yokimon
     * @param attack           the attack used by the first one
     * @return a pair containing the actual damage (to subtract from attacked Yokimon's HP) and the
     * success value of the attack
     */
    @Override
    public Pair<Integer, Fight.Success> getDMG(final Yokimon attackingYokimon,
                                               final Yokimon attackedYokimon, final Attack attack) {
        return new Pair<>((int) getDMGdouble(attackingYokimon, attackedYokimon, attack), Fight.Success.GOOD);
    }
}
