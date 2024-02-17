package io.github.yokigroup.core;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.observer.ModelObserver;

/**
 * Instances and keeps the game model updated.
 */
public final class GameLogicImpl implements GameLogic {
    private final MessageHandler handler = new GameMessageHandler();
    private boolean running = true;

    @Override
    public void start(ModelObserver view) {

    }

    private void loop() {
        while (running) {
            handler.updateSubmodules();
        }
    }
}
