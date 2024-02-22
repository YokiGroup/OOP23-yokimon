package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.event.submodule.abs.GameMapSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.Submodule;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.render.observer.ModelObserver;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.GameMapBuilderImpl;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {
    private static final Vector2 V_P = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2,
            (double) GameMap.TILE_DIMENSIONS.y() / 2);
    private TestMessageHandler handler;

    private static final class TestMessageHandler extends GameMessageHandler {
        public static class TestSubmodule extends GameMapSubmoduleAbs {

            private final TileBuilder tile = new TileBuilderImpl(0, "");
            private final GameMap map = new GameMapBuilderImpl().putTileAt(tile, new Pair<>(0, 0)).build(this.handler());
            //CheckStyle: OFF
            public TestSubmodule(final MessageHandler handler, final ModelObserver modelObs) {
                //CheckStyle: ON
                super(handler, modelObs);
            }

            @Override
            public int getPlayerDistanceFromHome() {
                return 1;
            }

            @Override
            public boolean movePlayerToTile(final Direction dir) {
                return map.movePlayerTileMapPosition(dir);
            }

            @Override
            public Set<Hitbox> getHitboxesOnCurrentTile() {
                return map.getPlayerTile().getHitboxes();
            }

            @Override
            public Set<Entity> getEntitiesOnCurrentTile() {
                return map.getPlayerTile().getEntities();
            }

            @Override
            protected void updateEntities() {

            }

            @Override
            protected void updateTile() {

            }

        }

        @Override
        protected Set<Class<? extends Submodule>> getSubmoduleTypes() {
            return Objects.requireNonNull(Set.of(
                    PlayerCharacterSubmodule.class,
                    TestMessageHandler.TestSubmodule.class,
                    PartySubmodule.class,
                    GameMapSubmodule.class
            ));
        }
    }

    @BeforeEach
    void setUp() {
        handler = new TestMessageHandler();
    }

    @Test
    void move() {

        final int scaleTot = 5;
        final double scaleCompensate = 0.1;
        handler.handle(PlayerCharacterSubmodule.class, play -> {
            for (double i = 1; i < scaleTot; i++) {
                final double scalable = -50;
                play.movePlayerBy(new Vector2Impl(scalable, 0.00).scale(scaleCompensate));
                assertEquals(V_P.getX() + scalable * i, play.getPosition().getPosition().getX());
                assertEquals(V_P.getY(), play.getPosition().getPosition().getY());
            }
        });

    }
}