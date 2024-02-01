package battle;

import java.util.Optional;

public abstract class NextYokimon {
    abstract Optional<Yokimon> getNext(List<Yokimon> party);
}
