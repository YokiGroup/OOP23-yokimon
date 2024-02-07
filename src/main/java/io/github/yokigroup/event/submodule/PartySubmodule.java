package io.github.yokigroup.event.submodule;

import io.github.yokigroup.battle.Yokimon;

import java.util.ArrayList;
import java.util.List;

/**
 * Submodule containing a party's information (yokimon's group).
 */
public class PartySubmodule implements Submodule {
    private List<Yokimon> yokimonList = new ArrayList<>();

    private List<Yokimon> deepCopyOf(final List<Yokimon> list) {
        // TODO wait for a copy constructor of yokimon to be pushed upstream
        //return List.copyOf(list.stream().map(y -> new YokimonImpl(y)));
        return List.copyOf(list);
    }

    /**
     * Adds a yokimon to the party.
     * @param y yokimon to add
     */
    public void addYokimon(final Yokimon y) {
        yokimonList.add(y);
    }

    /**
     * Lists all the yokimons in the party.
     * @return defensive copy (watch out!) of yokimons in the party
     * @see {@code setParty}
     */
    public List<Yokimon> listYokimons() {
        return deepCopyOf(yokimonList);
    }

    /**
     * Sets (and overwrites) the current party.
     * Best used in tandem with {@code listYokimons} to update the party.
     * @param party party to set
     */
    public void setParty(final List<Yokimon> party) {
        yokimonList = deepCopyOf(party);
    }

    /**
     * Removes a yokimon from the party.
     * @param y yokimon to remove
     * @return true if the yokimon has been removed
     */
    public boolean removeYokimon(final Yokimon y) {
        return yokimonList.remove(y);
    }

    @Override
    public void process() {

    }
}
