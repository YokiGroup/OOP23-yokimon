package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.InputSubmoduleAbs;
import io.github.yokigroup.view.observer.ModelObserver;

public class InputSubmodule extends InputSubmoduleAbs {
    /**
     * @param handler         MessageHandler to call in order to query other submodules.
     * @param ignoredModelObs
     */
    public InputSubmodule(MessageHandler handler, ModelObserver ignoredModelObs) {
        super(handler, ignoredModelObs);
    }

    @Override
    public void handleInput(String keyText) {
        System.out.println(keyText);
    }

    @Override
    public void update() {

    }
}
