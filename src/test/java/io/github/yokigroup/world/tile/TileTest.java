package io.github.yokigroup.world.tile;

import io.github.yokigroup.util.Vector2;
import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TileTest {

    private static Hitbox hitbox1;
    private static Hitbox hitbox2;
    private static Hitbox hitbox3;
    private static Vector2 vector1;
    private static Vector2 vector2;
    private static Vector2 vector3;
    private static Tile tile1;

    @BeforeEach
    public void init() {
        final double x1 = 1.0d;
        final double y1 = 3.0d;
        final double x2 = 2.0d;
        final double y2 = 6.0d;
        final double x3 = 3.0d;
        final double y3 = 9.0d;
        hitbox1 = new CircularHitbox(10.0d);
        hitbox2 = new CircularHitbox(2.0d);
        hitbox3 = new CircularHitbox(3.0d);
        tile1 = new TileImpl();
        vector1 = new Vector2Impl(x1, y1);
        vector2 = new Vector2Impl(x2, y2);
        vector3 = new Vector2Impl(x3, y3);
    }

    @Test
    void tileGettersAndAdd() {
        assertEquals(0, tile1.getEntities().size());
        assertEquals(0, tile1.getHitboxes().size());
        assertEquals(0, tile1.getEntitySpawnLocations().size());
        tile1.addSpawnLocation(vector1);
        assertEquals(1, tile1.getEntitySpawnLocations().size());
        tile1.addSpawnLocation(vector2);
        assertEquals(2, tile1.getEntitySpawnLocations().size());
        tile1.addSpawnLocation(vector3);
        assertEquals(3, tile1.getEntitySpawnLocations().size());
        tile1.addHitbox(hitbox1);
        assertEquals(1, tile1.getHitboxes().size());
        tile1.addHitbox(hitbox2);
        assertEquals(2, tile1.getHitboxes().size());
        tile1.addHitbox(hitbox3);
        assertEquals(3, tile1.getHitboxes().size());
    }

    @Test
    void spawnEntities() {
        // TODO: fill this test
    }
}
