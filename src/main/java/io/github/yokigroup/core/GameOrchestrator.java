package io.github.yokigroup.core;

import io.github.yokigroup.event.EventHandler;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerPositionSubmodule;
import io.github.yokigroup.event.submodule.SubModuleMap;
import io.github.yokigroup.event.submodule.SubModuleMapImpl;
import io.github.yokigroup.event.submodule.Submodule;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.tile.TileMap;

import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

/**
 * Game loop. Responsible for receiving events and updating entities
 */
public class GameOrchestrator implements EventHandler {
    private final SubModuleMap subModules;
    private final TileMap gameMap;
    private final Entity playerCharacter;

    /**
     * Initializes game logic submodules.
     * @return Initialized SubModuleMap
     * @see SubModuleMap
     */
    private SubModuleMap initSubmodules() {
        SubModuleMap retMap = new SubModuleMapImpl();

        // submodules this class uses
        PartySubmodule partySub = new PartySubmodule();
        PlayerPositionSubmodule playerPositionSub = new PlayerPositionSubmodule(playerCharacter, gameMap);
        FightSubmodule fightSub = new FightSubmodule(partySub);

        retMap.registerAll(Set.of(partySub, playerPositionSub, fightSub));

        return retMap;
    }

    /**
     * Initializes a GameOrchestrator with a new TileMap and PlayerCharacter, along with the required submodules.
     */
    public GameOrchestrator() {
        playerCharacter = new Entity() { }; // TODO replace with Entity implementation
        gameMap = new TileMap() { }; // TODO replace with TileMap implementation
        subModules = initSubmodules();
    }

    @Override
    public final <T extends Submodule> void handle(final Class<T> subModuleType, final Consumer<T> handler) {
        Optional<T> submodule = subModules.get(subModuleType);
        if (submodule.isEmpty()) {
            throw new IllegalArgumentException(this.getClass() + " does not contain submodule " + subModuleType);
        }
        handler.accept(submodule.get());
    }
}
