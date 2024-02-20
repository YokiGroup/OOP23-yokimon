package io.github.yokigroup.battle;

/**
 * Class interface used to relieve the YokimonImpl class from
 * heavy logical tasks and to manage the level up mechanics.
 */
public interface LevelUpLogic {

    /**
     * Set the stats of the yokimon given as parameter to what
     * they should be based on level and base stats.
     *
     * @param yokimon a yokimon.
     */
    void setStats(Yokimon yokimon);

    /**
     * This class raise the level of the yokimon of the parameter plus.
     * So level = level + plus. stats will raise and new attacks may be learned.
     *
     * @param yokimon a yokimon.
     * @param plus    number to add to level.
     * @return returns levelUP if the yokimon just level up, newMove if it learns one or mode moves.
     */
    Yokimon.ExpCode levelUp(Yokimon yokimon, int plus);


    /**
     * Reset xp, xpNext and stats on what they should be
     * based on the level of the yokimon given.
     *
     * @param yokimon a yokimon
     */
    void reset(Yokimon yokimon);
}
