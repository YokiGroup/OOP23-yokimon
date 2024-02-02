package io.github.yokigroup.event.context;

import io.github.yokigroup.battle.Fight;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.Queue;

/**
 * Handles queuing fights and starting them when the process
 */
public class FightSubmodule implements Submodule{
    Optional<Fight> lastAnnouncedFight = Optional.empty();

    void addEncounter(Fight f){
        lastAnnouncedFight = Optional.ofNullable(f);
    }

    @Override
    public void process() {
        // TODO start fight
        return;
    }
}
