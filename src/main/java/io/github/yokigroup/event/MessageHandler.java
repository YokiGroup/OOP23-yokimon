package io.github.yokigroup.event;

import io.github.yokigroup.event.submodule.Submodule;

import java.util.function.Consumer;

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
}
