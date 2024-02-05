package io.github.yokigroup.battle.XPCalculator;

import io.github.yokigroup.battle.fight.Yokimon;

import java.util.List;

public class dummyImplXPCalculator extends XPCalculator {

    private static final int MULTIPLIER = 100;

    /**
     *
     * @param defeatedOpps the Yokimons who have been defeated.
     * @return number of defeated Yokimons multiplied by a certain multiplier
     */
    @Override
    public int getXP(List<Yokimon> defeatedOpps) {
        if (!defeatedOpps.isEmpty()) {
            return defeatedOpps.size() * MULTIPLIER;
        }
        return 0;
    }
}
