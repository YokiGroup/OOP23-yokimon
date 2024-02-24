package io.github.yokigroup.core;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameEndSubmodule;
import io.github.yokigroup.event.submodule.abs.GameEndSubmoduleAbs;
import io.github.yokigroup.view.render.painter.DrawCallable;
import io.github.yokigroup.view.render.observer.ModelObserver;

/**
 * Instances and keeps the game model updated.
 */
public final class GameLogicImpl extends Thread implements GameLogic {
    private static final int SLEEP_TIME = 5;
    private final MessageHandler handler;
    private final DrawCallable renderer;
    private boolean running = true;

    /**
     * Constructor for GameLogicImpl.
     * @param view
     * @param renderer
     */
    public GameLogicImpl(final ModelObserver view, final DrawCallable renderer) {
        super();
        this.handler = new GameMessageHandler(view);
        this.renderer = renderer;
    }

    /**
     * The message handler.
     * @return the message handler.
     */
    public MessageHandler getMessageHandler() {
        return handler;
    }

    @Override
    public void run() {
        gameLoop();
    }

    private void gameLoop() {
        while (!handler.handle(GameEndSubmodule.class, GameEndSubmodule::gameEnded)) {
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            handler.updateSubmodules();
            renderer.safeDraw();
        }
    }

    @Override
    public void stopGame() {
        running = false;
    }
}
