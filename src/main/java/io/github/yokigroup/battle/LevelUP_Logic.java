package io.github.yokigroup.battle;

public interface LevelUP_Logic {

    public int nextBoundXP(int level);
    public void set_Stats(Yokimon yokimon);

    public void LevelUP(Yokimon yokimon, int plus);
}
