package io.github.yokigroup.world.tile;

import io.github.yokigroup.util.Vector2Impl;
import io.github.yokigroup.world.entity.hitbox.CircularHitbox;
import io.github.yokigroup.world.entity.hitbox.Hitbox;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TileTest {

    @Test
    void tileGettersAndAdd() {
        final Hitbox h1 = new CircularHitbox(10.0d);
        final Hitbox h2 = new CircularHitbox(2.0d);
        final Hitbox h3 = new CircularHitbox(3.0d);
        final Tile tile = new TileImpl();
        assertEquals(0, tile.getEntities().size());
        assertEquals(0, tile.getHitboxes().size());
        assertEquals(0, tile.getEntitySpawnLocations().size());
        tile.addSpawnLocation(new Vector2Impl(1.0d, 3.0d));
        assertEquals(1, tile.getEntitySpawnLocations().size());
        tile.addSpawnLocation(new Vector2Impl(2.0d, 6.0d));
        assertEquals(2, tile.getEntitySpawnLocations().size());
        tile.addSpawnLocation(new Vector2Impl(3.0d, 9.0d));
        assertEquals(3, tile.getEntitySpawnLocations().size());
        tile.addHitbox(h1);
        assertEquals(1, tile.getHitboxes().size());
        tile.addHitbox(h2);
        assertEquals(2, tile.getHitboxes().size());
        tile.addHitbox(h3);
        assertEquals(3, tile.getHitboxes().size());
    }

    @Test
    void spawnEntities() {
        // TODO: fill this test
    }
}