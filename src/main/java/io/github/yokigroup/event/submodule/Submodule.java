package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;

/**
 * Submodule interface.
 * Every Submodule has the responsibility of handling and receiving events related to their function.
 * @see MessageHandler EventHandler
 */
public interface Submodule {
    /**
     * to be called by the logic that integrates this submodule to let it process its inputs.
     */
    void process();
}
