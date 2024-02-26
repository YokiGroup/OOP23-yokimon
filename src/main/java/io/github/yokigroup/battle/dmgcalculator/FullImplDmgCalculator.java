package io.github.yokigroup.battle.dmgcalculator;

import io.github.yokigroup.battle.attack.Attack;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.battle.attack.Color;
import io.github.yokigroup.util.Pair;

/**
 * Complete version of {@link DmgCalculator}, that takes into consideration
 * the two {@link Yokimon} colors and the {@link Attack} color, and the colors' hierarchy as well.
 * It also multiplies the damage by some values in a randomized way.
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
    protected Pair<Double, Fight.Success> getDMGdouble(final Yokimon attackingYokimon, final Yokimon attackedYokimon, final Attack attack) {
       final MultiplierDmgCalculator multipl = new MultiplierDmgCalculator();
       double total = multipl.getDMGdouble(attackingYokimon, attackedYokimon, attack);
       final Color attackedYokimonColor = attackedYokimon.getYokimonColor();
       Fight.Success attackSuccess;

        //hierarchy
        attackSuccess = switch (attackingYokimon.getYokimonColor()) {
            case PURPLE -> switch (attackedYokimonColor) {
                    case BLACK, PURPLE -> Fight.Success.WEAK;
                    case RED -> Fight.Success.SUPER;
                    default -> Fight.Success.GOOD;
                };

            case RED -> switch (attackedYokimonColor) {
                    case PURPLE, RED -> Fight.Success.WEAK;
                    case BLACK -> Fight.Success.SUPER;
                    default -> Fight.Success.GOOD;
                };

            case BLACK -> switch (attackedYokimonColor) {
                    case RED, BLACK -> Fight.Success.WEAK;
                    case PURPLE -> Fight.Success.SUPER;
                    default -> Fight.Success.GOOD;
                };

            default -> Fight.Success.GOOD;
        };

        total = switch (attackSuccess) {
            case WEAK -> total / WEAK;
            case SUPER -> total * STRONG;
            case GOOD -> total * NORMAL;
            default -> total;
        };

        return new Pair<>(total, attackSuccess);
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
     * @return a pair containing the actual damage (to subtract from attacked Yokimon's HP) and the
     * success value of the attack
     */
    @Override
    public Pair<Integer, Fight.Success> getDMG(final Yokimon attackingYokimon,
                                               final Yokimon attackedYokimon, final Attack attack) {
        final var finalDamage = getDMGdouble(attackingYokimon, attackedYokimon, attack);
        return new Pair<>(finalDamage.x().intValue(),  finalDamage.y());
    }
}
