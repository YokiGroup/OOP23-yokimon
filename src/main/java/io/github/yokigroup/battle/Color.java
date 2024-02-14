package io.github.yokigroup.battle;

/**
 * This enum represents the different colors that yokimon can take on or the color which an attack will have.
 * A yokimon which attack with a move of the same color of himself deal more damage.
 */
public enum Color {
    /**
     * The purple color type, deals less damage to black but more to red.
     */
    PURPLE,
    /**
     * The red color type, deals less damage to purple but more to black.
     */
    RED,
    /**
     * The black color type, deals less damage red to black but more to purple.
     */
    BLACK,
    /**
     * The white color type, no damage modifiers.
     */
    WHITE
}
