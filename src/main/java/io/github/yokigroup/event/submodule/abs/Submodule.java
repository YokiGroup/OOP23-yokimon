package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.observer.ModelObserver;
import io.github.yokigroup.event.Updateable;

/**
 * Submodule interface.
 * Every Submodule has the responsibility of handling and receiving events related to their function.
 * @see MessageHandler EventHandler
 * @author Giovanni Paone
 */
public abstract class Submodule extends Updateable {
    private final MessageHandler handler;

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    protected Submodule(final MessageHandler handler, ModelObserver ignoredModelObs) {
        this.handler = handler;
    }

    /**
     * Message bus used by submodules to communicate to each other.
     * @return handler reference held by submodule
     */
    protected final MessageHandler handler() {
        return handler;
    }
}
