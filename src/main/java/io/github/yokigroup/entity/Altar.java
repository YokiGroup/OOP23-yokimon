package io.github.yokigroup.entity;



import io.github.yokigroup.battle.Yokimon;

import java.util.Optional;

/**
 * an altar which gives you a yokimon at the first interaction
 */
public interface Altar extends Entity{
    public enum altarState{
        USED,
        NEW
    }

    /**
     * Return the current state of the Altar (if it's used or not)
     * @return State
     */
    public Altar.altarState getState();

    /**
     * return a Yokimon if the altar is new otherwise
     * an optional empty
     * @return Optional<Yokimon>
     */
    public Optional<Yokimon> getNewYokimon();


}
