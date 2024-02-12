package io.github.yokigroup.battle;

/**
 * This enum represents the different colors that a Yokimon
 * or an attack can take on.
 * A Yokimon who attacks with a move of the same color as himself provokes more damage.
 * An attack can be more or less powerful also according to the compatibility of the colors,
 * as written below.
 */
public enum Color {
    /**
     * Deals less damage to black but more to red.
     */
    PURPLE,
    /**
     * Deals less damage to purple but more to black.
     */
    RED,
    /**
     * Deals less damage to red to but more to purple.
     */
    BLACK,
    /**
     * It's neutral.
     */
    WHITE
}
