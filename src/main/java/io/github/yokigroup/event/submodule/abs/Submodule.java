package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.drawqueue.ViewInit;

/**
 * Submodule interface.
 * Every Submodule has the responsibility of handling and receiving events related to their function.
 * @see MessageHandler EventHandler
 * @author Giovanni Paone
 */
public abstract class Submodule {
    private final MessageHandler handler;

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public Submodule(final MessageHandler handler) {
        this.handler = handler;
    }

    public Submodule(final MessageHandler handler, final ViewInit view) {
        this(handler);
    }

    /**
     * Message bus used by submodules to communicate to each other.
     * @return handler reference held by submodule
     */
    protected final MessageHandler handler() {
        return handler;
    }

    /**
     * to be called by the logic that integrates this submodule to let it process its state.
     */
    public abstract void update();
}
