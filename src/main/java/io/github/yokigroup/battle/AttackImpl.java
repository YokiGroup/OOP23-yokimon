package io.github.yokigroup.battle;

import java.util.Objects;

/**
 * AttackImpl class represents an implementation of the Attack interface.
 * It provides methods to manipulate and manage Attack objects.
 */
public class AttackImpl implements Attack {
    private final int id;
    private final String name;
    private final Color color;
    private final int power;
    private final Effect effect;

    /**
     * Constructs an AttackImpl object with the specified attributes.
     * @param id id of the attack
     * @param name The name of the Attack.
     * @param color The color of the Attack.
     * @param power The power of the Attack.
     * @param effect The secondary effect of the Attack.
     *
     */
    //Objects.requireNonNull(, " passed was null")
    public AttackImpl(final int id, final String name, final Color color, final int power, final Effect effect) {
        this.id = id;
        this.name = name;
        this.color = Objects.requireNonNull(color, "color passed was null");
        this.power = power;
        this.effect = Objects.requireNonNull(effect, "effect passed was null");
    }

    /**
     * Constructs an AttackImpl object with generic attributes.
     * @param id id of the attack
     * @param name The name of the Attack.
     * @param color The color of the Attack.
     */
    public AttackImpl(final int id, final String name, final Color color) {
        this(id, name, color, 100, Effect.NONE);
    }

    @Override
    public final int getId() {
        return 0;
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

    /**
     * Control if this attack is equal to another.
     * @param o Objects
     * @return boolean
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AttackImpl attack = (AttackImpl) o;
        return id == attack.id && power == attack.power && Objects.equals(name, attack.name)
                && color == attack.color && effect == attack.effect;
    }

    /**
     * Return a int for the hash code.
     * @return integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, power, effect);
    }
}
