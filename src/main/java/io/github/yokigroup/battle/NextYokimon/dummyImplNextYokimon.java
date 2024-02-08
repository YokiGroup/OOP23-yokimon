package io.github.yokigroup.battle.NextYokimon;

import io.github.yokigroup.battle.fight.Yokimon;

import java.util.List;
import java.util.Optional;

public class dummyImplNextYokimon extends NextYokimon {

    /**
     *
     * @param party the party from which it must be chosen the next one to fight, once the current one is defeated
     * @return the first element of the party list if there's any, otherwise an empty Optional
     */
    @Override
    public Optional<Yokimon> getNext(List<Yokimon> party) {
        if (!party.isEmpty()) {
            return Optional.of(party.get(0));
        }
        return Optional.empty();
    }
}
