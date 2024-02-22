package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.render.observer.ModelObserver;

import java.util.List;
import java.util.Optional;

/**
 * Abstract class of a submodule containing information about the player's upcoming fights.
 * @see Yokimon
 * @author Giovanni Paone
 */
public abstract class FightSubmoduleAbs extends Submodule {

    /**
     * Constructor for FightSubmoduleAbs.
     * @param handler to init the submodule with
     * @param modelObs the model observer.
     */
    public FightSubmoduleAbs(final MessageHandler handler, final ModelObserver modelObs) {
        super(handler, modelObs);
    }

    /**
     * Process next encounter.
     */
    public abstract void addEncounter(List<Yokimon> enemyParty);

    /**
     * Get last Fight added and not yet processed by the game logic.
     *
     * @return last Fight as detailed above, if any
     */
    public abstract Optional<Fight> getLastAnnouncedFight();

    /**
     *
     */
    public abstract void nextAttack();
    public abstract void prevAttack();

    @Override
    protected final void updateCode(final double delta) {
        if (getLastAnnouncedFight().isPresent()) {
            // TODO implement
        }
    }
}
