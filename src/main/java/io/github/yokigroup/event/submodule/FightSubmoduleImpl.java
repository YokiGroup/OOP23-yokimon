package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.event.submodule.abs.FightSubmodule;

import java.util.Optional;

/**
 * Handles queuing fights and starting them when the process.
 * @author Giovanni Paone
 */
public class FightSubmoduleImpl extends FightSubmodule {
    private Optional<Fight> lastAnnouncedFight = Optional.empty();

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public FightSubmoduleImpl(final MessageHandler handler) {
        super(handler);
    }



    @Override
    public void addEncounter() {
        // FIXME implement
        //lastAnnouncedFight = Optional.ofNullable(f);
    }

    @Override
    public Optional<Fight> getLastAnnouncedFight() {
        return lastAnnouncedFight;
    }

}
