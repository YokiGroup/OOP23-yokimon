package io.github.yokigroup.battle.fight;

import java.util.Map;
import java.util.List;
public interface Yokimon {

    enum Stats{
        ATK,
        DEF,
        SPD,
        HP
    }
     enum BaseStats {
        B_ATK,
        B_DEF,
        B_HP,
        B_SPD
    }

    /**
     * Returns the name of the yokimon
     * @return String name
     */
     String getName();

    /**
     * Returns all the Stats as a map of Integer
     * with Stats as a key
     * @return Map<Stats, Integer>
     */
     Map<Stats, Integer> getALLStats();

    /**
     * Returns all the BASE Stats as a map of Integer
     * with Stats as a key
     * @return Map<Stats, Integer>
     */
     Map<Stats, Integer> getALLBaseStats();

    /**
     * Returns the correspondent stat value
     * given in input
     * @param stat is the value of the Actual used stat
     * @return int value
     */
     int getStat(Stats stat);

    /**
     * Returns the correspondent basestast value
     * given in input
     * @param baseStat is the value of the stat used for the level up
     * @return int value
     */
     int getBaseStat(BaseStats baseStat);

    /**
     *Return an int with the value of yokimon current level
     * @return Int value
     */
     int getLevel();


    /**
     *Return List of objects representing the current attacks of
     * the yokimon
     * @return List<Attacks> </Attacks>
     */
     List<Attack> getAttacks();

    /**
     *Return actual HP of the yokimon
     * @return actual HP
     */
     int getActualHP();

    /**
     *Return MAX HP of the yokimon
     * @return MAX HP
     */
     int getMAXHP();

    /**
     * Does level up of the yokimon n-times
     * return false if the level up is not possible
     * @param n number of level
     * @return exit status
     */
     boolean levelUP(int n);

    /**
     * Return true is the yokimon is Active, false
     * if is it not (0 HP)
     * @return boolean
     */
     boolean Active();

     enum exp_code{
        OK,
        LEVEL_UP,
        NEW_MOVE,
        MAX,
        ERROR
    }
    /**
     *Using this method to add a certain amount of xp to
     * a yokimon
     *It will automatically level up and learn new moves if
     *it needs to.
     * @param n int value of the xp to add
     * @return will return:
     * OK if the level of the yokimon doesn't change
     * LEVEL_UP if the yokimon just level-up
     * NEW_MOVE if the yokimon learn a new move
     * MAX if the xp can't no longer raise
     * ERROR if the method fails
     */
     exp_code takeXP(int n);
}
