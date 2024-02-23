package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.render.observer.ModelObserver;

public abstract class GameEndSubmoduleAbs extends Submodule {

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     * @param modelObs {@link ModelObserver} used to instantiate the submodule
     */
    protected GameEndSubmoduleAbs(final MessageHandler handler, final ModelObserver modelObs) {
        super(handler, modelObs);
    }

    /**
     * Trigger a game over that happens when the player dies in battle.
     */
    public abstract void triggerDeathGameGO();

    /**
     * Triggers a game over that happens when the player fights an enemy with no yokimons.
     */
    public abstract void triggerBattleWithNoYokimonsGO();

    /**
     * Triggers a victory.
     */
    public abstract void triggerVictory();

    @Override
    protected void updateCode(final double delta) {
    }
}
