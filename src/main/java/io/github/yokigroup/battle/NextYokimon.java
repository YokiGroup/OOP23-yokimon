package io.github.yokigroup.battle;

import java.util.List;
import java.util.Optional;

public abstract class NextYokimon {
    abstract Optional<Yokimon> getNext(List<Yokimon> party);
}
