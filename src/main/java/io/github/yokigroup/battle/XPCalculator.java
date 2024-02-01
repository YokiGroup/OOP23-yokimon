package io.github.yokigroup.battle;


import java.util.List;

public abstract class XPCalculator {

    /**
     * @param defeatedOpps the Yokimons who have been defeated.
     * @return the number of points that must be added to my party's current Yokimon
     */
    abstract int getXP(List<Yokimon> defeatedOpps);
}