package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.InputSubmoduleAbs;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.observer.ModelObserver;

import java.util.Locale;

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
        switch (keyText.toLowerCase(Locale.ROOT)) {
            case "w":
                handler().handle(PlayerCharacterSubmodule.class, s -> {
                    s.movePlayerBy(new Vector2Impl(0, -3));
                });
                break;
        }
    }

    @Override
    public void update() {

    }
}
