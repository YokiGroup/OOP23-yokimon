package io.github.yokigroup.core;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.event.submodule.Submodule;
import io.github.yokigroup.event.submodule.SubmoduleMap;
import io.github.yokigroup.event.submodule.SubmoduleMapImpl;
import io.github.yokigroup.world.entity.Entity;

import java.lang.reflect.InvocationTargetException;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Game loop. Responsible for receiving events and updating entities
 */
public class GameMessageHandler implements MessageHandler {
    private final SubmoduleMap subModules;

    /**
     * Initializes game logic submodules.
     * @return Initialized SubModuleMap
     * @see SubmoduleMap
     */
    private SubmoduleMap initSubmodules() {
        SubmoduleMap retMap = new SubmoduleMapImpl();
        List<Class<? extends Submodule>> submoduleTypes = List.of(
                PartySubmodule.class,
                PlayerCharacterSubmodule.class,
                FightSubmodule.class,
                GameMapSubmodule.class
        );

        submoduleTypes.forEach(s -> {
            try {
                retMap.register(s.getConstructor(MessageHandler.class).newInstance(this));
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException
                     | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        return retMap;
    }

    /**
     * Initializes a GameOrchestrator with a new GameMap and PlayerCharacter, along with the required submodules.
     */
    public GameMessageHandler() {
        playerCharacter = null; // TODO replace with Entity implementation
        subModules = initSubmodules();
    }

    @Override
    public final <T extends Submodule> void handle(final Class<T> subModuleType, final Consumer<T> handler) {
        this.handle(subModuleType, a -> {
            handler.accept(a);
            return null;
        });
    }

    @Override
    public final <T extends Submodule, E> E handle(final Class<T> subModuleType, final Function<T, E> handler) {
        Optional<T> submodule = subModules.get(subModuleType);
        if (submodule.isEmpty()) {
            throw new IllegalArgumentException(this.getClass() + " does not contain submodule " + subModuleType);
        }
        return handler.apply(submodule.get());
    }
}
