package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.event.submodule.abs.FightSubmoduleAbs;

import java.util.Optional;

/**
 * Handles queuing fights and starting them when the process.
 * @author Giovanni Paone
 */
public final class FightSubmodule extends FightSubmoduleAbs {
    private Fight lastAnnouncedFight = null;

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public FightSubmodule(final MessageHandler handler) {
        super(handler);
    }



    @Override
    public void addEncounter() {
        // FIXME implement
        //lastAnnouncedFight = Optional.ofNullable(f);
    }

    @Override
    public Optional<Fight> getLastAnnouncedFight() {
        return Optional.ofNullable(lastAnnouncedFight);
    }

}
