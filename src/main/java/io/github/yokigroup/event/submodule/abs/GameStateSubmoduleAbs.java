package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.render.observer.ModelObserver;

/**
 * Abstract class of a submodule containing information about the game's current state.
 *
 * @author Giovanni Paone
 */
public abstract class GameStateSubmoduleAbs extends Submodule {
    /**
     * enum describing the current game's state.
     */
    public enum GameState {
        /**
         * player is engaged in a fight.
         */
        FIGHT,
        /**
         * player is exploring the game world.
         */
        WORLD,
        /**
         * game is over.
         */
        GAMEOVER,
        /**
         * game is won.
         */
        VICTORY
    }

    /**
     * @param handler         MessageHandler to call in order to query other submodules.
     * @param ignoredModelObs {@link ModelObserver} used to instantiate the submodule
     */
    protected GameStateSubmoduleAbs(final MessageHandler handler, final ModelObserver ignoredModelObs) {
        super(handler, ignoredModelObs);
    }

    /**
     * sets the game state to {@param state}.
     *
     * @param state state to set the game's state to
     */
    public abstract void setGameState(GameState state);

    /**
     * @return the game's current state.
     */
    public abstract GameState getGameState();
}
