package io.github.yokigroup.event.submodule;

/**
 * a SubModuleMap is an object containing zero or more (different) submodules, queryable with the provided functions
 */
public interface SubModuleMap {
    <T extends Submodule> void registerSubmodule(T s);
    <T extends Submodule> T getSubmodule(Class<T> type);
}
