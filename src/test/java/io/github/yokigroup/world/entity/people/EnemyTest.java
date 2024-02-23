package io.github.yokigroup.world.entity.people;

import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.event.submodule.PlayerCharacterSubmodule;
import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.GameMap;
import io.github.yokigroup.world.entity.AbsTestMessageHandler;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.Position;
import io.github.yokigroup.world.entity.PositionImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EnemyTest {
    private MessageHandler testMeg;

    @BeforeEach
    void setUp() {
        testMeg = new AbsTestMessageHandler();
    }

    @Test
    void updateFollow() {
        final Vector2 vNear = new Vector2Impl((double) GameMap.TILE_DIMENSIONS.x() / 2 + 200,
                (double) GameMap.TILE_DIMENSIONS.y() / 2 + 100);
        final int numTest = 20;
        testMeg.handle(AbsTestMessageHandler.TestSubmodule.class, map -> {
            for (final Entity entity : map.getEntitiesOnCurrentTile()) {
                if (entity instanceof Enemy) {
                    testMeg.handle(PlayerCharacterSubmodule.class, player -> {
                        final Enemy en = (Enemy) entity;
                        final Position near = new PositionImpl(vNear);
                        entity.setPos(near);
                        entity.update();

                        assertEquals(Enemy.State.FOLLOW, en.getState());
                        for (int i = 0; i < numTest; i++) {
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
        final int numTest = 20;
        testMeg.handle(AbsTestMessageHandler.TestSubmodule.class, map -> {
            for (final Entity entity : map.getEntitiesOnCurrentTile()) {
                assertEquals(3, map.getEntitiesOnCurrentTile().size());
                if (entity instanceof Enemy) {
                    testMeg.handle(PlayerCharacterSubmodule.class, player -> {
                        final Enemy en = (Enemy) entity;
                        assertEquals(Enemy.State.WANDER, en.getState());
                        for (int i = 0; i < numTest; i++) {
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
                People.Direction.LEFT, People.Direction.RIGHT, People.Direction.DOWN_RIGHT, People.Direction.LEFT_DOWN,
                People.Direction.UP_LEFT, People.Direction.UP_RIGHT);
        final int numTest = 20;
        testMeg.handle(AbsTestMessageHandler.TestSubmodule.class, map -> {
            for (final Entity entity : map.getEntitiesOnCurrentTile()) {
                if (entity instanceof Enemy) {
                    testMeg.handle(PlayerCharacterSubmodule.class, player -> {
                        final Enemy en = (Enemy) entity;
                        assertEquals(Enemy.State.WANDER, en.getState());
                        for (int i = 0; i < numTest; i++) {
                            entity.update();
                            assertTrue(possibleDirections.contains(en.getDirection()));
                        }
                    });


                }
            }
        });
    }
}
