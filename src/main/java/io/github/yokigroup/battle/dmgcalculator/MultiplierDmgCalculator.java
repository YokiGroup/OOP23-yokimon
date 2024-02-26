package io.github.yokigroup.battle.dmgcalculator;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.attack.Color;
import io.github.yokigroup.util.Pair;

/**
 * This version of {@link DmgCalculator} takes into consideration
 * the two {@link Yokimon} color and the {@link Attack} color.
 * @see Color
 */
public class MultiplierDmgCalculator implements DmgCalculator {

    private static final double MULTIPLIER = 1.2;

    /**
     * This version uses a multiplier in case a Yokimon and the attack are of the same color.
     * @param attackingYokimon the offending Yokimon
     * @param attackedYokimon the offended Yokimon
     * @param attack the attack used by the first one
     * @return the actual damage (to subtract from the HP of the attacked Yokimon)
     */
    protected double getDMGdouble(final Yokimon attackingYokimon, final Yokimon attackedYokimon, final Attack attack) {

        final BasicImplDmgCalculator basic = new BasicImplDmgCalculator();
        double total = basic.getDMGdouble(attackingYokimon, attackedYokimon, attack);

        if (attackingYokimon.getYokimonColor().equals(attack.getColor())) {
            total = total * MULTIPLIER;
        }
        if (attackedYokimon.getYokimonColor().equals(attack.getColor())) {
            total = total / MULTIPLIER;
        }

        return total;
    }

    /**
     * This version uses a multiplier in case a Yokimon and the attack are of the same color.
     *
     * @param attackingYokimon the offending Yokimon
     * @param attackedYokimon  the offended Yokimon
     * @param attack           the attack used by the first one
     * @return pair containing the actual damage (to subtract from attacked Yokimon's HP) and the
     * success value of the attack
     */
    @Override
    public Pair<Integer, Fight.Success> getDMG(final Yokimon attackingYokimon,
                                               final Yokimon attackedYokimon, final Attack attack) {
        return new Pair<>((int) getDMGdouble(attackingYokimon, attackedYokimon, attack), Fight.Success.GOOD);
    }
}
