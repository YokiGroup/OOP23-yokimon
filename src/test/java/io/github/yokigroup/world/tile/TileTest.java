package io.github.yokigroup.world.tile;

import io.github.yokigroup.core.GameMessageHandler;
import io.github.yokigroup.event.MessageHandler;
import io.github.yokigroup.world.Direction;
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
        assertTrue(tile.getHitboxes().contains(hitbox1));
        assertTrue(tile.getHitboxes().contains(hitbox2));
        assertTrue(tile.getHitboxes().contains(hitbox3));
        assertEquals(0, tile.getEntities().size());
    }
}
