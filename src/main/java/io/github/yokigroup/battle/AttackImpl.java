package io.github.yokigroup.battle;

public class AttackImpl implements Attack{
    private final String name;
    private final color color;
    private final int power;
    private final effect effect;

    public AttackImpl(String name, Attack.color color, int power, Attack.effect effect) {
        this.name = name;
        this.color = color;
        this.power = power;
        this.effect = effect;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public color color() {
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
