package io.github.yokigroup.world.tile;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.util.WeightedPoolImpl;
import io.github.yokigroup.world.Direction;
import io.github.yokigroup.world.entity.Altar;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TileTest {

    private static Hitbox hitbox1;
    private static Hitbox hitbox2;
    private static Hitbox hitbox3;
    private static Tile tile;

    @BeforeEach
    public void init() {
        final MessageHandler messageHandler = new GameMessageHandler();
        final double r1 = 10.0d;
        final double r2 = 2.0d;
        final double r3 = 3.0d;
        hitbox1 = new CircularHitbox(r1);
        hitbox2 = new CircularHitbox(r2);
        hitbox3 = new CircularHitbox(r3);
        tile = new TileBuilderImpl(13)
                .addHitbox(hitbox1)
                .addHitbox(hitbox2)
                .addHitbox(hitbox3)
                .addAdjacency(Direction.UP)
                .addAdjacency(Direction.DOWN)
                .build(messageHandler);
    }

    @Test
    void tileGettersAndAdd() {
        assertEquals(13, tile.getId());
        assertEquals(3, tile.getAdjacencies().size());
        assertTrue(tile.getAdjacencies().contains(Direction.UP));
        assertTrue(tile.getAdjacencies().contains(Direction.DOWN));
        assertEquals(3, tile.getHitboxes().size());
    }

    @Test
    void spawnEntities() {
        final WeightedPool<Entity> entityPool = new WeightedPoolImpl<>();
        final MessageHandler mh = new GameMessageHandler();

        entityPool.addElement(new Altar(0, new PositionImpl(new Vector2Impl(0, 0)), null,  mh), 1.0f);
        entityPool.addElement(new Altar(1, new PositionImpl(new Vector2Impl(0, 0)), null,  mh), 1.0f);
        entityPool.addElement(new Altar(2, new PositionImpl(new Vector2Impl(0, 0)), null,  mh), 1.0f);
        entityPool.addElement(new Altar(3, new PositionImpl(new Vector2Impl(0, 0)), null,  mh), 1.0f);
        entityPool.addElement(new Altar(4, new PositionImpl(new Vector2Impl(0, 0)), null,  mh), 1.0f);
        /*
        tile.addSpawnLocation(vector1);
        tile.addSpawnLocation(vector2);
        tile.addSpawnLocation(vector3);
        tile.spawnEntities(entityPool);
        assertEquals(3, tile.getEntities().size());
        tile.getEntities()
                .stream()
                .map(e -> e.getPos().getPosition())
                .forEach(v -> assertTrue(tile.getEntitySpawnLocations().contains(v)));
        */
    }
}
