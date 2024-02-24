package io.github.yokigroup.core;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameEndSubmodule;
import io.github.yokigroup.event.submodule.abs.GameEndSubmoduleAbs;
import io.github.yokigroup.view.render.painter.DrawCallable;
import io.github.yokigroup.view.render.observer.ModelObserver;

/**
 * Instances and keeps the game model updated.
 */
public final class GameLogicImpl extends Thread {
    private static final int SLEEP_TIME = 5;
    private final MessageHandler handler;
    private final DrawCallable renderer;
    private final Runnable stopWindowFun;

    /**
     * Constructor for GameLogicImpl.
     * @param view
     * @param renderer
     * @param stopWindowFun
     */
    public GameLogicImpl(final ModelObserver view, final DrawCallable renderer, Runnable stopWindowFun) {
        super();
        this.handler = new GameMessageHandler(view);
        this.renderer = renderer;
        this.stopWindowFun = stopWindowFun;
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
        this.stopWindowFun.run();
    }
}
