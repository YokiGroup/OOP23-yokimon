package io.github.yokigroup.battle;
//import java.util.HashMap;
public interface Attack {

    public enum effect{
        NONE
    }

    /**
     * The color associated to a Yokimon. This affects each attack success rate.
     */
    public enum color{
        /**
         * Color type red.
         */
        RED,
        /**
         * Color type yellow.
         */
        YELLOW,
        /**
         * Color type green.
         */
        GREEN,
        /**
         * Color type blue.
         */
        BLUE
    }
    /**
     *Return the name of the Attack
     * @return String name
     */
    public String getName();

    /**
     *Return a reference of the color
     * @return return enum color
     */
    public Attack.color color();
    /**
     *Return power of the attack (0 if it doesn't deal damage)
     * @return integer atk pwr
     */
    public int attackPower();

    /**
     * Return the secondary effect of the attack
     * @return enum effect
     */
    public Attack.effect getEffectID();

}
