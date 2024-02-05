package io.github.yokigroup.event.submodule;

import java.util.Optional;

/**
 * a SubModuleMap is an object containing zero or more (different) submodules, queryable with the provided functions
 */
public interface SubModuleMap {
    /**
     * Put a submodule in the map
     * @param s submodule to add
     * @return true if the submodule has not been added previously, false if the added submodule overwrote another one
     */
    <T extends Submodule> boolean registerSubmodule(T s);

    /**
     * Get submodule of given class
     * @param type Type of submodule to receive
     * @return Optional of submodule, empty if it is not contained in the map
     */
    <T extends Submodule> Optional<T> getSubmodule(Class<T> type);
}
