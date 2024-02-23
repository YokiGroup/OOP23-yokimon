package io.github.yokigroup.battle.xpcalculator;

import io.github.yokigroup.battle.yokimon.Yokimon;

import java.util.List;

/**
 * Full version of XPCalculator. It takes into consideration the level of each
 * Yokimon from the opponent party who has been defeated during the fight.
 */
public final class FullImplXPCalculator extends XPCalculator {
    private static final int DEFAULT_TOTAL = 0;
    private static final int MULTIPLIER = 100;
    private static final int DIVISOR = 10;
    private static final int BASE = 1;

    @Override
    public int getXP(final List<Yokimon> defeatedOpps) {
        int total = DEFAULT_TOTAL;
        for (final var el: defeatedOpps) {
            final double delta = (double) el.getLevel() / DIVISOR;
            total += (int) (MULTIPLIER * (BASE + delta));
        }
        return total;
    }
}
