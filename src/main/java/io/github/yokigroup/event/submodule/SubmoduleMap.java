package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.submodule.abs.Submodule;

import java.util.Optional;
import java.util.Set;

/**
 * a SubModuleMap is an object containing zero or more (different) submodules, queryable with the provided functions.
 * @author Giovanni Paone
 */
public interface SubmoduleMap {
    /**
     * Put a submodule in the map.
     * @param s submodule to add
     * @return true if a submodule of the same type has not been added previously,
     * false if the added submodule overwrote another one
     */
    boolean register(Submodule s);

    /**
     * Register all the submodules contained in the set.
     * @param sSet set of submodules
     * @param <T> type of submodule to register
     */
    <T extends Submodule> void registerAll(Set<T> sSet);

    /**
     * Get submodule of given class.
     * @param type type of submodule to receive
     * @param <T> type of submodule to get
     * @return optional of submodule, empty if it is not contained in the map
     */
    <T extends Submodule> Optional<T> get(Class<T> type);

    /**
     * @return set of Submodules registered to this map
     */
    Set<Submodule> subModuleSet();
}
