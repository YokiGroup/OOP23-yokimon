package io.github.yokigroup.battle;

import java.util.Objects;

/**
 * AttackImpl class represents an implementation of the Attack interface.
 * It provides methods to manipulate and manage Attack objects.
 */
public class AttackImpl implements Attack {
    private final String name;
    private final Color color;
    private final int power;
    private final Effect effect;

    /**
     * Constructs an AttackImpl object with the specified attributes.
     * @param name The name of the Attack.
     * @param color The color of the Attack.
     * @param power The power of the Attack.
     * @param effect The secondary effect of the Attack.
     */
    //Objects.requireNonNull(, " passed was null")
    public AttackImpl(final String name, final Color color, final int power, final Effect effect) {
        this.name = name;
        this.color = Objects.requireNonNull(color, "color passed was null");
        this.power = power;
        this.effect = Objects.requireNonNull(effect, "effect passed was null");
    }

    /**
     * Constructs an AttackImpl object with generic attributes.
     * @param name The name of the Attack.
     * @param color The color of the Attack.
     */
    public AttackImpl(final String name, final Color color) {
        this(name, color, 100, null);
    }

    @Override
    public final String getName() {
        return this.name;
    }

    @Override
    public final Color getColor() {
        return this.color;
    }

    @Override
    public final int attackPower() {
        return this.power;
    }

    @Override
    public final Effect getEffectID() {
        return this.effect;
    }
}
