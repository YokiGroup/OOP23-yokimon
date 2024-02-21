package io.github.yokigroup.event.submodule;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.fight.FightImpl;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.FightSubmoduleAbs;
import io.github.yokigroup.file.loader.YokimonLoader;
import io.github.yokigroup.view.render.observer.ModelObserver;

import java.util.List;
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
        YokimonLoader loader = new YokimonLoader();
        Yokimon a = loader.load(1);
        handler().handle(PartySubmodule.class, s -> {
            fightPub.notifyObservers(new FightImpl(s.listYokimons(), List.of(a)));
        });
    }

    @Override
    public Optional<Fight> getLastAnnouncedFight() {
        return Optional.ofNullable(lastAnnouncedFight);
    }

}
