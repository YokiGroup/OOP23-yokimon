package io.github.yokigroup.core;

import io.github.yokigroup.event.EventHandler;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerPositionSubmodule;
import io.github.yokigroup.event.submodule.Submodule;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.tile.TileMap;

import java.util.List;
import java.util.function.Consumer;

public class GameOrchestrator implements EventHandler {
    private final List<Submodule> subModules;
    private final TileMap gameMap;
    private final Entity playerCharacter;

    public GameOrchestrator() {
        playerCharacter = new Entity() {}; // TODO replace with Entity implementation
        gameMap = new TileMap() {}; // TODO replace with TileMap implementation

        // initializing submodules
        PartySubmodule partySub = new PartySubmodule();
        PlayerPositionSubmodule playerPositionSub = new PlayerPositionSubmodule(playerCharacter, gameMap);
        FightSubmodule fightSub = new FightSubmodule(partySub);
        subModules = List.of(partySub, playerPositionSub, fightSub);
    }

    @Override
    public <T extends Submodule> void handle(Class<T> subModuleType, Consumer<T> handler) {

    }
}
