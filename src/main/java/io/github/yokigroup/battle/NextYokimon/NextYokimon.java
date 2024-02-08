package io.github.yokigroup.battle.NextYokimon;

import io.github.yokigroup.battle.Yokimon;

import java.util.List;
import java.util.Optional;

/**
 * This is meant to select the best next Yokimon to use in the fight, once the current one is defeated.
 * The choice is based on different criteria, based on the different implementations.
 */
public abstract class NextYokimon {
    /**
     * @param party the party from which it must be chosen the next one to fight, once the current one is defeated
     * @return the party member chosen
     */
    public abstract Optional<Yokimon> getNext(List<Yokimon> party);
}
