package io.github.yokigroup.event.submodule.abs;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.event.MessageHandler;

import java.util.Optional;

/**
 * Handles queuing fights and starting them when the process.
 * @author Giovanni Paone
 */
public abstract class FightSubmoduleAbs extends Submodule {
    public FightSubmoduleAbs(MessageHandler handler) {
        super(handler);
    }

    /**
     * Generates fight to be processed as next encounter.
     */
    public abstract void addEncounter();

    /**
     * Get last Fight added and not yet processed by the game logic.
     *
     * @return last Fight as detailed above, if any
     */
    public abstract Optional<Fight> getLastAnnouncedFight();

    @Override
    public final void update() {
        if (getLastAnnouncedFight().isPresent()) {
            // TODO implement
        }
    }
}
