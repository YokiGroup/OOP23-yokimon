package io.github.yokigroup.battle;
//import java.util.HashMap;

/**
 * Attack interface represents an attack that a Yokimon can perform.
 */
public interface Attack {

    /**
     * The status effect of the Attack.
     */
    enum Effect {
        /**
         * No attack status effect.
         */
        NONE
    }

    /**
     * Return the name of the Attack.
     * @return String name.
     */
    String getName();

    /**
     * Return a reference of the color.
     * @return return enum color.
     */
    Color getColor();
    /**
     *Return power of the attack (0 if it doesn't deal damage).
     * @return integer atk pwr.
     */
    int attackPower();

    /**
     * Return the secondary effect of the attack.
     * @return enum effect.
     */
    Effect getEffectID();

}
