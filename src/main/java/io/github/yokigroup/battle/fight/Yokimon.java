package io.github.yokigroup.battle.fight;

import java.util.Map;
import java.util.List;
public interface Yokimon {

    public enum Stats{
        ATK,
        DEF,
        SPD,
        HP
    }
    public enum BaseStats {
        B_ATK,
        B_DEF,
        B_HP,
        B_SPD
    }

    /**
     * Returns the name of the yokimon
     * @return String name
     */
    public String getName();

    /**
     * Returns all the Stats as a map of Integer
     * with Stats as a key
     * @return Map<Stats, Integer>
     */
    public Map<Stats, Integer> getALLStats();

    /**
     * Returns all the BASE Stats as a map of Integer
     * with Stats as a key
     * @return Map<Stats, Integer>
     */
    public Map<Stats, Integer> getALLBaseStats();

    /**
     * Returns the correspondent stat value
     * given in input
     * @param stat is the value of the Actual used stat
     * @return int value
     */
    public int getStat(Stats stat);

    /**
     * Returns the correspondent basestast value
     * given in input
     * @param baseStat is the value of the stat used for the level up
     * @return int value
     */
    public int getBaseStat(BaseStats baseStat);

    /**
     *Return an int with the value of yokimon current level
     * @return Int value
     */
    public int getLevel();


    /**
     *Return List of objects representing the current attacks of
     * the yokimon
     * @return List<Attacks> </Attacks>
     */
    public List<Attack> getAttacks();

    /**
     *Return actual HP of the yokimon
     * @return actual HP
     */
    public int getActualHP();

    /**
     *Return MAX HP of the yokimon
     * @return MAX HP
     */
    public int getMAXHP();

    /**
     * Does level up of the yokimon n-times
     * return false if the level up is not possible
     * @param n number of level
     * @return exit status
     */
    public boolean levelUP(int n);

    /**
     * Return true is the yokimon is Active, false
     * if is it not (0 HP)
     * @return boolean
     */
    public boolean Active();

    public enum exp_code{
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
    public exp_code takeXP(int n);
}
