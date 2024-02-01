package battle;

import java.util.Optional;
import java.util.List;

public abstract class NextYokimon {
    abstract Optional<Yokimon> getNext(List<Yokimon> party);
}
