package battle;

import java.util.Optional;

public interface NextYokimon {
    Optional<Yokimon> getNext(List<Yokimon> party);
}
