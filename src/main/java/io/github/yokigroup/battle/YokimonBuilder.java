package io.github.yokigroup.battle;

import io.github.yokigroup.battle.Yokimon;

public interface YokimonBuilder {
    void setName();
    void setColor();
    void setBaseStats();
    void setStats();
    void setLevel();
    void setMAX_HP();
    void setACTUAL_HP();
    void setGrowRate();
    void setXP();
    void setXP_NEXTLEV();
    void setMoves();
    void setActive();
    Yokimon getYokimon();
}
