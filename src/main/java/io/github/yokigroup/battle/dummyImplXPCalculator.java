package io.github.yokigroup.battle;

import java.util.List;

public class dummyImplXPCalculator extends XPCalculator {

    private static final int MULTIPLIER = 100;
    @Override
    public int getXP(List<Yokimon> defeatedOpps) {
        if (!defeatedOpps.isEmpty()) {
            return defeatedOpps.size() * MULTIPLIER;
        }
        return 0;
    }
}
