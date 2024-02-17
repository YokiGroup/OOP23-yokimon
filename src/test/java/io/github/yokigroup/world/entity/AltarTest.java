package io.github.yokigroup.world.entity;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.abs.GameMapSubmoduleAbs;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.file.loader.YokimonLoader;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.people.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AltarTest {
    private static YokimonLoader loader;
    private static MessageHandler messageHandler;
    @BeforeEach
    void setUp() {
        loader = new YokimonLoader();
        MessageHandler messageHandler = new GameMessageHandler();
    }
    @Test
    void getState() {

        Position playerPos = new PositionImpl(new Vector2Impl(0, 0));
        Position altarPos = new PositionImpl(new Vector2Impl(2, 0));

        Altar altar = new Altar(altarPos, messageHandler);
        Entity player = new Player(playerPos, messageHandler);
        GameMap map = messageHandler.handle(GameMapSubmodule.class, GameMapSubmoduleAbs::getGameMap);
        map.getPlayerTile().getEntities().add(player);
        map.getPlayerTile().getEntities().add(altar);
        assertEquals(Altar.AltarState.POWERED, altar.getState());
        altar.update();
        assertEquals(Altar.AltarState.USED, altar.getState());

    }

    @Test
    void update() {
    }
}