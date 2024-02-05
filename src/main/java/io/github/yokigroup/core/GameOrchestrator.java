package io.github.yokigroup.core;

import io.github.yokigroup.event.EventHandler;
import io.github.yokigroup.event.submodule.*;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.tile.TileMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;

public class GameOrchestrator implements EventHandler {
    private final SubModuleMap subModules;
    private final TileMap gameMap;
    private final Entity playerCharacter;

    /**
     * initializes game logic submodules
     */
    private SubModuleMap initSubmodules(){
        SubModuleMap retMap = new SubModuleMapImpl();

        // submodules this class uses
        PartySubmodule partySub = new PartySubmodule();
        PlayerPositionSubmodule playerPositionSub = new PlayerPositionSubmodule(playerCharacter, gameMap);
        FightSubmodule fightSub = new FightSubmodule(partySub);

        retMap.addAll(Set.of(partySub, playerPositionSub, fightSub));

        return retMap;
    }

    public GameOrchestrator() {
        playerCharacter = new Entity() {}; // TODO replace with Entity implementation
        gameMap = new TileMap() {}; // TODO replace with TileMap implementation
        subModules = initSubmodules();
    }

    @Override
    public <T extends Submodule> void handle(Class<T> subModuleType, Consumer<T> handler) {
        Optional<T> submodule = subModules.get(subModuleType);
        if(submodule.isEmpty()){
            throw new IllegalArgumentException(this.getClass()+" does not contain submodule "+subModuleType);
        }
        handler.accept(submodule.get());
    }
}
