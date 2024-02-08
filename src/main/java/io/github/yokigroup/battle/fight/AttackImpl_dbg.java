package io.github.yokigroup.battle.fight;

public class AttackImpl_dbg implements Attack {
    private final String name;
    private final color color;
    private int attackPower;
    private effect effectID;

    public AttackImpl_dbg(String name, color color, int attackPower){
        this.name = name;
        this.color = color;
        this.attackPower = attackPower;
    }
    public AttackImpl_dbg(String name, color color){
        this(name, color, 0);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public color color() {
        return color;
    }

    @Override
    public int attackPower() {
        return attackPower;
    }

    @Override
    public effect getEffectID() {
        return effectID;
    }
}
