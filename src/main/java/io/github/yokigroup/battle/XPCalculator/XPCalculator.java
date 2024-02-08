package io.github.yokigroup.battle.XPCalculator;

import io.github.yokigroup.battle.Yokimon;

import java.util.List;

/**
 * It calculates the number of XP points that, in case of victory, my current Yokimon will earn.
 * Different criteria for the choice are applied for different implementations of this interface.
 */
public abstract class XPCalculator {

    /**
     * @param defeatedOpps the Yokimons who have been defeated.
     * @return the number of points that must be added to my party's current Yokimon
     */
    public abstract int getXP(List<Yokimon> defeatedOpps);
}