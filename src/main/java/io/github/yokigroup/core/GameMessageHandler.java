package io.github.yokigroup.core;

import io.github.yokigroup.core.exception.GameInitFailException;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.submodule.GameEndSubmodule;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.event.submodule.GameStateSubmodule;
import io.github.yokigroup.event.submodule.InputSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.event.submodule.SubmoduleMap;
import io.github.yokigroup.event.submodule.SubmoduleMapImpl;
import io.github.yokigroup.event.MessageHandler;

import io.github.yokigroup.event.Updatable;
import io.github.yokigroup.event.submodule.abs.Submodule;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.view.render.observer.NOPModelObserver;

import java.lang.reflect.InvocationTargetException;

import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Game loop. Responsible for receiving events and updating entities
 */
public class GameMessageHandler implements MessageHandler {
    private SubmoduleMap subModules;


    /**
     * @return Set of submodules used by the {@link GameMessageHandler}
     * @see SubmoduleMap
     */
    private Set<Class<? extends Submodule>> createSubmoduleSet() {
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
     * creates a {@link SubmoduleMap} with the given instantiated submodules.
     * @param submoduleTypes set of submodules to initialize
     * @param modelObs model observer to instantiate the submodules with
     * @throws GameInitFailException if the initialization did not succeed
     */
    protected final void instantiateSubmodules(final ModelObserver modelObs,
                                                       final Set<Class<? extends Submodule>> submoduleTypes) {
        Objects.requireNonNull(modelObs);
        Objects.requireNonNull(submoduleTypes);
        final SubmoduleMap retMap = new SubmoduleMapImpl();
        submoduleTypes.forEach(s -> {
            try {
                retMap.register(s.getConstructor(MessageHandler.class, ModelObserver.class)
                        .newInstance(this, modelObs));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException
                     | IllegalAccessException e) {
                throw new GameInitFailException(e);
            }
        });
        subModules = retMap;
    }

    /**
     * Constructor for the game message handler.
     * @param modelObs the model observer.
     * @see ModelObserver
     */
    public GameMessageHandler(final ModelObserver modelObs) {
        instantiateSubmodules(modelObs, createSubmoduleSet());
    }

    /**
     * Empty constructor for the game message handler.
     * Initializes the game logic with a {@link NOPModelObserver} so as not to communicate with a view.
     */
    public GameMessageHandler() {
        this(new NOPModelObserver());
    }

    /**
     *
     * @param subModuleTypes type of the submodule called in play ...
     * @param handler handler function to determine what to do with the submodule ...
     * @param <T> Parameter which extends Submodule
     */
    @Override
    public final <T extends Submodule> void handle(final Class<T> subModuleTypes, final Consumer<T> handler) {
        this.handle(subModuleTypes, a -> {
            handler.accept(a);
            return null;
        });
    }

    @Override
    public final <T extends Submodule> void handle(final Set<Class<T>> subModuleTypes, final Consumer<T> handler) {
        subModuleTypes.forEach(smt -> handle(smt, handler));
    }

    @Override
    public final <T extends Submodule, E> E handle(final Class<T> subModuleType, final Function<T, E> handler) {
        final Optional<T> submodule = subModules.get(subModuleType);
        return handler.apply(submodule.orElseThrow(
                () -> new IllegalArgumentException(this.getClass() + " does not contain submodule " + subModuleType))
        );
    }

    @Override
    public final void updateSubmodules() {
        subModules.subModuleSet().forEach(Updatable::update);
    }
}
