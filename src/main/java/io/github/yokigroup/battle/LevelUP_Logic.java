package io.github.yokigroup.battle;

public interface LevelUP_Logic {

    public void set_Stats(Yokimon yokimon);

    public Yokimon.exp_code LevelUP(Yokimon yokimon, int plus);

    public void resetAttack(Yokimon yokimon);

    public void reset(Yokimon yokimon);
}
