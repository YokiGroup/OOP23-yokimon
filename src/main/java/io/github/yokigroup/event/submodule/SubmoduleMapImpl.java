package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.submodule.abs.Submodule;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Optional;
import java.util.HashSet;


/**
 * Map of submodules. Used to collect all submodules used by a game component into one place.
 * @author Giovanni Paone
 */
public final class SubmoduleMapImpl implements SubmoduleMap {
    private final Map<Class<? extends Submodule>, Submodule> submodules = new HashMap<>();

    @Override
    public boolean register(final Submodule s) {
        return submodules.put(s.getClass(), s) == null;
    }

    @Override
    public <T extends Submodule> void registerAll(final Set<T> sSet) {
        Objects.requireNonNull(sSet);
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
