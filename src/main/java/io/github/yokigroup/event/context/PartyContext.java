package io.github.yokigroup.event.context;

import io.github.yokigroup.battle.Yokimon;

import java.util.List;

public interface PartyContext extends Context{
    void addYokimon();
    List<Yokimon> listYokimons();
    boolean removeYokimon();
}
