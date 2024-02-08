package io.github.yokigroup.battle;



import java.util.Map;
import java.util.List;
public interface Yokimon {
    /**
    * Stats represent the stats that the yokimon has at this moment
    * so just use this
    * */
    public enum Stats{
        ATK,
        DEF,
        SPD,
        HP;
    }

    public enum GrowRate {
        SLOW(0.75),
        MEDIUM(1.00),
        FAST(1.25);
        private final double rate;
        GrowRate(double rate) {
            this.rate = rate;
        }
        public double get(){
            return rate;
        }
    }
    /**
     * Returns the name of the yokimon
     * @return String name
     */
    public String getName();

    /**
     * return the color of the yokimon
     * @return Color
     */
    public Color getYokimonColor();
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
    public int getBaseStat(Stats baseStat);

    /**
     *
     *
     */
    public void setStat(Stats change, int newValue);

    /**
     *Return an int with the value of yokimon current level
     * @return Int value
     */
    public int getLevel();
    /**
     *
     * @param n value of the new level of the yokimon
     */
    public exp_code setLevel(int n);

    /**
     * Does level up of the yokimon n-times
     * return false if the level up is not possible
     * @param n number of level
     * @return exit status
     */
    public boolean levelUP(int n);

    /**
     *Return List of objects representing the current attacks of
     * the yokimon
     * @return List<Attacks> </Attacks>
     */
    public List<Attack> getAttacks();

    /**
     *gives a new attack to the yokimon
     */
    public void addAttack(Attack newAttack);

    /**
     * Return a map with learnable attacks
     * key: Integer which represent the level at which the yokimon learns the attack
     * value: the actual attack
     * @return Map of as key level and Attack as value
     */
    public Map<Integer, Attack> getLernableAttacks();
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
     * change the actual hp with a new value
     * @param newValue the new value assigned to actualHP
     */
    public void setActualHP(int newValue);

    /**
     * change the max hp with a new value
     * @param newValue the new value assigned to maxHP
     */
    public void setMaxHP(int newValue);

    /**
     * subtracts to the Actual HP of the yokimon the parameter damage
     * if the hp of the yokimon reaches zero, it returns true
     * otherwise it return true
     * @param damage out put of the damage
     * @return true if the yokimon is still active, false if is not
     */
    public boolean takeDamage(int damage);

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

    /**
     * return the exp needed to reach the next
     * level
     * @return int ExpNext
     */
    public double getExpNext();
    /**
     * this method set a new bond to
     * the amount of exp neaded for level-up
     * to the next level
     */
    public void setExpNext(double newExp);

    /**
     * this method set a new value on the current exp
     * of the yokimon
     */
    public void setExp(double exp);



}
