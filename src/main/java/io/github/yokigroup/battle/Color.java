package io.github.yokigroup.battle;

/**
 * This enum represents the different colors that yokimon can take on
 * or the color which an attack will have
 * A yokimon which attack with a move of the same color of himself deal more damage
 * According to the compatibility of the colors:
 * purple -> deal less damage to black but more to red \
 * red -> deal less purple damage to  but more to black \
 * black -> deal less red damage to  but more to purple \
 * white -> has no damage modifier
 */
public enum Color {
    PURPLE,
    RED,
    BLACK,
    WHITE
}
