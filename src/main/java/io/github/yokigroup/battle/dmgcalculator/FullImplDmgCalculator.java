package io.github.yokigroup.battle.dmgcalculator;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.attack.Color;

/**
 * Complete version of {@link DmgCalculator}, that takes into consideration
 * the two {@link Yokimon} colors and the {@link Attack} color, and the colors' hierarchy as well.
 *
 * @see Color
 */
public class FullImplDmgCalculator implements DmgCalculator {

    private static final double STRONG = 2.0;
    private static final double NORMAL = 1.0;
    private static final double WEAK = 0.5;


    /**
     * This version uses a multiplier in case a Yokimon and the attack are of the same color.
     * It also considers a hierarchy which states as follows:
     * PURPLE beats RED beats BLACK beats PURPLE.
     * WHITE is neutral.
     *
     * @param attackingYokimon the offending Yokimon
     * @param attackedYokimon  the offended Yokimon
     * @param attack           the attack used by the attacking Yokimon
     * @return the actual damage (to subtract from the HP of the attacked Yokimon)
     */
    protected double getDMGdouble(final Yokimon attackingYokimon, final Yokimon attackedYokimon, final Attack attack) {

        final MultiplierDmgCalculator multipl = new MultiplierDmgCalculator();
        double total = multipl.getDMGdouble(attackingYokimon, attackedYokimon, attack);

        //hierarchy
        switch (attackingYokimon.getYokimonColor()) {
            case PURPLE -> {
                switch (attackedYokimon.getYokimonColor()) {
                    case BLACK, PURPLE -> {
                        total = total * WEAK;
                    }
                    case RED -> {
                        total = total * STRONG;
                    }
                    default -> {
                        total = total * NORMAL;
                    }
                }
            }
            case RED -> {
                switch (attackedYokimon.getYokimonColor()) {
                    case PURPLE, RED -> {
                        total = total * WEAK;
                    }
                    case BLACK -> {
                        total = total * STRONG;
                    }
                    default -> {
                        total = total * NORMAL;
                    }
                }
            }
            case BLACK -> {
                switch (attackedYokimon.getYokimonColor()) {
                    case RED, BLACK -> {
                        total = total * WEAK;
                    }
                    case PURPLE -> {
                        total = total * STRONG;
                    }
                    default -> {
                        total = total * NORMAL;
                    }
                }
            }
            default -> {
                total = total * NORMAL;
            }
        }

        return total;
    }

    /**
     * This version uses a multiplier in case a Yokimon and the attack are of the same color.
     * It also considers a hierarchy which states as follows:
     * PURPLE beats RED beats BLACK beats PURPLE.
     * WHITE is neutral.
     *
     * @param attackingYokimon the offending Yokimon
     * @param attackedYokimon  the offended Yokimon
     * @param attack           the attack used by the attacking Yokimon
     * @return the actual damage (to subtract from the HP of the attacked Yokimon)
     */
    @Override
    public int getDMG(final Yokimon attackingYokimon, final Yokimon attackedYokimon, final Attack attack) {
        return (int) getDMGdouble(attackingYokimon, attackedYokimon, attack);
    }
}
