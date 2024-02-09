package io.github.yokigroup.battle;

/**
 * AttackImpl class represents an implementation of the Attack interface.
 * It provides methods to manipulate and manage Attack objects.
 */
public class AttackImpl implements Attack{
    private final String name;
    private final Color color;
    private final int power;
    private final effect effect;

    /**
     * Constructs an AttackImpl object with the specified attributes.
     * @param name The name of the Attack
     * @param color The color of the Attack
     * @param power The power of the Attack
     * @param effect The secondary effect of the Attack
     */
    public AttackImpl(String name, Color color, int power, Attack.effect effect) {
        this.name = name;
        this.color = color;
        this.power = power;
        this.effect = effect;
    }

    public AttackImpl(String name, Attack.color color){
        this(name,color,100,null);
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
    public effect getEffectID() {
        return this.effect;
    }
}
