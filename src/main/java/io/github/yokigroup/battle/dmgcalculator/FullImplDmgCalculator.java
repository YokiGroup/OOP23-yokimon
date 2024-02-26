package io.github.yokigroup.battle.dmgcalculator;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.attack.Color;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.util.WeightedPoolImpl;

/**
 * Complete version of {@link DmgCalculator}, that takes into consideration
 * the two {@link Yokimon} colors and the {@link Attack} color, and the colors' hierarchy as well.
 * It also multiplies the damage by some values in a randomized way.
 *
 * @see Color
 */
public class FullImplDmgCalculator implements DmgCalculator {
    /* attack success pool */
    private static final WeightedPool<Fight.Success> SUCCESS_WEIGHTED_POOL = new WeightedPoolImpl<>();
    static final float FAIL_RATE = 0.05f;
    static final float WEAK_RATE = 0.2f;
    static final float GOOD_RATE = 0.7f;
    static final float SUPER_RATE = 0.2f;
    static {
        SUCCESS_WEIGHTED_POOL.addElement(Fight.Success.FAIL, FAIL_RATE);
        SUCCESS_WEIGHTED_POOL.addElement(Fight.Success.WEAK, WEAK_RATE);
        SUCCESS_WEIGHTED_POOL.addElement(Fight.Success.GOOD, GOOD_RATE);
        SUCCESS_WEIGHTED_POOL.addElement(Fight.Success.SUPER, SUPER_RATE);
    }

    private static final double STRONG = 2.0;
    private static final double NORMAL = 1.0;
    private static final double WEAK = 0.5;

    private int addDamageModifiers(final Fight.Success attackSuccessValue, final int damage) {
        return switch (attackSuccessValue) {
            case FAIL -> 0;
            case WEAK -> damage / 2;
            case SUPER -> damage * 2;
            default -> damage;
        };
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
    protected double getDMGdouble(final Yokimon attackingYokimon, final Yokimon attackedYokimon, final Attack attack) {
       final MultiplierDmgCalculator multipl = new MultiplierDmgCalculator();
       double total = multipl.getDMGdouble(attackingYokimon, attackedYokimon, attack);
       final Color attackedYokimonColor = attackedYokimon.getYokimonColor();

        //hierarchy
        total = switch (attackingYokimon.getYokimonColor()) {
            case PURPLE -> switch (attackedYokimonColor) {
                    case BLACK, PURPLE -> total * WEAK;
                    case RED -> total * STRONG;
                    default -> total * NORMAL;
                };

            case RED -> switch (attackedYokimonColor) {
                    case PURPLE, RED -> total * WEAK;
                    case BLACK -> total * STRONG;
                    default -> total * NORMAL;
                };

            case BLACK -> switch (attackedYokimonColor) {
                    case RED, BLACK -> total * WEAK;
                    case PURPLE -> total * STRONG;
                    default -> total * NORMAL;
                };

            default -> total * NORMAL;
        };
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
     * @return      * @return a pair containing the actual damage (to subtract from attacked Yokimon's HP) and the
     * success value of the attack
     */
    @Override
    public Pair<Integer, Fight.Success> getDMG(final Yokimon attackingYokimon,
                                               final Yokimon attackedYokimon, final Attack attack) {
        final Fight.Success randomizedSuccess = SUCCESS_WEIGHTED_POOL.getRandomizedElement();
        final int finalDamage = (int) getDMGdouble(attackingYokimon, attackedYokimon, attack);
        return new Pair<>(addDamageModifiers(randomizedSuccess, finalDamage), randomizedSuccess);
    }
}
