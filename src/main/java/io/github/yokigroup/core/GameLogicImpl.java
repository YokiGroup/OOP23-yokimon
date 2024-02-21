package io.github.yokigroup.core;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.render.DrawCallable;
import io.github.yokigroup.view.render.observer.ModelObserver;

/**
 * Instances and keeps the game model updated.
 */
public final class GameLogicImpl extends Thread implements GameLogic {
    private final MessageHandler handler;
    private final DrawCallable renderer;
    private boolean running = true;

    public GameLogicImpl(final ModelObserver view, final DrawCallable renderer) {
        super();
        this.handler = new GameMessageHandler(view);
        this.renderer = renderer;
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
            handler.updateSubmodules();
            renderer.safeDraw();
        }
    }

    @Override
    public void stopGame() {
        running = false;
    }
}
