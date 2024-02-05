package io.github.yokigroup.event.submodule;

import java.util.Optional;
import java.util.Set;

/**
 * a SubModuleMap is an object containing zero or more (different) submodules, queryable with the provided functions
 */
public interface SubModuleMap {
    /**
     * Put a submodule in the map
     * @param s submodule to add
     * @return true if the submodule has not been added previously, false if the added submodule overwrote another one
     */
    <T extends Submodule> boolean register(T s);

    /**
     *
     */
    <T extends Submodule> void registerAll(Set<T> sSet);

    /**
     * Get submodule of given class
     * @param type Type of submodule to receive
     * @return Optional of submodule, empty if it is not contained in the map
     */
    <T extends Submodule> Optional<T> get(Class<T> type);

    /**
     * @return Set of Submodules registered to this map
     */
    <T extends Submodule> Set<T> subModuleSet();
}
