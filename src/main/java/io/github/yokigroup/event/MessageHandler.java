package io.github.yokigroup.event;

import io.github.yokigroup.event.submodule.abs.Submodule;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Responsible for the communication between entities and the submodules they interact with.
 * @author Giovanni Paone
 */
public interface MessageHandler {
    /**
     * handles an operation with a submodule, usually called by entities.
     * @param subModuleType type of the submodule called in play
     * @param handler handler function to determine what to do with the submodule
     * @param <T> Submodule to handle
     */
    <T extends Submodule> void handle(Class<T> subModuleType, Consumer<T> handler);

    /**
     * handles an operation with multiple submodule.
     * @param subModuleTypes types of the submodule called in play
     * @param handler handler function to determine what to do with the submodule
     * @param <T> Submodule to handle
     */
    <T extends Submodule> void handle(Set<Class<T>> subModuleTypes, Consumer<T> handler);

    /**
     * handles an operation with a submodule, usually called by entities.
     * Returns the return value of the handler function.
     * @param subModuleType type of the submodule called in play
     * @param handler handler function to determine what to do with the submodule
     * @param <T> Submodule to handle
     * @param <E> value to return to caller
     * @return return value of handler function
     */
    <T extends Submodule, E> E handle(Class<T> subModuleType, Function<T, E> handler);

    /**
     * Updates every submodule, to be called in a game loop.
     */
    void updateSubmodules();
}
