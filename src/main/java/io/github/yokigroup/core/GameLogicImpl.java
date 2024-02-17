package io.github.yokigroup.core;

import io.github.yokigroup.event.MessageHandler;

/**
 * Instances and keeps the game model updated.
 */
public final class GameLogicImpl implements GameLogic {
    private final MessageHandler handler = new GameMessageHandler();
    private boolean running = true;

    @Override
    public void start() {

    }

    private void loop() {
        while (running) {
            handler.updateSubmodules();
        }
    }
}
