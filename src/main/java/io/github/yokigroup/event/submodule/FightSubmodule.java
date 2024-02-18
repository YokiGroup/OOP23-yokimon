package io.github.yokigroup.event.submodule;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.FightSubmoduleAbs;
import io.github.yokigroup.view.observer.ModelObserver;

import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of {@link FightSubmoduleAbs}.
 * @author Giovanni Paone
 */
public final class FightSubmodule extends FightSubmoduleAbs {
    private Fight lastAnnouncedFight = null;
    private Publisher<Fight> fightPub = new PublisherImpl<>();

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public FightSubmodule(final MessageHandler handler, ModelObserver modelObs) {
        super(handler, modelObs);
        Objects.requireNonNull(modelObs);
        modelObs.addFightPublisher(fightPub);
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
