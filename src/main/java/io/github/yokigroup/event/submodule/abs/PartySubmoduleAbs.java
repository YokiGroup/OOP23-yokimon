package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.MessageHandler;

import java.util.List;

/**
 * Abstract class of a submodule containing information about the player's party of yokimons.
 * @see Yokimon
 * @author Giovanni Paone
 */
public abstract class PartySubmoduleAbs extends Submodule {

    /**
     * @param handler to init the submodule with
     */
    public PartySubmoduleAbs(final MessageHandler handler) {
        super(handler);
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

    /**
     * Removes a yokimon from the party.
     *
     * @param y yokimon to remove
     * @return true if the yokimon has been removed
     */
    public abstract boolean removeYokimon(Yokimon y);

    @Override
    public void update() {

    }
}
