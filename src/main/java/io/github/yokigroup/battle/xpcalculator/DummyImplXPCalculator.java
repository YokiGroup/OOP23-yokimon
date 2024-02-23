package io.github.yokigroup.battle.xpcalculator;

import io.github.yokigroup.battle.yokimon.Yokimon;

import java.util.List;

/**
 * This version simply takes into consideration the number
 * of the opponent party Yokimons who have been defeated.
 */
public class DummyImplXPCalculator extends XPCalculator {

    private static final int MULTIPLIER = 100;

    /**
     * @param defeatedOpps the Yokimons who have been defeated.
     * @return number of defeated Yokimons multiplied by a certain multiplier
     */
    @Override
    public int getXP(final List<Yokimon> defeatedOpps) {
        if (!defeatedOpps.isEmpty()) {
            return defeatedOpps.size() * MULTIPLIER;
        }
        return 0;
    }
}
