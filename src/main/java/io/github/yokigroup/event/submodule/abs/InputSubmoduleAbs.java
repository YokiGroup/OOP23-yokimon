package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.observer.ModelObserver;

public abstract class InputSubmoduleAbs extends Submodule {
    /**
     * @param handler         MessageHandler to call in order to query other submodules.
     * @param ignoredModelObs
     */
    public InputSubmoduleAbs(MessageHandler handler, ModelObserver ignoredModelObs) {
        super(handler, ignoredModelObs);
    }

    public abstract void registerKeyPress(String keyText);
    public abstract void registerKeyRelease(String keyText);
}
