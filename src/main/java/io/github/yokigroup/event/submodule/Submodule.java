package io.github.yokigroup.event.submodule;

/**
 * Submodule interface.
 * Every Submodule has the responsibility of handling and receiving events related to their function.
 * @see io.github.yokigroup.event.EventHandler EventHandler
 */
public interface Submodule {
    /**
     * to be called by the logic that integrates this submodule to let it process its inputs.
     */
    void process();
}
