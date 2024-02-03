package io.github.yokigroup.battle;

import java.util.List;
import java.util.Optional;

public class dummyImplNextYokimon extends NextYokimon {

    @Override
     Optional<Yokimon> getNext(List<Yokimon> party) {
        if (!party.isEmpty()) {
            return Optional.of(party.get(0));
        }
        return Optional.empty();
    }
}
