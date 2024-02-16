package io.github.yokigroup.world.entity;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.*;

import io.github.yokigroup.file.loader.YokimonLoader;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.GameMap;
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
/*
    private static class TestMessageHandler extends GameMessageHandler {
        private static class TestFightSubmodule extends GameMapSubmodule {
            Position playerPos = new PositionImpl(new Vector2Impl(0, 0));
            Position altarPos = new PositionImpl(new Vector2Impl(30, 0));
            Tile tile = new TileBuilderImpl(0).addEntity(TileBuilder.EntityType.ALTAR, altarPos)
                    .build(messageHandler);
            GameMap mappa = new GameMap() {
                @Override
                public Tile getTileAt(Pair<Integer, Integer> position) {
                    return tile;
                }

                @Override
                public Pair<Integer, Integer> getPlayerTileMapPosition() {
                    return new Pair<>(0,0);
                }

                @Override
                public Tile getPlayerTile() {
                    return tile;
                }

                @Override
                public boolean movePlayerTileMapPosition(Direction direction) {
                    return false;
                }
            } ;

            public TestFightSubmodule(MessageHandler handler) {
                super(handler);
            }

            @Override
            public GameMap getGameMap() {
                return mappa;
            }

            @Override
            public Set<Hitbox> getHitboxesOnCurrentTile() {
                return new HashSet<>();
            }

            @Override
            public Set<Entity> getEntitiesOnCurrentTile() {
                return new HashSet<>();
            }
        }

        @Override
        protected List<Class<? extends Submodule>> getSubmoduleTypes() {
            return (List<Class<? extends Submodule>>) Set.of( GameMapSubmoduleImpl.class , PartySubmodule.class);
        }
    }
    */

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