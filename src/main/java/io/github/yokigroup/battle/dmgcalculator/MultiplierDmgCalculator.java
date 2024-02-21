package io.github.yokigroup.battle.dmgcalculator;

import io.github.yokigroup.battle.Attack;
import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.Color;

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
     * @param attackingYokimon the offending Yokimon
     * @param attackedYokimon the offended Yokimon
     * @param attack the attack used by the first one
     * @return the actual damage (to subtract from the HP of the attacked Yokimon)
     */
    @Override
    public int getDMG(final Yokimon attackingYokimon, final Yokimon attackedYokimon, final Attack attack) {
        return (int) getDMGdouble(attackingYokimon, attackedYokimon, attack);
    }
}
