package io.github.yokigroup.battle;

public class AttackImpl implements Attack{
    private final String name;
    private final Color color;
    private final int power;
    private final effect effect;

    public AttackImpl(String name, Color color, int power, Attack.effect effect) {
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
