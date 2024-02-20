package io.github.yokigroup.battle;

import java.util.Objects;

/**
 * Record of an Attack.
 *
 * @param id     id for json loading
 * @param name   name of the attack
 * @param color  color of the attack
 * @param power  power of the attack
 * @param effect effect, for now is not
 */
public record AttackImpl(int id, String name, Color color, int power, Attack.Effect effect) implements Attack {


    /**
     * Constructs an AttackImpl object with generic attributes.
     *
     * @param id    id of the attack
     * @param name  The name of the Attack.
     * @param color The color of the Attack.
     */
    public AttackImpl(final int id, final String name, final Color color) {
        this(id, name, color, 100, Effect.NONE);
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public int attackPower() {
        return this.power;
    }

    @Override
    public Effect getEffectID() {
        return this.effect;
    }

    /**
     * Control if this attack is equal to another.
     *
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
        final AttackImpl attack = (AttackImpl) o;
        return id == attack.id && power == attack.power && Objects.equals(name, attack.name)
                && color == attack.color && effect == attack.effect;
    }

    /**
     * Return an int for the hash code.
     *
     * @return integer
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, color, power, effect);
    }
}
