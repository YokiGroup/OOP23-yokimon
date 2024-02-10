package io.github.yokigroup.event.submodule;

import io.github.yokigroup.battle.fight.Fight;

import java.util.Optional;

/**
 * Handles queuing fights and starting them when the process.
 * @author Giovanni Paone
 */
public class FightSubmodule implements Submodule {
    private Optional<Fight> lastAnnouncedFight = Optional.empty();
    private final PartySubmodule party; // keeping a reference to the party submodule to get a list of yokimons whenever necessary

    /**
     * Instantiate a FightSubmodule.
     * @param party party submodule reference to consult when fights start
     */
    public FightSubmodule(final PartySubmodule party) {
        this.party = party;
    }

    /**
     * Adds Fight {@param f} to be processed as next encounter.
     * @param f
     */
    public void addEncounter(final Fight f) {
        // FIXME perhaps Fight could adopt the Builder design pattern?
        lastAnnouncedFight = Optional.ofNullable(f);
    }

    /**
     * Get last Fight added and not yet processed by the game logic.
     * @return last Fight as detailed above, if any
     */
    public Optional<Fight> getLastAnnouncedFight() {
        return lastAnnouncedFight;
    }

    @Override
    public final void process() {
        if (lastAnnouncedFight.isPresent()) {
            // TODO start fight
        }
    }
}
