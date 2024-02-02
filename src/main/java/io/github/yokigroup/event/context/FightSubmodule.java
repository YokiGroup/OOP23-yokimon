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
    PartySubmodule party; // keeping a reference to the party submodule to get a list of yokimons whenever necessary

    public FightSubmodule(PartySubmodule party){
        this.party = party;
    }

    public void addEncounter(Fight f){
        lastAnnouncedFight = Optional.ofNullable(f);
    }

    public Optional<Fight> getLastAnnouncedFight(){
        return lastAnnouncedFight;
    }

    @Override
    public void process() {
        if(lastAnnouncedFight.isPresent()){
            // TODO start fight
        }
    }
}
