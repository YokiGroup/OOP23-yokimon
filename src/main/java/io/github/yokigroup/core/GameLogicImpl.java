package io.github.yokigroup.core;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.observer.ModelObserver;

/**
 * Instances and keeps the game model updated.
 */
public final class GameLogicImpl extends Thread implements GameLogic {
    private final MessageHandler handler;
    private boolean running = true;

    public GameLogicImpl(ModelObserver view) {
        super();
        handler = new GameMessageHandler(view);
    }

    public MessageHandler getMessageHandler() {
        return handler;
    }

    @Override
    public void run() {
        gameLoop();
    }

    private void gameLoop() {
        while (running) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            handler.updateSubmodules();
        }
    }

    @Override
    public void stopGame() {
        running = false;
    }
}
