package io.github.yokigroup.world.tile;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.util.WeightedPool;
import io.github.yokigroup.util.WeightedPoolImpl;
import io.github.yokigroup.util.MutablePairImpl;
import io.github.yokigroup.world.entity.Altar;
import io.github.yokigroup.world.entity.Entity;
import io.github.yokigroup.world.entity.PositionImpl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TileTest {

    private static Hitbox hitbox1;
    private static Hitbox hitbox2;
    private static Hitbox hitbox3;
    private static Vector2 vector1;
    private static Vector2 vector2;
    private static Vector2 vector3;
    private static Tile tile;

    @BeforeEach
    public void init() {
        final double x1 = 1.0d;
        final double y1 = 3.0d;
        final double x2 = 2.0d;
        final double y2 = 6.0d;
        final double x3 = 3.0d;
        final double y3 = 9.0d;
        final double r1 = 10.0d;
        final double r2 = 2.0d;
        final double r3 = 3.0d;
        hitbox1 = new CircularHitbox(r1);
        hitbox2 = new CircularHitbox(r2);
        hitbox3 = new CircularHitbox(r3);
        tile = new TileImpl(new HashSet<>(), new HashSet<>());
        vector1 = new Vector2Impl(x1, y1);
        vector2 = new Vector2Impl(x2, y2);
        vector3 = new Vector2Impl(x3, y3);
    }

    @Test
    void tileGettersAndAdd() {
        assertEquals(0, tile.getEntities().size());
        assertEquals(0, tile.getHitboxes().size());
        assertEquals(0, tile.getEntitySpawnLocations().size());
        tile.addSpawnLocation(vector1);
        assertEquals(1, tile.getEntitySpawnLocations().size());
        tile.addSpawnLocation(vector2);
        assertEquals(2, tile.getEntitySpawnLocations().size());
        tile.addSpawnLocation(vector3);
        assertEquals(3, tile.getEntitySpawnLocations().size());
        tile.addHitbox(hitbox1);
        assertEquals(1, tile.getHitboxes().size());
        tile.addHitbox(hitbox2);
        assertEquals(2, tile.getHitboxes().size());
        tile.addHitbox(hitbox3);
        assertEquals(3, tile.getHitboxes().size());
    }

    @Test
    void spawnEntities() {
        final WeightedPool<Entity> entityPool = new WeightedPoolImpl<>();
        // FIXME: instantiation of entities
        entityPool.addElement(new Altar(new PositionImpl(new MutablePairImpl(0, 0)), null, null, null), 1.0f);
        entityPool.addElement(new Altar(new PositionImpl(new MutablePairImpl(0, 0)), null, null, null), 1.0f);
        entityPool.addElement(new Altar(new PositionImpl(new MutablePairImpl(0, 0)), null, null, null), 1.0f);
        entityPool.addElement(new Altar(new PositionImpl(new MutablePairImpl(0, 0)), null, null, null), 1.0f);
        entityPool.addElement(new Altar(new PositionImpl(new MutablePairImpl(0, 0)), null, null, null), 1.0f);
        entityPool.addElement(new Altar(new PositionImpl(new MutablePairImpl(0, 0)), null, null, null), 1.0f);
        entityPool.addElement(new Altar(new PositionImpl(new MutablePairImpl(0, 0)), null, null, null), 1.0f);
        tile.addSpawnLocation(vector1);
        tile.addSpawnLocation(vector2);
        tile.addSpawnLocation(vector3);
        tile.spawnEntities(entityPool);
        assertEquals(3, tile.getEntities().size());
        tile.getEntities()
                .stream()
                .map(e -> e.getPos().turnIntoVector())
                .forEach(v -> assertTrue(tile.getEntitySpawnLocations().contains(v)));
    }
}
