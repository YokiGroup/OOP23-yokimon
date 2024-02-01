package battle;

@FunctionalInterface
public interface XPCalculator {

    /**
     * @param yokimon the yokimon who defeated an enemy and whose XP points must be updated
     * @return the number of points that must be added
     */
    int getXP(Yokimon yokimon);
}