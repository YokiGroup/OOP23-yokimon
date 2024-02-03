package io.github.yokigroup.battle.XPCalculator;


import io.github.yokigroup.battle.fight.Yokimon;

import java.util.List;

public abstract class XPCalculator {

    /**
     * @param defeatedOpps the Yokimons who have been defeated.
     * @return the number of points that must be added to my party's current Yokimon
     */
    public abstract int getXP(List<Yokimon> defeatedOpps);
}