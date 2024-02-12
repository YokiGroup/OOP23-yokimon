package io.github.yokigroup.battle;

import java.util.Map;
import java.util.List;

/**
 * Yokimon interface represents a Yokimon, a creature with various stats and abilities.
 */
public interface Yokimon {
    /**
    * Stats represent the stats of the yokimon.
     * art -> attack def -> defence spd -> speed
     * hp -> health point.
    * */
    public enum Stats{
        ATK,
        DEF,
        SPD,
        HP
    }

    /**
     * Represents the growth rate of the Yokimon.
     * the value assumed will be multiplied when the yokimon take xp.
     */
    public enum GrowthRate {
        SLOW(0.75),
        MEDIUM(1.00),
        FAST(1.25);
        private final double rate;
        GrowthRate(double rate) {
            this.rate = rate;
        }
        public double get(){
            return rate;
        }
    }

    /**
     * Returns the GrowRate of the yokimon.
     * @return GrowRate of the yokimon
     */
    public GrowthRate getGrowRate();

    /**
     * Returns the name of the yokimon.
     * @return String name
     */
    public String getName();

    /**
     * return the color of the yokimon.
     * @return Color
     */
    public Color getYokimonColor();

    /**
     * Returns all the Stats as a map of Integer
     * with Stats as a key.
     * @return Map<Stats, Integer>
     */
    public Map<Stats, Integer> getAllStats();

    /**
     * Returns all the BASE Stats as a map of Integer
     * with Stats as a key.
     * @return Map<Stats, Integer>
     */
    public Map<Stats, Integer> getAllBaseStats();

    /**
     * Returns the correspondent stat value
     * given in input.
     * @param stat is the value of the Actual used stat
     * @return int value
     */
    public int getStat(Stats stat);

    /**
     * Returns the value of the specified base stat.
     * @param baseStat The base stat to retrieve
     * @return int Value of the specified base stat
     */
    public int getBaseStat(Stats baseStat);

    /**
     * Sets the value of the specified stat.
     * @param change The stat to change
     * @param newValue The new value of the stat
     */
    public void setStat(Stats change, int newValue);

    /**
     *Return an int with the value of yokimon current level.
     * @return Int value
     */
    public int getLevel();
    /**
     * Sets the level of the Yokimon.
     * @param n The new level of the Yokimon
     */
    public void setLevel(int n);

    /**
     * Does level up of the yokimon n-times
     * return false if the level up is not possible.
     * @param n number of level
     * @return exit status
     */
    public boolean levelUP(int n);

    /**
     * Returns a list of attacks the Yokimon currently has.
     * @return List<Attack> List of attacks
     */
    public List<Attack> getAttacks();

    /**
     * Gives a new attack to the yokimon.
     * @param newAttack new Attack
     */
    public void addAttack(Attack newAttack);

    /**
     * Return a map with learnable attacks.
     * key: Integer which represent the level at which the yokimon learns the attack.
     * value: the actual attack.
     * @return Map of as key level and Attack as value
     */
    public Map<Integer, Attack> getLearnableAttacks();
    /**
     *Return actual hp of the yokimon.
     * @return actual hp
     */
    public int getActualHp();

    /**
     *Return MAX hp of the yokimon.
     * @return MAX hp
     */
    public int getMaxHp();

    /**
     * change the actual hp with a new value.
     * @param newValue the new value assigned to actual hp
     */
    public void setActualHp(int newValue);

    /**
     * change the max hp with a new value.
     * @param newValue the new value assigned to max hp
     */
    public void setMaxHp(int newValue);

    /**
     * subtracts to the Actual Hp of the yokimon the parameter damage
     * if the Hp of the yokimon reaches zero, it returns true
     * otherwise it returns true.
     * @param damage out put of the damage
     * @return true if the yokimon is still active, false if is not
     */
    public boolean takeDamage(int damage);

    /**
     * Return true is the yokimon is Active, false
     * if is it not (0 hp).
     * @return boolean
     */
    public boolean active();


    /**
     * enum used to specify return status of various methods.
     */
    public enum exp_code{
        OK,
        LEVEL_UP,
        NEW_MOVE
    }

    /**
     * Return the current xp amount of the yokimon.
     * @return double current xp
     */
    public double getXp();

    /**
     * Return the current xpNextLevel amount of the yokimon;
     * @return double getNextLevelXp
     */
    public double getNextXp();
    /**
     * Adds a certain amount of XP to the Yokimon, potentially triggering level-up and
     * learning new moves.
     * @param n Amount of XP to add
     * @return exp_code Status of the XP addition
     * ok if the level of the yokimon doesn't change
     * level-up if the yokimon just level-up
     * newMove if the yokimon learn a new move
     * max if the xp can't no longer raise
     * error if the method fails
     */
    public exp_code takeXp(int n);


    /**
     * this method set a new bond to
     * the amount of exp needed for level-up
     * to the next level.
     */
    public void setExpNext(double newExp);

    /**
     * this method set a new value on the current exp
     * of the yokimon.
     */
    public void setExp(double exp);

}
