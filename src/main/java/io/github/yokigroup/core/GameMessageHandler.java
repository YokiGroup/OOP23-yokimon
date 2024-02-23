package io.github.yokigroup.core;

import io.github.yokigroup.battle.fight.Fight;
import io.github.yokigroup.view.render.drawable.SpriteData;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.observer.Publisher;

import io.github.yokigroup.event.Updateable;
import io.github.yokigroup.event.submodule.*;
import io.github.yokigroup.event.submodule.abs.Submodule;
import io.github.yokigroup.view.render.RenderState;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.view.notification.Notification;

import java.lang.reflect.InvocationTargetException;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Game loop. Responsible for receiving events and updating entities
 */
public class GameMessageHandler implements MessageHandler {
    private final SubmoduleMap subModules;

    /**
     * @return set of submodules to be instanced by the MessageHandler
     */
    protected Set<Class<? extends Submodule>> getSubmoduleTypes() {
        return Set.of(
                PartySubmodule.class,
                PlayerCharacterSubmodule.class,
                FightSubmodule.class,
                GameMapSubmodule.class,
                InputSubmodule.class,
                GameEndSubmodule.class,
                GameStateSubmodule.class
        );
    }

    /**
     * Initializes game logic submodules.
     * @return Initialized SubModuleMap
     * @param modelObs modelObs
     * @see SubmoduleMap
     */
    private SubmoduleMap initSubmodules(final ModelObserver modelObs) {
        final SubmoduleMap retMap = new SubmoduleMapImpl();
        final var submoduleTypes = getSubmoduleTypes();

        submoduleTypes.forEach(s -> {
            try {
                retMap.register(s.getConstructor(MessageHandler.class, ModelObserver.class).newInstance(this, modelObs));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException
                     | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return retMap;
    }

    /**
     * Constructor for game message handler.
     * @param modelObs the model Observer.
     */
    public GameMessageHandler(final ModelObserver modelObs) {
        subModules = initSubmodules(modelObs);
    }

    /**
     * Constructor for game message handler.
     */
    public GameMessageHandler() {
        // FIXME TEMPORARY HACK
        this(new ModelObserver() {
            @Override
            public void addSpritePublisher(final RenderState state, final Publisher<SpriteData> spriteObs) {
            }

            @Override
            public void addSpritePublishers(final RenderState state, final Publisher<Set<SpriteData>> spriteObs) {
            }

            @Override
            public void addFightPublisher(final Publisher<Fight> fightObs) {
            }

            @Override
            public void addNotificationPublisher(final Publisher<Notification> notificationPub) {
            }

            @Override
            public void addStateChangePublisher(final Publisher<RenderState> renderStatePublisher) {
            }
        });
    }

    /**
     *
     * @param subModuleTypes type of the submodule called in play ...
     * @param handler handler function to determine what to do with the submodule ...
     * @param <T>
     */
    @Override
    public final <T extends Submodule> void handle(final Class<T> subModuleTypes, final Consumer<T> handler) {
        this.handle(subModuleTypes, a -> {
            handler.accept(a);
            return null;
        });
    }

    @Override
    public <T extends Submodule> void handle(final Set<Class<T>> subModuleTypes, final Consumer<T> handler) {
        subModuleTypes.forEach(smt -> handle(smt, handler));
    }

    @Override
    public final <T extends Submodule, E> E handle(final Class<T> subModuleType, final Function<T, E> handler) {
        final Optional<T> submodule = subModules.get(subModuleType);
        if (submodule.isEmpty()) {
            throw new IllegalArgumentException(this.getClass() + " does not contain submodule " + subModuleType);
        }
        return handler.apply(submodule.get());
    }

    @Override
    public final void updateSubmodules() {
        subModules.subModuleSet().forEach(Updateable::update);
    }
}
