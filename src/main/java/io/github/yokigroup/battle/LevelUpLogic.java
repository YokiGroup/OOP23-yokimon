package io.github.yokigroup.battle;

/**
 * Class interface used to relieve the YokimonImpl class from
 * heavy logical tasks and to manage the level up mechanics.
 */
public interface LevelUpLogic {

    /**
     * Set the stats of the yokimon given as parameter to what
     * they should be based on level and base stats.
     * @param yokimon a yokimon.
     */
    void setStats(Yokimon yokimon);

    /**
     * This class raise the level of the yokimon of the parameter plus.
     * So level = level + plus. stats will raise and new attacks may be learned.
     * @param yokimon a yokimon.
     * @param plus number to add to level.
     * @return returns levelUP if the yokimon just level up, newMove if it learns one or mode moves.
     */
    Yokimon.exp_code levelUp(Yokimon yokimon, int plus);

    /**
     * Set the moves of the yokimon given as parameter to what.
     * they should be based on it's level
     * @param yokimon a yokimon
     */
    void resetAttack(Yokimon yokimon);

    /**
     * Reset xp, xpNext and stats on what they should be
     * based on the level of the yokimon given.
     * @param yokimon a yokimon
     */
    void reset(Yokimon yokimon);
}
