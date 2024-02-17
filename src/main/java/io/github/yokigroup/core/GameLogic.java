package io.github.yokigroup.core;

import io.github.yokigroup.view.drawqueue.ViewInit;

/**
 * Interface containing functions inherent to starting and observing the state of the game.
 */
public interface GameLogic {
    /**
     * Starts the game logic.
     */
    void start(ViewInit view);
}
