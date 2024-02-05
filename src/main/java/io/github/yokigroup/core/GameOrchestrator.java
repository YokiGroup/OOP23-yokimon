package io.github.yokigroup.core;

import io.github.yokigroup.event.EventHandler;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerPositionSubmodule;
import io.github.yokigroup.event.submodule.Submodule;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.tile.TileMap;

import java.nio.charset.IllegalCharsetNameException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

public class GameOrchestrator implements EventHandler {
    private final Map<Class<? extends Submodule>, Submodule> subModules;
    private final TileMap gameMap;
    private final Entity playerCharacter;

    /**
     * initializes game logic submodules
     */
    private Map<Class<? extends Submodule>, Submodule> initSubmodules(){
        Map<Class<? extends Submodule>, Submodule> retMap = new HashMap<>();
        Set<? extends Submodule> submodules;

        // submodules this class uses
        PartySubmodule partySub = new PartySubmodule();
        PlayerPositionSubmodule playerPositionSub = new PlayerPositionSubmodule(playerCharacter, gameMap);
        FightSubmodule fightSub = new FightSubmodule(partySub);

        submodules = Set.of(partySub, playerPositionSub, fightSub);
        for(var submodule: submodules){
            retMap.put(submodule.getClass(), submodule);
        }

        return retMap;
    }

    public GameOrchestrator() {
        playerCharacter = new Entity() {}; // TODO replace with Entity implementation
        gameMap = new TileMap() {}; // TODO replace with TileMap implementation
        subModules = initSubmodules();
    }

    @Override
    public <T extends Submodule> void handle(Class<T> subModuleType, Consumer<T> handler) {

    }
}
