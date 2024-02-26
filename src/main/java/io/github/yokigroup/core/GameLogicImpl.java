package io.github.yokigroup.core;

import io.github.yokigroup.controller.Controller;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameEndSubmodule;
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
    private final Controller controller;

    /**
     * Constructor for GameLogicImpl.
     *
     * @param view ModelObserver
     * @param renderer DrawCallable
     * @param stopWindowFun Runnable
     */
    public GameLogicImpl(final ModelObserver view, final DrawCallable renderer, final Runnable stopWindowFun) {
        super();
        this.handler = new GameMessageHandler(view);
        this.renderer = renderer;
        this.stopWindowFun = stopWindowFun;
        this.controller = new Controller(this.handler);
    }

    /**
     * The message handler.
     *
     * @return the message handler.
     */
    public MessageHandler getMessageHandler() {
        return handler;
    }

    /**
     * @return the controller associated with this game logic
     */
    public Controller getController() {
        return controller;
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
