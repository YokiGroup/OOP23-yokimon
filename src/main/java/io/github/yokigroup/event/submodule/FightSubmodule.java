package io.github.yokigroup.event.submodule;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.battle.fight.Fight;

import java.util.List;
import java.util.Optional;

/**
 * Handles queuing fights and starting them when the process.
 * @author Giovanni Paone
 */
public class FightSubmodule extends Submodule {
    private Optional<Fight> lastAnnouncedFight = Optional.empty();

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public FightSubmodule(final MessageHandler handler) {
        super(handler);
    }

    /**
     * Generates fight to be processed as next encounter.
     */
    public void addEncounter() {
        // FIXME implement
        //lastAnnouncedFight = Optional.ofNullable(f);
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
            // TODO implement
        }
    }
}
