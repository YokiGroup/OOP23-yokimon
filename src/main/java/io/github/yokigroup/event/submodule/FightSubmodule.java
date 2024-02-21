package io.github.yokigroup.event.submodule;

import io.github.yokigroup.battle.Yokimon;
import io.github.yokigroup.battle.fight.FightImpl;
import io.github.yokigroup.core.state.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.event.observer.Publisher;
import io.github.yokigroup.event.observer.PublisherImpl;
import io.github.yokigroup.event.submodule.abs.FightSubmoduleAbs;
import io.github.yokigroup.file.loader.YokimonLoader;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.world.GameMap;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of {@link FightSubmoduleAbs}.
 *
 * @author Giovanni Paone
 */
public final class FightSubmodule extends FightSubmoduleAbs {
    private final Fight lastAnnouncedFight = null;
    private final Publisher<Fight> fightPub = new PublisherImpl<>();
    private final Publisher<SpriteData> backgroundPub = new PublisherImpl<>();
    private final SpriteData battleBackground;

    /**
     * @param handler MessageHandler to call in order to query other submodules.
     */
    public FightSubmodule(final MessageHandler handler, ModelObserver modelObs) {
        super(handler, modelObs);
        Objects.requireNonNull(modelObs);
        modelObs.addFightPublisher(fightPub);
        modelObs.addSpritePublisher(RenderState.FIGHT, backgroundPub);
        final Vector2 mapDim = Vector2Impl.castPair(GameMap.TILE_DIMENSIONS);
        battleBackground = new SpriteData(
                "io/github/yokigroup/view/textures/tiles/battle-forest.png",
                mapDim.scale(0.5),
                mapDim,
                -100
        );
        backgroundPub.notifyObservers(battleBackground);
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
