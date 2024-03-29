package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.battle.yokimon.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.view.render.observer.ModelObserver;

import java.util.List;

/**
 * Abstract class of a submodule containing information about the player's party of yokimons.
 * @see Yokimon
 * @author Giovanni Paone
 */
public abstract class PartySubmoduleAbs extends Submodule {

    /**
     * @param handler to init the submodule with
     * @param modelObs the model Observer.
     */
    public PartySubmoduleAbs(final MessageHandler handler, final ModelObserver modelObs) {
        super(handler, modelObs);
    }

    /**
     * Adds a yokimon to the party.
     * @param y yokimon to add
     */
    public abstract void addYokimon(Yokimon y);

    /**
     * Lists all the yokimons in the party.
     *
     * @return defensive copy (watch out!) of yokimons in the party
     * @see #setParty(List) setParty
     */
    public abstract List<Yokimon> listYokimons();

    /**
     * Sets (and overwrites) the current party.
     * Best used in tandem with {@code listYokimons} to update the party.
     *
     * @param party party to set
     */
    public abstract void setParty(List<Yokimon> party);

    @Override
    protected void updateCode(final double delta) {

    }
}
