package io.github.yokigroup.controller;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.EObserver;
import io.github.yokigroup.event.submodule.InputSubmodule;
import io.github.yokigroup.util.Pair;

import java.util.function.Consumer;

/**
 * Controller interface used to be interfaced with the model.
 */
public class Controller implements EObserver<Pair<Controller.KeyEventType, String>> {
    /**
     * type of key event received.
     */
    public enum KeyEventType {
        /**
         * the key has been pressed.
         */
        PRESS,
        /**
         * the key has been released.
         */
        RELEASE
    }
    private final Consumer<String> keyPressHandler;
    private final Consumer<String> keyReleaseHandler;

    /**
     * Constructor for Controller.
     * @param handler {@link MessageHandler} to issue key event requests to
     */
    public Controller(final MessageHandler handler) {
        this.keyPressHandler = str -> {
            handler.handle(InputSubmodule.class, (Consumer<InputSubmodule>) s -> s.registerKeyPress(str));
        };
        this.keyReleaseHandler = str -> {
            handler.handle(InputSubmodule.class, (Consumer<InputSubmodule>) s -> s.registerKeyRelease(str));
        };
    }

    @Override
    public void update(final Pair<KeyEventType, String> lastArg, final Pair<KeyEventType, String> arg) {
        switch (arg.x()) {
            case PRESS -> keyPressHandler.accept(arg.y());
            case RELEASE -> keyReleaseHandler.accept(arg.y());
        }
    }
}
