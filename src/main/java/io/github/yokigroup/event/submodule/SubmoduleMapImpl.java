package io.github.yokigroup.event.submodule;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Map of submodules. Used to collect all submodules used by a game component into one place.
 */
public final class SubmoduleMapImpl implements SubmoduleMap {
    private final Map<Class<? extends Submodule>, Submodule> submodules = new HashMap<>();

    @Override
    public <T extends Submodule> boolean register(final T s) {
        return submodules.put(s.getClass(), s) == null;
    }

    @Override
    public <T extends Submodule> void registerAll(final Set<T> sSet) {
        sSet.forEach(this::register);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Submodule> Optional<T> get(final Class<T> type) {
        Submodule tempSub = submodules.get(type);
        T retSub = null;
        if (type.isInstance(tempSub)) {
            retSub = (T) tempSub;
        }
        return Optional.ofNullable(retSub);
    }

    @Override
    public Set<Submodule> subModuleSet() {
        return new HashSet<>(submodules.values());
    }
}
