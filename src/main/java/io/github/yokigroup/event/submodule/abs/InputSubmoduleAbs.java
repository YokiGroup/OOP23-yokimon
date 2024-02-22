package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.render.observer.ModelObserver;

/**
 * Input Submodules.
 */
public abstract class InputSubmoduleAbs extends Submodule {
    /**
     * @param handler MessageHandler to call in order to query other submodules.
     * @param ignoredModelObs ...
     */
    public InputSubmoduleAbs(final MessageHandler handler, final ModelObserver ignoredModelObs) {
        super(handler, ignoredModelObs);
    }

    /**
     * ...
     * @param keyText ...
     */
    public abstract void registerKeyPress(String keyText);

    /**
     * ...
     * @param keyText ...
     */
    public abstract void registerKeyRelease(String keyText);
}
