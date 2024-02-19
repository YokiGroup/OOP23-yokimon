package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.FightSubmodule;
import io.github.yokigroup.event.submodule.GameMapSubmodule;
import io.github.yokigroup.event.submodule.PartySubmodule;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.event.submodule.abs.GameMapSubmoduleAbs;
import io.github.yokigroup.event.submodule.abs.Submodule;
import io.github.yokigroup.util.Pair;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.view.observer.ModelObserver;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.GameMapBuilderImpl;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import io.github.yokigroup.world.tile.TileBuilder;
import io.github.yokigroup.world.tile.TileBuilderImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnemyTest {
    private TestMessageHandler testMeg;
    private static final double X_TEST = 150;
    private static final double Y_TEST = 120;
    private static final double NUM_TEST = 20;
    private static final Vector2 V_NEAR = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 + 200,
            (double) GameMap.TILE_DIMENSIONS.y() / 2 + 100);
    final private static class TestMessageHandler extends GameMessageHandler {
        public static class TestSubmodule extends GameMapSubmoduleAbs {
            private final Vector2 v = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 - X_TEST,
                    (double) GameMap.TILE_DIMENSIONS.y() / 2 - Y_TEST - 1);
            private final Vector2 v2 = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 - X_TEST,
                    (double) GameMap.TILE_DIMENSIONS.y() / 2 - Y_TEST - 2);

            private final Position altarPos = new PositionImpl(v);
            private final Position pos1 = new PositionImpl(v2);


            TileBuilder tile = new TileBuilderImpl(0, "")
                    .addEntity(TileBuilder.EntityType.ALTAR, altarPos)
                    .addEntity(TileBuilder.EntityType.ENEMY, altarPos)
                    .addEntity(TileBuilder.EntityType.ENEMY, pos1);
            final GameMap map = new GameMapBuilderImpl().putTileAt(tile, new Pair<>(0,0)).build(this.handler());


            public TestSubmodule(final MessageHandler handler, ModelObserver modelObs) {
                super(handler, modelObs);
            }

            @Override
            public boolean movePlayerToTile(Direction dir) {
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

            return Set.of(
                    PlayerCharacterSubmodule.class,
                    EnemyTest.TestMessageHandler.TestSubmodule.class,
                    PartySubmodule.class,
                    FightSubmodule.class,
                    GameMapSubmodule.class
            );
        }
    }
    @BeforeEach
    void setUp() {
        testMeg = new TestMessageHandler();
    }
    @Test
    void updateFollow() {

        testMeg.handle(TestMessageHandler.TestSubmodule.class, map -> {
            for (final Entity entity : map.getEntitiesOnCurrentTile()) {
                if (entity instanceof Enemy) {
                    testMeg.handle(PlayerCharacterSubmodule.class, player -> {
                        final Enemy en = (Enemy) entity;
                        final Position near = new PositionImpl(V_NEAR);
                        entity.setPos(near);
                        entity.update();

                        assertEquals(Enemy.State.FOLLOW, en.getState());
                        for (int i = 0; i < NUM_TEST; i++) {
                            entity.update();
                            assertEquals(Enemy.State.FOLLOW, en.getState());
                        }

                    });


                }
            }
        });

    }
    @Test
    void updateWander() {

        testMeg.handle(TestMessageHandler.TestSubmodule.class, map -> {
            for (final Entity entity : map.getEntitiesOnCurrentTile()) {
                assertEquals(4, map.getEntitiesOnCurrentTile().size());
                if (entity instanceof Enemy) {
                    testMeg.handle(PlayerCharacterSubmodule.class, player -> {
                        final Enemy en = (Enemy) entity;
                        assertEquals(Enemy.State.WANDER, en.getState());
                        for (int i = 0; i < NUM_TEST ; i++) {
                            entity.update();
                            assertEquals(Enemy.State.WANDER, en.getState());
                        }

                    });


                }
            }
        });

    }
    @Test
    void direction() {
        final Set<People.Direction> possibleDirections = Set.of(People.Direction.UP, People.Direction.DOWN,
                People.Direction.LEFT, People.Direction.RIGHT);

        testMeg.handle(TestMessageHandler.TestSubmodule.class, map -> {
            for (final Entity entity : map.getEntitiesOnCurrentTile()) {
                if ( entity instanceof Enemy ) {
                    testMeg.handle(PlayerCharacterSubmodule.class, player -> {
                        final Enemy en = (Enemy) entity;
                        assertEquals(Enemy.State.WANDER, en.getState());
                        for (int i = 0; i < NUM_TEST; i++) {
                            entity.update();
                            assertTrue(possibleDirections.contains(en.getDirection()));

                        }

                    });


                }
            }
        });
    }
}
