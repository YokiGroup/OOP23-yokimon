package io.github.yokigroup.battle;

import java.util.Map;
import java.util.List;

/**
 * Yokimon interface represents a Yokimon, a creature with various stats and abilities.
 */
public interface Yokimon {
    /**
     * Stats represent the stats of the yokimon.
     **/
    enum Stats {
        /**
         * The attack power of the yokimon.
         */
        ATK,
        /**
         * The defence amount of the yokimon.
         */
        DEF,
        /**
         * The speed of the yokimon.
         */
        SPD,
        /**
         * The health points of the yokimon.
         */
        HP
    }

    /**
     * Represents the growth rate of the Yokimon.
     * The value assumed will be multiplied when the yokimon take xp.
     */
    enum GrowthRate {
        /**
         * Slow growth rate.
         */
        SLOW(0.75d),
        /**
         * Medium growth rate.
         */
        MEDIUM(1.00d),
        /**
         * Fast growth rate.
         */
        FAST(1.25d);
        private final double rate;

        GrowthRate(final double rate) {
            this.rate = rate;
        }

        /**
         * @return The value of the growth rate.
         */
        public double get() {
            return rate;
        }
    }

    /**
     * Return the id of the yokimon.
     *
     * @return return the id of the yokimon
     */
    int getId();

    /**
     * @return The GrowthRate of the yokimon.
     */
    GrowthRate getGrowRate();

    /**
     * @return The name of the yokimon.
     */
    String getName();

    /**
     * @return Color of the yokimon.
     */
    Color getYokimonColor();

    /**
     * Returns all the Stats as a map of Integer
     * with Stats as a key.
     *
     * @return All stats.
     */
    Map<Stats, Integer> getAllStats();

    /**
     * Returns all the BASE Stats as a map of Integer
     * with Stats as a key.
     *
     * @return All base stats.
     */
    Map<Stats, Integer> getAllBaseStats();

    /**
     * Returns the correspondent stat value
     * given in input.
     *
     * @param stat Is the value of the Actual used stat.
     * @return Int value.
     */
    int getStat(Stats stat);

    /**
     * Returns the value of the specified base stat.
     *
     * @param baseStat The base stat to retrieve.
     * @return Int value of the specified base stat.
     */
    int getBaseStat(Stats baseStat);

    /**
     * Sets the value of the specified stat.
     *
     * @param newStats newStats
     */
    void setStats(Map<Stats, Integer> newStats);

    /**
     * Return an int with the value of yokimon current level.
     *
     * @return Int value.
     */
    int getLevel();

    /**
     * Sets the level of the Yokimon.
     *
     * @param n The new level of the Yokimon.
     */
    void setLevel(int n);

    /**
     * Does level up of the yokimon n-times
     * return false if the level up is not possible.
     *
     * @param n Number of level.
     */
    void levelUP(int n);

    /**
     * Returns a list of attacks the Yokimon currently has.
     *
     * @return List of attacks.
     */
    List<Attack> getAttacks();

    /**
     * Reset the list of attacks.
     *
     * @param attacks List of attacks
     */
    void setAttacks(List<Attack> attacks);

    /**
     * Gives a new attack to the yokimon.
     *
     * @param newAttack New attack.
     */
    void addAttack(Attack newAttack);

    /**
     * Return a map with learnable attacks.
     * Key: Integer which represent the level at which the yokimon learns the attack.
     * Value: the actual attack.
     *
     * @return Map of as key level and Attack as value.
     */
    Map<Integer, Attack> getLearnableAttacks();

    /**
     * Return actual hp of the yokimon.
     *
     * @return Actual hp.
     */
    int getActualHp();

    /**
     * Returns max hp of the yokimon.
     *
     * @return Max hp.
     */
    int getMaxHp();

    /**
     * Change the actual hp with a new value.
     *
     * @param newValue The new value assigned to actual hp.
     */
    void setActualHp(int newValue);

    /**
     * Change the max hp with a new value.
     *
     * @param newValue The new value assigned to max hp.
     */
    void setMaxHp(int newValue);

    /**
     * Set the level_up logic
     * @param logic Logic level_up
     */
   // void setLevelUPLogic(LevelUpLogic logic);
    /**
     * Subtracts to the Actual Hp of the yokimon the parameter damage
     * if the Hp of the yokimon reaches zero, it returns true
     * otherwise it returns true.
     *
     * @param damage out put of the damage
     * @return True if the yokimon is still active, false if is not.
     */
    boolean takeDamage(int damage);

    /**
     * @return True if the yokimon is Active, false if it is dead.
     */
    boolean active();

    /**
     * Enum used to specify return status of various methods.
     */
    enum ExpCode {
        /**
         * If the level of the yokimon hasn't changed.
         */
        OK,
        /**
         * If the yokimon has leveled up.
         */
        LEVEL_UP,
        /**
         * If the yokimon has learned a new move.
         */
        NEW_MOVE
    }

    /**
     * Return the current xp amount of the yokimon.
     *
     * @return Double current xp.
     */
    double getXp();

    /**
     * Adds a certain amount of XP to the Yokimon, potentially triggering level-up and
     * learning new moves.
     *
     * @param n Amount of XP to add
     * @return ExpCode Status of the XP addition
     */
    ExpCode takeXp(int n);

    /**
     * This method set a new bound to
     * the amount of exp needed for level-up
     * to the next level.
     *
     * @param newExp The new bound of xp.
     */
    void setExpNext(double newExp);

    /**
     * This method set a new value on the current exp
     * of the yokimon.
     *
     * @param exp The xp value to set the yokimon to.
     */
    void setExp(double exp);
}
