package io.github.yokigroup.battle;


public abstract class XPCalculator {

    /**
     * @param yokimon the yokimon who defeated an enemy and whose XP points must be updated
     * @return the number of points that must be added
     */
    abstract int getXP(Yokimon yokimon);
}