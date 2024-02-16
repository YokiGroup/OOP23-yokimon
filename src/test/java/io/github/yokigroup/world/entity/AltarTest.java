package io.github.yokigroup.world.entity;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;

import io.github.yokigroup.event.submodule.GameMapSubmoduleImpl;
import io.github.yokigroup.file.loader.YokimonLoader;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.people.Player;
import io.github.yokigroup.world.tile.Tile;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AltarTest {
    private static YokimonLoader loader;
    private static MessageHandler messageHandler;
    private static GameMessageHandler gameMessageHandler;
    @BeforeEach
    void setUp() {
        loader = new YokimonLoader();
        MessageHandler messageHandler = new GameMessageHandler();
        GameMessageHandler gameMessageHandler = new GameMessageHandler();
    }
    @Test
    void getState() {

        Position playerPos = new PositionImpl(new Vector2Impl(0, 0));
        Position altarPos = new PositionImpl(new Vector2Impl(30, 0));

        Tile tile = new TileBuilderImpl(0)
                .addEntity(TileBuilder.EntityType.ALTAR, altarPos).build(messageHandler);
        /*
        messageHandler.handle(PlayerCharacterSubmodule.class, p -> {
            p.getPosition();
        });
        */

        for (Entity entity : tile.getEntities()){
            assertEquals(entity.getPos(), altarPos);
        }

        //altar.update();
        //assertEquals(Altar.AltarState.USED, altar.getState());

    }

    @Test
    void update() {
    }
}