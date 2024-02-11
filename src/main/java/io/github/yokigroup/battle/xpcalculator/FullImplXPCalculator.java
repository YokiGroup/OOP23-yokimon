package io.github.yokigroup.battle.xpcalculator;

import io.github.yokigroup.battle.Yokimon;

import java.util.List;

/**
 * Full version of XPCalculator. It takes into consideration the level of each
 * Yokimon from the opponent party who has been defeated during the fight.
 */
public final class FullImplXPCalculator extends XPCalculator {      //TODO MUST TEST THIS CLASS
    private static final int DEFAULT_TOTAL = 0;
    private static final int MULTIPLIER = 100;
    private static final int DIVISOR = 10;
    private static final int SINGLE = 1;

    @Override
    public int getXP(List<Yokimon> defeatedOpps) {
        int total = DEFAULT_TOTAL;
        for (var el: defeatedOpps) {
            total += MULTIPLIER * (SINGLE + el.getLevel() / DIVISOR);
        }
        return total;
    }
}
