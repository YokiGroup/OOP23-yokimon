package io.github.yokigroup.world.entity;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.*;

import io.github.yokigroup.file.loader.YokimonLoader;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.GameMapBuilder;
import io.github.yokigroup.world.GameMapBuilderImpl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.entity.people.Player;
import io.github.yokigroup.world.tile.Tile;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AltarTest {
    private static YokimonLoader loader;
    private static MessageHandler messageHandler;
    private static GameMessageHandler gameMessageHandler;

    private static class TestMessageHandler extends GameMessageHandler {
        private static class TestSubmodule extends GameMapSubmodule {
            Vector2 v = new Vector2Impl(50, 50);
            Position altarPos = new PositionImpl(v);
            Tile tile = new TileBuilderImpl(0).addEntity(TileBuilder.EntityType.ALTAR, altarPos)
                    .build(messageHandler);
            GameMap map = new GameMapBuilderImpl().build(messageHandler);

            public TestSubmodule(MessageHandler handler) {
                super(handler);
            }

            @Override
            public GameMap getGameMap() {
                return map;
            }

            @Override
            public Set<Hitbox> getHitboxesOnCurrentTile() {
                return map.getPlayerTile().getHitboxes();
            }

            @Override
            public Set<Entity> getEntitiesOnCurrentTile() {
                return map.getPlayerTile().getEntities();
            }
        }

        @Override
        protected Set<Class<? extends Submodule>> getSubmoduleTypes() {
            return Set.of(

            );
        }
    }

    @BeforeEach
    void setUp() {
        loader = new YokimonLoader();
        MessageHandler messageHandler = new GameMessageHandler();
        GameMessageHandler gameMessageHandler = new GameMessageHandler();
    }
    @Test
    void getState() {


        //altar.update();
        //assertEquals(Altar.AltarState.USED, altar.getState());

    }

    @Test
    void update() {
    }
}